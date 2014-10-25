%{

/* Imports necesarios en la clase Parser */
import gui.ConsolaManager;
import lexico.AnalizadorLexico;
import proyecto.Proyecto;
import proyecto.Token;
import proyecto.ElementoTS;
import java.util.Vector;
import arbol.sintactico.*;

%}

%token IDENTIFICADOR ENTERO ENTERO_LSS CADENA_MULTILINEA PR_SI PR_ENTONCES PR_SINO PR_IMPRIMIR PR_ENTERO PR_ENTERO_LSS	PR_ITERAR PR_HASTA PR_VECTOR PR_DE COMP_MAYOR_IGUAL COMP_MENOR_IGUAL COMP_DISTINTO ASIGNACION PUNTO_PUNTO FUERA_RANGO
	
%nonassoc INFERIOR_A_SINO
%nonassoc PR_SINO

%%

/* Gram�tica */

programa	: sent_declarativa sent_ejecutable
			;
		
sent_declarativa 	: sent_declarativa declaracion
					| /* Sentencia declarativa vac�a */
					; 

declaracion 	: tipo_dato lista_variables ';' { indicarSentencia("Declaraci�n de tipo b�sico"); generarDeclaracionTipoBasico(); } 
				| IDENTIFICADOR '[' ENTERO PUNTO_PUNTO ENTERO ']' PR_VECTOR PR_DE tipo_dato ';' { indicarSentencia("Declaraci�n de tipo vector"); generarDeclaracionTipoVector($1,$3,$5); tratarRedeclaraciones($1);}
				| declaracion_invalida
				;
			
declaracion_invalida	: tipo_dato error ';' { escribirError("Sintaxis de declaraci�n de tipo b�sico incorrecta."); }
						| IDENTIFICADOR '[' error ']' { escribirError("Sintaxis de declaraci�n de tipo vector incorrecta."); } PR_VECTOR PR_DE tipo_dato ';' 
						| IDENTIFICADOR '[' ENTERO PUNTO_PUNTO ENTERO ']' error ';' { escribirError("Sintaxis de declaraci�n de tipo vector incorrecta."); }
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
					
sentencia_valida	:  { indicarSentencia("Selecci�n"); aumentarNivel(); }  seleccion	{ apilar(SentenciaSeleccion); }
					|  { indicarSentencia("Iteraci�n"); aumentarNivel(); }  iteracion	{ apilar(SentenciaIteracion); }
					|  { indicarSentencia("Impresi�n"); }  impresion 					{ apilar(SentenciaImpresion); }
					|  asignacion 														{ apilar(SentenciaAsignacion); }
					|  PR_SINO bloque 	{ escribirError("No se esperaba la palabra reservada \"sino\"."); }	
					|  error ';' 		{ escribirError("Sentencia inv�lida."); }
					;

sentencia 	:  sentencia_valida
			|  declaracion { escribirError("No se pueden declarar variables luego de alguna sentencia ejecutable."); }  
			;

seleccion	: PR_SI '(' condicion ')' PR_ENTONCES bloque	 %prec INFERIOR_A_SINO	{ Bloque = desapilar(); decrementarNivel(); Cuerpo = crear_nodo("Cuerpo",Bloque,null);    SentenciaSeleccion = crear_nodo("Selecci�n",getUltimaCondicion(),Cuerpo); }			
			| PR_SI '(' condicion ')' PR_ENTONCES bloque PR_SINO { aumentarNivel(); } bloque {  Bloque2 = desapilar(); decrementarNivel(); Bloque1 = desapilar(); decrementarNivel(); Cuerpo = crear_nodo("Cuerpo",Bloque1,Bloque2); SentenciaSeleccion = crear_nodo("Selecci�n",getUltimaCondicion(),Cuerpo); }			
			| seleccion_invalida
			;
			
seleccion_invalida	: PR_SI {escribirError("Falta '(' en sentencia si.");} condicion ')' PR_ENTONCES bloque 
					| PR_SI '(' condicion {escribirError("Falta ')' en sentencia si.");} PR_ENTONCES bloque 
					| PR_SI '(' error ')' {escribirError("Condici�n inv�lida en la sentencia si.");} PR_ENTONCES bloque  
					| PR_SI '(' condicion ')' error bloque {escribirError("Sentencia si inv�lida.");}
					| PR_SI error bloque {escribirError("Sentencia si inv�lida.");}
					;
			
