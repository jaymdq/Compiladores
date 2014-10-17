%{

/* Imports necesarios en la clase Parser */
import gui.ConsolaManager;
import lexico.AnalizadorLexico;
import proyecto.Proyecto;
import proyecto.Token;
import proyecto.ElementoTS;
import java.util.Vector;

%}

%token IDENTIFICADOR ENTERO ENTERO_LSS CADENA_MULTILINEA PR_SI PR_ENTONCES PR_SINO PR_IMPRIMIR PR_ENTERO PR_ENTERO_LSS	PR_ITERAR PR_HASTA PR_VECTOR PR_DE COMP_MAYOR_IGUAL COMP_MENOR_IGUAL COMP_DISTINTO ASIGNACION PUNTO_PUNTO FUERA_RANGO
	
%nonassoc INFERIOR_A_SINO
%nonassoc PR_SINO

%%

/* Gramática */

programa	: sent_declarativa sent_ejecutable
			;
		
sent_declarativa 	: sent_declarativa declaracion
					| /* Sentencia declarativa vacía */
					; 

declaracion 	: tipo_dato lista_variables ';' { indicarSentencia("Declaración de tipo básico"); generarDeclaracionTipoBasico(); } 
				| IDENTIFICADOR '[' ENTERO PUNTO_PUNTO ENTERO ']' PR_VECTOR PR_DE tipo_dato ';' { indicarSentencia("Declaración de tipo vector"); generarDeclaracionTipoVector($1,$3,$5); tratarRedeclaraciones($1);}
				| declaracion_invalida
				;
			
declaracion_invalida	: tipo_dato error ';' { escribirError("Sintaxis de declaración de tipo básico incorrecta."); }
						| IDENTIFICADOR '[' error ']' { escribirError("Sintaxis de declaración de tipo vector incorrecta."); } PR_VECTOR PR_DE tipo_dato ';' 
						| IDENTIFICADOR '[' ENTERO PUNTO_PUNTO ENTERO ']' error ';' { escribirError("Sintaxis de declaración de tipo vector incorrecta."); }
						;
				
lista_variables	: IDENTIFICADOR							{ tratarRedeclaraciones($1); agregar();}
				| lista_variables ',' IDENTIFICADOR 	{ tratarRedeclaraciones($3); agregar();}
				;
			
tipo_dato	: PR_ENTERO		{ agregar("entero");}
			| PR_ENTERO_LSS { agregar("entero_lss");}
			;
			
sent_ejecutable	: sentencia_valida
				| sent_ejecutable sentencia 
				;
					
sentencia_valida	:  { indicarSentencia("Selección");} seleccion
					|  { indicarSentencia("Iteración");} iteracion 				
					|  { indicarSentencia("Impresión");} impresion 				
					|  asignacion 
					|  PR_SINO bloque { escribirError("No se esperaba la palabra reservada \"sino\"."); }	
					|  error ';' { escribirError("Sentencia inválida."); }
					;

sentencia 	:  sentencia_valida
			|  declaracion { escribirError("No se pueden declarar variables luego de alguna sentencia ejecutable."); }  
			;

seleccion	: PR_SI '(' condicion ')' PR_ENTONCES bloque	 %prec INFERIOR_A_SINO					
			| PR_SI '(' condicion ')' PR_ENTONCES bloque PR_SINO bloque	
			| seleccion_invalida
			;
			
seleccion_invalida	: PR_SI {escribirError("Falta '(' en sentencia si.");} condicion ')' PR_ENTONCES bloque 
					| PR_SI '(' condicion {escribirError("Falta ')' en sentencia si.");} PR_ENTONCES bloque 
					| PR_SI '(' error ')' {escribirError("Condición inválida en la sentencia si.");} PR_ENTONCES bloque  
					| PR_SI '(' condicion ')' error bloque {escribirError("Sentencia si inválida.");}
					| PR_SI error bloque {escribirError("Sentencia si inválida.");}
					;
			
