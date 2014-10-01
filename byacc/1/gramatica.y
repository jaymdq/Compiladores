%{

/* Imports necesarios en la clase Parser */
import gui.ConsolaManager;
import lexico.AnalizadorLexico;
import proyecto.Proyecto;
import proyecto.Token;

%}

%token IDENTIFICADOR ENTERO ENTERO_LSS CADENA_MULTILINEA PR_SI PR_ENTONCES PR_SINO PR_IMPRIMIR PR_ENTERO PR_ENTERO_LSS	PR_ITERAR PR_HASTA PR_VECTOR PR_DE COMP_MAYOR_IGUAL COMP_MENOR_IGUAL COMP_DISTINTO ASIGNACION PUNTO_PUNTO FUERA_RANGO
	
%left NEG
%right PR_ENTONCES
%right PR_SINO
%left IDENTIFICADOR

%%

/* Gramática */

programa	: sent_declarativa sent_ejecutable
			;
		
sent_declarativa 	: sent_declarativa declaracion
					| /* Sentencia declarativa vacía */
					; 

declaracion 	: tipo_dato lista_variables ';' 												{ indicarSentencia("Declaración de tipo básico"); } 
				| IDENTIFICADOR '[' ENTERO PUNTO_PUNTO ENTERO ']' PR_VECTOR PR_DE tipo_dato ';' { indicarSentencia("Declaración de tipo vector"); }
				;
			
lista_variables	: IDENTIFICADOR
				| lista_variables ',' IDENTIFICADOR
				;
			
tipo_dato	: PR_ENTERO
			| PR_ENTERO_LSS
			;
			
sent_ejecutable	: sentencia_valida
				| sent_ejecutable sentencia 
				;
					
sentencia_valida	:  { indicarSentencia("Selección");} seleccion
					|  { indicarSentencia("Iteración");} iteracion 				
					|  { indicarSentencia("Impresión");} impresion 				
					|  asignacion 
					;

sentencia 	:  sentencia_valida
			| declaracion { escribirError("No se pueden declarar variables luego de alguna sentencia ejecutable."); }  
			;
			
seleccion	: PR_SI '(' condicion ')' PR_ENTONCES bloque									
			| PR_SI '(' condicion ')' PR_ENTONCES bloque PR_SINO bloque	
			/*| PR_SI {escribirError("Falta '(' en sentencia si.");} condicion ')' error ';'										
			| PR_SI '(' ')' error ';'												{escribirError("Falta condición en sentencia si.");}
			| PR_SI '(' condicion PR_ENTONCES error ';'								{escribirError("Falta ')' en sentencia si.");}
			| error ';'																{escribirError("Sentencia si mal escrita.");}
			*/;
			
iteracion	: PR_ITERAR bloque PR_HASTA condicion ';'
			;
			
impresion	: PR_IMPRIMIR  '(' CADENA_MULTILINEA ')' ';'
			| impresion_invalida
			;
			
impresion_invalida 	: PR_IMPRIMIR {escribirError("Falta '('.");} CADENA_MULTILINEA error';'
					| PR_IMPRIMIR '(' ')' {escribirError("No se especificó una cadena multilínea.");} ';'
					| PR_IMPRIMIR '(' CADENA_MULTILINEA {escribirError("Falta ')'.");} ';' 
					| PR_IMPRIMIR error ';' {escribirError("Impresión Inválida cerca del ';'.");}
					| PR_IMPRIMIR ';' {escribirError("Falta \"('Cadena_Multilinea')\" cerca del ';'.");}
					;
			
asignacion	: asignable { indicarSentencia("Asignación");} ASIGNACION e ';'
			;

bloque		: sentencia
			| '{' sent_ejecutable '}'
			| '{' declaracion { escribirError("No se pueden declarar variables dentro de un bloque."); } sent_declarativa sent_ejecutable '}'
			| '{' '}'  { escribirError("Bloque de sentencias vacío."); }
			;
					
condicion	: e comparador e
			/*| error PR_ENTONCES {escribirError("Condición inválida.");}
			| error  ';'  		{escribirError("Condición inválida.");}*/
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
	| '-' ENTERO %prec NEG	{ chequearNegativo(); }
	| '-' ENTERO_LSS %prec NEG { escribirError("Negativo malo");}
	/*| FUERA_RANGO error ';' {escribirError("Fuera Rango");} */ /*No lo ponemos acá porque tira un par de errores..*/
	;

valor	: asignable
		| ENTERO		{ chequearRango(); }
		| ENTERO_LSS
		;

asignable	: IDENTIFICADOR
			| IDENTIFICADOR '[' e ']'  
			;
			
%%

/* Código */

private Proyecto proyecto;
private int errores = 0;

private void yyerror(String string) {
	System.out.println(string);	
}

private int yylex(){
	int salida = AnalizadorLexico.yylex();
	if (AnalizadorLexico.yylval != null){
		yylval = AnalizadorLexico.yylval;
		AnalizadorLexico.yylval = null;
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
		if (! proyecto.getTablaDeSimbolos().containsToken(tnuevo.getLexema()) )
			t.setLexema("-"+t.getLexema());
		else{
			proyecto.getTablaDeSimbolos().getToken(tnuevo.getLexema()).aumentarContador();
			proyecto.getTablaDeSimbolos().remove(t.getLexema());
		}
	}else{
		//Se crea otro token nuevo.
		t.disminuirContador();
		
		Token tnuevo = new Token(Token.TipoToken.ENTERO,"-"+t.getLexema());
		if (! proyecto.getTablaDeSimbolos().containsToken(tnuevo.getLexema()) )
			proyecto.getTablaDeSimbolos().add(tnuevo);
		else
			proyecto.getTablaDeSimbolos().getToken(tnuevo.getLexema()).aumentarContador();
	}
	//Se actualiza la tabla de simbolos visualmente.
	actualizarTablaDeSimbolos();
}

public void chequearRango(){
	Token t = proyecto.getTablaDeSimbolos().getToken(yylval.ival);
	if (Integer.parseInt(t.getLexema()) == 32768){
		t.setTipo(Token.TipoToken.ENTERO_LSS);
	}
	actualizarTablaDeSimbolos();
}

public void actualizarTablaDeSimbolos(){
	proyecto.getTablaDeSimbolos().setearCambios();
	proyecto.getTablaDeSimbolos().notifyObservers();
	for (Token tok : proyecto.getTablaDeSimbolos().getList()){
		proyecto.getTablaDeSimbolos().setearCambios();
		proyecto.getTablaDeSimbolos().notifyObservers(tok);
	}
}

public int getCantidadErrores(){
	return errores;
}