iteracion	: PR_ITERAR bloque PR_HASTA condicion ';' { Bloque = desapilar(); decrementarNivel(); SentenciaIteracion = crear_nodo("Iteraci�n",Bloque,getUltimaCondicion()); }
			| iteracion_invalida
			;
			
iteracion_invalida	: PR_ITERAR { escribirError("No se especific� un bloque a iterar."); } PR_HASTA condicion ';' 
					| PR_ITERAR bloque condicion ';' { escribirError("No se especific� la palabra reservada \"hasta\" en la iteraci�n."); }
					| PR_ITERAR bloque PR_HASTA error ';' { escribirError("Condici�n inv�lida en la sentencia iterar."); }
					;
			
impresion	: PR_IMPRIMIR  '(' CADENA_MULTILINEA ')' ';' { tratarCadenaMultilinea($3); SentenciaImpresion = crear_nodo("Impresi�n",crear_hoja($3),null); }
			| impresion_invalida
			;
			
impresion_invalida 	: PR_IMPRIMIR {escribirError("Falta '(' en sentencia de impresi�n.");} CADENA_MULTILINEA error ';'
					| PR_IMPRIMIR '(' ')' {escribirError("No se especific� una cadena multil�nea en sentencia de impresi�n.");} ';'
					| PR_IMPRIMIR '(' CADENA_MULTILINEA {escribirError("Falta ')' en sentencia de impresi�n.");} ';' 
					| PR_IMPRIMIR '(' error ')' ';' {escribirError("Impresi�n Inv�lida.");}
					| PR_IMPRIMIR ';' {escribirError("Falta \"('Cadena_Multilinea')\" .");}
					;
			
asignacion	: asignable  ASIGNACION e ';' { indicarSentencia("Asignaci�n"); SentenciaAsignacion = crear_nodo("Asignaci�n",crear_hoja($1),E);}
			| asignacion_invalida
			;

asignacion_invalida	: asignable ASIGNACION error ';' { escribirError("Asignaci�n inv�lida.");}
					| ASIGNACION error ';'			 { escribirError("Asignaci�n inv�lida.");}
					;
			
bloque		: sentencia
			| '{' sent_ejecutable '}'
			| '{' declaracion { escribirError("No se pueden declarar variables dentro de un bloque."); } sent_declarativa sent_ejecutable '}'
			| '{' '}'  { escribirError("Bloque de sentencias vac�o."); }
			;
					
condicion	: e comparador e { Condicion = crear_nodo(UltimoComparador,E1,E2); agregarCondicion(Condicion); }
			;
		
comparador	: '>'				{ UltimoComparador = new String(">"); }
			| COMP_MAYOR_IGUAL	{ UltimoComparador = new String(">="); }
			| '<'				{ UltimoComparador = new String("<"); }
			| COMP_MENOR_IGUAL	{ UltimoComparador = new String("<="); }
			| '='				{ UltimoComparador = new String("="); }
			| COMP_DISTINTO		{ UltimoComparador = new String("^="); }
			;
			
e	: e '+' t			{ E = crear_nodo("Suma \"+\"",E,T);  agregarExpresion(E); }
	| e '-' t			{ E = crear_nodo("Resta \"-\"",E,T); agregarExpresion(E); }
	| t					{ E = T; agregarExpresion(E); }
	;
	
t	: t '*' f			{ T = crear_nodo("Multiplicaci�n \"*\"",T,F); }
	| t '/' f			{ T = crear_nodo("Divisi�n \"/\"",T,F); }
	| f					{ T = F; }
	;
	
f	: valor				{ F = HojaAux; }
	| '-' ENTERO		{ chequearNegativo(); }
	| '-' ENTERO_LSS 	{ escribirError("Constante negativa fuera de rango."); borrarFueraRango(); }
	| FUERA_RANGO 		{ escribirError("Constante fuera de rango"); }
	;

valor	: asignable
		| ENTERO 		{ chequearRango();					HojaAux = crear_hoja($1); }
		| ENTERO_LSS 	{ tratarConstante($1,"entero_lss");	HojaAux = crear_hoja($1);  }
		;

asignable	: IDENTIFICADOR			  { tratarNodeclaraciones($1);	HojaAux = crear_hoja($1);  }
			| IDENTIFICADOR '[' e ']' { tratarNodeclaraciones($1);	HojaAux = crear_hoja($1); tratarIndiceInvalido($1); }
			;
			
%%

/* C�digo */

private Proyecto proyecto;
private int errores = 0;
private int erroresGenCod = 0;

private Vector<Token> declaracionesAux = new Vector<Token>();