iteracion	: PR_ITERAR bloque PR_HASTA condicion ';'
			| iteracion_invalida
			;
			
iteracion_invalida	: PR_ITERAR { escribirError("No se especificó un bloque a iterar."); } PR_HASTA condicion ';' 
					| PR_ITERAR bloque condicion ';' { escribirError("No se especificó la palabra reservada \"hasta\" en la iteración."); }
					| PR_ITERAR bloque PR_HASTA error ';' { escribirError("Condición inválida en la sentencia iterar."); }
					;
			
impresion	: PR_IMPRIMIR  '(' CADENA_MULTILINEA ')' ';' { tratarCadenaMultilinea($3);}
			| impresion_invalida
			;
			
impresion_invalida 	: PR_IMPRIMIR {escribirError("Falta '(' en sentencia de impresión.");} CADENA_MULTILINEA error ';'
					| PR_IMPRIMIR '(' ')' {escribirError("No se especificó una cadena multilínea en sentencia de impresión.");} ';'
					| PR_IMPRIMIR '(' CADENA_MULTILINEA {escribirError("Falta ')' en sentencia de impresión.");} ';' 
					| PR_IMPRIMIR '(' error ')' ';' {escribirError("Impresión Inválida.");}
					| PR_IMPRIMIR ';' {escribirError("Falta \"('Cadena_Multilinea')\" .");}
					;
			
asignacion	: asignable  ASIGNACION e ';' { indicarSentencia("Asignación"); subindiceValido = true;}
			| asignacion_invalida
			;

asignacion_invalida	: asignable ASIGNACION error ';' { escribirError("Asignación inválida.");}
					| ASIGNACION error ';'			 { escribirError("Asignación inválida.");}
					;
			
bloque		: sentencia
			| '{' sent_ejecutable '}'
			| '{' declaracion { escribirError("No se pueden declarar variables dentro de un bloque."); } sent_declarativa sent_ejecutable '}'
			| '{' '}'  { escribirError("Bloque de sentencias vacío."); }
			;
					
condicion	: e comparador e {subindiceValido = true;}
			;
		
comparador	: '>'
			| COMP_MAYOR_IGUAL
			| '<'
			| COMP_MENOR_IGUAL
			| '='
			| COMP_DISTINTO
			;
			
e	: e '+' t
	| e '-' t
	| t					
	;
	
t	: t '*' f
	| t "/" f
	| f					
	;
	
f	: valor
	| '-' ENTERO		{ chequearNegativo(); }
	| '-' ENTERO_LSS 	{ escribirError("Constante negativa fuera de rango."); borrarFueraRango(); }
	| FUERA_RANGO 		{ escribirError("Constante fuera de rango"); }
	;

valor	: asignable
		| ENTERO 		{ chequearRango(); }
		| ENTERO_LSS 	{ tratarConstante($1,"entero_lss");  subindiceValido = false;}
		;

asignable	: IDENTIFICADOR			  { tratarNodeclaraciones($1); chequearTipoParaSub($1);}
			| IDENTIFICADOR '[' e ']' { tratarNodeclaraciones($1); chequearTipoParaSub($1); chequearSubindice();}
			;
			
%%

/* Código */

private Proyecto proyecto;
private int errores = 0;
private Vector<Token> declaracionesAux = new Vector<Token>();
private boolean subindiceValido = true;


private void yyerror(String string) {
	//System.out.println(string);	
}

private int yylex(){
	int salida = AnalizadorLexico.yylex();
	if (AnalizadorLexico.yylval != null){
		yylval = AnalizadorLexico.yylval;
		//AnalizadorLexico.yylval = null;
	}else{
		yylval = new ParserVal(); 
	}
	return salida;
}

private int lineaActual(){
	return proyecto.getLineaActual();
}

private void escribirError(String s){
	errores++;
	ConsolaManager.getInstance().escribirError("Sintáctico [Línea " + lineaActual() + "] "+ s);
}

private void escribirErrorDeGeneracion(String s){
	ConsolaManager.getInstance().escribirError("Gen. Cod. Int [Línea " + lineaActual() + "] "+ s);
}

private void indicarSentencia(String s){
	proyecto.addSentenciaToList("Línea " +lineaActual()+ " -> " + s + ".");
}

public Parser(Proyecto p){
	this.proyecto = p;
}

private void chequearNegativo(){
	//Chequear valor del negativo
	Token t = proyecto.getTablaDeSimbolos().getToken(yylval.ival);
	if ( t.getContador() == 1){
		//Se pisa el token existente
		Token tnuevo = new Token(Token.TipoToken.ENTERO,"-"+t.getLexema());
		if (! proyecto.getTablaDeSimbolos().containsToken(tnuevo.getLexema()) ){
			t.setLexema("-"+t.getLexema());
			tratarConstante(yylval,"entero");
		}
		else{
			proyecto.getTablaDeSimbolos().getToken(tnuevo.getLexema()).aumentarContador();
			proyecto.getTablaDeSimbolos().remove(t.getLexema());
		}
	}else{
		//Se crea otro token nuevo.
		t.disminuirContador();
		
		Token tnuevo = new Token(Token.TipoToken.ENTERO,"-"+t.getLexema());
		if (! proyecto.getTablaDeSimbolos().containsToken(tnuevo.getLexema()) ){
			proyecto.getTablaDeSimbolos().add(tnuevo);
			tratarConstante(new ParserVal(proyecto.getTablaDeSimbolos().getList().size()-1),"entero");
		}
		else{
			proyecto.getTablaDeSimbolos().getToken(tnuevo.getLexema()).aumentarContador();
			tratarConstante(new ParserVal(proyecto.getTablaDeSimbolos().getPos(tnuevo.getLexema())),"entero");
		}
	}
	//Se actualiza la tabla de simbolos visualmente.
	actualizarTablaDeSimbolos();
}

public void chequearRango(){
	Token t = proyecto.getTablaDeSimbolos().getToken(yylval.ival);
	if (Integer.parseInt(t.getLexema()) == 32768){
		t.setTipo(Token.TipoToken.ENTERO_LSS);
		tratarConstante(yylval,"entero_lss");
	}else
		tratarConstante(yylval,"entero");
	
	actualizarTablaDeSimbolos();
}

public void actualizarTablaDeSimbolos(){
	proyecto.getTablaDeSimbolos().setearCambios();
	proyecto.getTablaDeSimbolos().notifyObservers();
	for (ElementoTS elemento : proyecto.getTablaDeSimbolos().getList()){
		proyecto.getTablaDeSimbolos().setearCambios();
		proyecto.getTablaDeSimbolos().notifyObservers(elemento);
	}
}

public void borrarFueraRango(){
	Token t = proyecto.getTablaDeSimbolos().getToken(yylval.ival);
	if (t.getContador() > 1){
		t.disminuirContador();
	}else{
		proyecto.getTablaDeSimbolos().remove(t.getLexema());
	}
	//Se actualiza la tabla de simbolos visualmente.
	actualizarTablaDeSimbolos();
}

public int getCantidadErrores(){
	return errores;
}

public void agregar(){
	Token t = proyecto.getTablaDeSimbolos().getToken(yylval.ival);
	declaracionesAux.add(t);
}

public void agregar(String tipo){
	Token t = new Token();
	t.setLexema(tipo);
	declaracionesAux.add(t);
}

public void generarDeclaracionTipoBasico(){
ElementoTS.TIPOS tipo;
if (declaracionesAux.elementAt(0).getLexema().equals("entero"))
	tipo = ElementoTS.TIPOS.ENTERO;
else
	tipo = ElementoTS.TIPOS.ENTERO_LSS;
	
	for (int i = 1; i < declaracionesAux.size();i++){
		int pos = proyecto.getTablaDeSimbolos().getPos(declaracionesAux.elementAt(i).getLexema());
		ElementoTS elemento = proyecto.getTablaDeSimbolos().getElemento(pos);
		elemento.setTipo(tipo);
		elemento.setUso(ElementoTS.USOS.VARIABLE);
	}
	
	actualizarTablaDeSimbolos();
	declaracionesAux.clear();
}