private Integer nivelActual = 0;
private Vector<Integer>  pilaNivel = new Vector<Integer>();
private Vector<ArbolAbs> pilaCondiciones = new Vector<ArbolAbs>();
private Vector<ArbolAbs> pilaBloques = new Vector<ArbolAbs>();
private Vector<ArbolAbs> sentencias = new Vector<ArbolAbs>();

private ArbolAbs SentenciaAsignacion;
private ArbolAbs SentenciaImpresion;
private ArbolAbs SentenciaSeleccion;
private ArbolAbs SentenciaIteracion;
private ArbolAbs E;
private ArbolAbs T;
private ArbolAbs F;
private ArbolAbs HojaAux;
private ArbolAbs Condicion;
private ArbolAbs Cuerpo;
private ArbolAbs E1;
private ArbolAbs E2;
private ArbolAbs Bloque;
private ArbolAbs Bloque1;
private ArbolAbs Bloque2;

private String UltimoComparador = "";

private void yyerror(String string) {
	
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
	ConsolaManager.getInstance().escribirError("Sint�ctico [L�nea " + lineaActual() + "] "+ s);
}

private void escribirErrorDeGeneracion(String s){
	erroresGenCod++;
	ConsolaManager.getInstance().escribirError("Gen. Cod. Int [L�nea " + lineaActual() + "] "+ s);
}

private void indicarSentencia(String s){
	proyecto.addSentenciaToList("L�nea " +lineaActual()+ " -> " + s + ".");
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

private void chequearRango(){
	Token t = proyecto.getTablaDeSimbolos().getToken(yylval.ival);
	if (Integer.parseInt(t.getLexema()) == 32768){
		t.setTipo(Token.TipoToken.ENTERO_LSS);
		tratarConstante(yylval,"entero_lss");
	}else
		tratarConstante(yylval,"entero");
	
	actualizarTablaDeSimbolos();
}

private void actualizarTablaDeSimbolos(){
	proyecto.getTablaDeSimbolos().setearCambios();
	proyecto.getTablaDeSimbolos().notifyObservers();
	for (ElementoTS elemento : proyecto.getTablaDeSimbolos().getList()){
		proyecto.getTablaDeSimbolos().setearCambios();
		proyecto.getTablaDeSimbolos().notifyObservers(elemento);
	}
}

private void borrarFueraRango(){
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

public int getCantidadErroresGenCod(){
	return erroresGenCod;
}
private void agregar(){
	Token t = proyecto.getTablaDeSimbolos().getToken(yylval.ival);
	declaracionesAux.add(t);
}

private void agregar(String tipo){
	Token t = new Token();
	t.setLexema(tipo);
	declaracionesAux.add(t);
}

private void generarDeclaracionTipoBasico(){
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

private void generarDeclaracionTipoVector(ParserVal iden, ParserVal limInf, ParserVal limSup){
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
		escribirErrorDeGeneracion("L�mite Inferior menor a 0.");
	if (lim_s < 0)
		escribirErrorDeGeneracion("L�mite Superior menor a 0.");
	if (lim_i > lim_s)
		escribirErrorDeGeneracion("El l�mite inferior es mayor al superior.");
		
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

private void tratarConstante(ParserVal pos,String tipoDado){
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

private void tratarCadenaMultilinea(ParserVal pos){
	ElementoTS elemento = proyecto.getTablaDeSimbolos().getElemento(pos.ival);
	
	elemento.setTipo(ElementoTS.TIPOS.CADENA_MULTILINEA);
	
	elemento.setUso(ElementoTS.USOS.CONSTANTE);
	
	actualizarTablaDeSimbolos();
	declaracionesAux.clear();
}

private void tratarRedeclaraciones(ParserVal pos){
	ElementoTS elemento = proyecto.getTablaDeSimbolos().getElemento(pos.ival);
	if (elemento.getToken().getContador() > 1)
		escribirErrorDeGeneracion("Duplicaci�n de identificador \"" + elemento.getToken().getLexema() + "\".");
}

private void tratarNodeclaraciones(ParserVal pos){
	ElementoTS elemento = proyecto.getTablaDeSimbolos().getElemento(pos.ival);
	if (elemento.getTipo() == null)
		escribirErrorDeGeneracion("Identificador \"" + elemento.getToken().getLexema() + "\" no declarado.");
}

private void tratarIndiceInvalido(ParserVal pos){
	ElementoTS elemento = proyecto.getTablaDeSimbolos().getElemento(pos.ival);
	if (E.getTipo() == null){
		escribirErrorDeGeneracion("�ndice inv�lido en el vector \"" + elemento.getToken().getLexema() + "\", existen operaciones entre entero y entero_lss.");
	}
	if (E.getTipo() == ElementoTS.TIPOS.ENTERO_LSS){
		escribirErrorDeGeneracion("�ndice inv�lido en el vector \"" + elemento.getToken().getLexema() + "\", el sub�ndice no puede ser de tipo entero_lss.");
	}
}
private ArbolAbs crear_hoja(ParserVal pos){
	ElementoTS elemento = proyecto.getTablaDeSimbolos().getElemento(pos.ival);
	ArbolAbs salida = new Hoja(elemento);
	return salida;
}

private ArbolAbs crear_nodo(String Operacion,ArbolAbs arb_izq,ArbolAbs arb_der){
	ArbolAbs salida = new Arbol(Operacion,arb_izq,arb_der);
	return salida;
}

private void addSentencia(ArbolAbs sentencia){
	//Vector de sentencias a devolver
	sentencias.add(sentencia);
}

public Vector<ArbolAbs> getSentencias(){
	//Vector de sentencias a devolver
	return sentencias;
}
private void agregarExpresion(ArbolAbs exp){
	if (E2 != null)
		E1 = E2.clone();
	else
		E1 = exp;
	E2 = exp;
}

private void agregarCondicion(ArbolAbs exp){
	pilaCondiciones.add(exp);
}

private ArbolAbs getUltimaCondicion(){
	ArbolAbs salida = pilaCondiciones.lastElement();
	pilaCondiciones.remove(pilaCondiciones.size() - 1);
	return salida;
}

//Esto funciona sin anidaciones
/*
private void apilar(ArbolAbs exp){
	if (bloqueActivado > 0){
			if ( pila.size() < bloqueActivado ){
				pila.add(crear_nodo("Sentencia General",exp,null));
			}else{
				//Buscar el mas derecho
				SentenciasArbolMasDerecho = ((Arbol) pila.elementAt(bloqueActivado - 1));
				while (((Arbol) SentenciasArbolMasDerecho).getDerecho() != null){
					SentenciasArbolMasDerecho = ((Arbol) SentenciasArbolMasDerecho).getDerecho();
				}
				//Tengo al mas derecho
				((Arbol) SentenciasArbolMasDerecho).setDerecho(crear_nodo("Sentencia General",exp,null));
			}
			Bloque = pila.elementAt(bloqueActivado - 1);
	}
}
*/


private void aumentarNivel(){
	nivelActual++;
}

private void decrementarNivel(){
	//Borramos todo lo referido a ese nivel
	while( ! pilaNivel.isEmpty() && pilaNivel.lastElement() == nivelActual && ! pilaBloques.isEmpty()){
		pilaBloques.remove(pilaBloques.size() - 1);
		pilaNivel.remove(pilaNivel.size() - 1);
	}
	nivelActual--;
}

private void apilar(ArbolAbs exp){
	if (nivelActual == 0){
		//No estamos dentro de un bloque
		addSentencia(exp);
	}
	pilaBloques.add(exp);
	pilaNivel.add(nivelActual);
}

private ArbolAbs desapilar(){
	ArbolAbs salida;
	ArbolAbs intermedio;
	Vector<ArbolAbs> objetivos = new Vector<ArbolAbs>();
	
	//Obtenemos los arboles a componer
	for (int i = 0; i < pilaNivel.size(); i++){
		if (pilaNivel.elementAt(i).equals(nivelActual)){
			objetivos.add(pilaBloques.elementAt(i));
			pilaBloques.setElementAt(null,i);
		}
	}
	//Desapilamos esos arboles
	while( ! pilaBloques.isEmpty() && pilaBloques.lastElement() == null){
		pilaBloques.remove(pilaBloques.size() - 1);
		pilaNivel.remove(pilaNivel.size() - 1);
	}

	//Se crea la estructura compuesta
	salida = crear_nodo("Sentencia General",objetivos.firstElement(),null);
	intermedio = salida;
	objetivos.remove(0);
	for ( ArbolAbs arbol : objetivos ){
		ArbolAbs aux = crear_nodo("Sentencia General",arbol,null);
		((Arbol) ((Arbol) intermedio).dameMasDerecho()).setDerecho(aux);
		intermedio = arbol;
	}
	
	//Guardamos el arbol generado
	pilaBloques.add(salida);
	pilaNivel.add(nivelActual);
	
	return salida;
}