public void generarDeclaracionTipoVector(ParserVal iden, ParserVal limInf, ParserVal limSup){
ElementoTS.TIPOS tipo;
if (declaracionesAux.elementAt(0).getLexema().equals("entero"))
	tipo = ElementoTS.TIPOS.VECTOR_ENTERO;
else
	tipo = ElementoTS.TIPOS.VECTOR_ENTERO_LSS;
	
	ElementoTS elemento = proyecto.getTablaDeSimbolos().getElemento(iden.ival);
	
	Integer lim_i = Integer.parseInt(proyecto.getTablaDeSimbolos().getElemento(limInf.ival).getToken().getLexema());
	
	Integer lim_s = Integer.parseInt(proyecto.getTablaDeSimbolos().getElemento(limSup.ival).getToken().getLexema());
	
	//Chequear rangos
	if (lim_i < 0)
		escribirErrorDeGeneracion("Límite Inferior menor a 0.");
	if (lim_s < 0)
		escribirErrorDeGeneracion("Límite Superior menor a 0.");
	if (lim_i > lim_s)
		escribirErrorDeGeneracion("El límite inferior es mayor al superior.");
		
	//----
	
	tratarConstante(limInf,"entero");
	tratarConstante(limSup,"entero");
	
	elemento.setLim_inf(lim_i);
	elemento.setLim_sup(lim_s);
	elemento.setTipo(tipo);
	elemento.setUso(ElementoTS.USOS.ARREGLO);
	
	actualizarTablaDeSimbolos();
	declaracionesAux.clear();
}

public void tratarConstante(ParserVal pos,String tipoDado){
	ElementoTS.TIPOS tipo;
	if (tipoDado.equals("entero"))
		tipo = ElementoTS.TIPOS.ENTERO;
	else
		tipo = ElementoTS.TIPOS.ENTERO_LSS;
	
	ElementoTS elemento = proyecto.getTablaDeSimbolos().getElemento(pos.ival);
	
	elemento.setTipo(tipo);
	
	elemento.setUso(ElementoTS.USOS.CONSTANTE);
	
	actualizarTablaDeSimbolos();
	declaracionesAux.clear();
}

public void tratarCadenaMultilinea(ParserVal pos){
	ElementoTS elemento = proyecto.getTablaDeSimbolos().getElemento(pos.ival);
	
	elemento.setTipo(ElementoTS.TIPOS.CADENA_MULTILINEA);
	
	elemento.setUso(ElementoTS.USOS.CONSTANTE);
	
	actualizarTablaDeSimbolos();
	declaracionesAux.clear();
}

public void tratarRedeclaraciones(ParserVal pos){
	ElementoTS elemento = proyecto.getTablaDeSimbolos().getElemento(pos.ival);
	if (elemento.getToken().getContador() > 1)
		escribirErrorDeGeneracion("Duplicación de identificador \"" + elemento.getToken().getLexema() + "\".");
}

public void tratarNodeclaraciones(ParserVal pos){
	ElementoTS elemento = proyecto.getTablaDeSimbolos().getElemento(pos.ival);
	if (elemento.getTipo() == null)
		escribirErrorDeGeneracion("Identificador \"" + elemento.getToken().getLexema() + "\" no declarado.");
}

public void chequearSubindice(){
	if (!subindiceValido)
		escribirErrorDeGeneracion("Subindice Inválido.");

	subindiceValido = true;
}

public void chequearTipoParaSub(ParserVal pos){
	ElementoTS elemento = proyecto.getTablaDeSimbolos().getElemento(pos.ival);
		if (!(elemento.getTipo() == ElementoTS.TIPOS.ENTERO || elemento.getTipo() == ElementoTS.TIPOS.VECTOR_ENTERO || elemento.getTipo() == null))
			subindiceValido = false;
}