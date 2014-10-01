%{
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
programa	: sent_declarativa sent_ejecutable
			;
		
sent_declarativa 	: sent_declarativa declaracion
					| /* Sentencia declarativa vac�a */
					; 

declaracion 	: tipo_dato lista_variables ';' { indicarSentencia("Declaraci�n de tipo b�sico"); } 
				| IDENTIFICADOR '[' ENTERO PUNTO_PUNTO ENTERO ']' PR_VECTOR PR_DE tipo_dato ';' { indicarSentencia("Declaraci�n de tipo vector"); }
				;
			
lista_variables	: IDENTIFICADOR
				| lista_variables ',' IDENTIFICADOR
				;
			
tipo_dato	: PR_ENTERO
			| PR_ENTERO_LSS
			;
			
sent_ejecutable	: sentencia
				| sent_ejecutable sentencia 
				;
					
sentencia	:  { indicarSentencia("Selecci�n");} seleccion
			|  { indicarSentencia("Iteraci�n");} iteracion  				
			|  { indicarSentencia("Impresi�n");} impresion  				
			|  asignacion 
			;
			
seleccion	: PR_SI '(' condicion ')' PR_ENTONCES bloque									
			| PR_SI '(' condicion ')' PR_ENTONCES bloque PR_SINO bloque	
			/*| PR_SI condicion ')' error ';'										{escribirError("Falta '(' en sentencia si.");}
			| PR_SI '(' ')' error ';'												{escribirError("Falta condici�n en sentencia si.");}
			| PR_SI '(' condicion PR_ENTONCES error ';'								{escribirError("Falta ')' en sentencia si.");}
			| error ';'																{escribirError("Sentencia si mal escrita.");}
			*/;
			
iteracion	: PR_ITERAR bloque PR_HASTA condicion ';'
			;
			
impresion	: PR_IMPRIMIR  '(' CADENA_MULTILINEA ')' ';'
			| impresion_invalida
			;
			
impresion_invalida 	: PR_IMPRIMIR {escribirError("Falta '('.");} CADENA_MULTILINEA error';'
					| PR_IMPRIMIR '(' ')' {escribirError("No se especific� una cadena multil�nea.");} ';'
					| PR_IMPRIMIR '(' CADENA_MULTILINEA {escribirError("Falta ')'.");} ';' 
					| PR_IMPRIMIR error ';' {escribirError("Impresi�n Inv�lida cerca del ';'.");}
					| PR_IMPRIMIR ';' {escribirError("Falta \"('Cadena_Multilinea')\" cerca del ';'.");}
					;
			
asignacion	: asignable { indicarSentencia("Asignaci�n");} ASIGNACION e ';'
			| error ';' { escribirError("Asignaci�n inv�lida.");}
			;

bloque		: sentencia
			| '{' sent_ejecutable '}'
			;
					
condicion	: e comparador e
			/*| error PR_ENTONCES {escribirError("Condici�n inv�lida.");}
			| error  ';'  		{escribirError("Condici�n inv�lida.");}*/
			;
			
comparador	: '>'
			| COMP_MAYOR_IGUAL
			| '<'
			| COMP_MENOR_IGUAL
			| '='
			| COMP_DISTINTO
			;
			
e	: e '+' t			{ $$ = new ParserVal($1.ival + $3.ival); }
	| e '-' t			{ $$ = new ParserVal($1.ival - $3.ival); }
	| t					
	;
	
t	: t '*' f			{ $$ = new ParserVal($1.ival * $3.ival); }
	| t "/" f			{ $$ = new ParserVal($1.ival / $3.ival); }
	| f					
	;
	
f	: valor
	| '-' ENTERO %prec NEG	{ chequearNegativo(); }
	| '-' ENTERO_LSS %prec NEG { escribirError("Negativo malo");}
	/*| FUERA_RANGO error ';' {escribirError("Fuera Rango");} */ /*No lo ponemos ac� porque tira un par de errores..*/
	;

valor	: asignable
		| ENTERO		{ chequearRango(); $$ = new ParserVal(getValor($1.ival)); }
		| ENTERO_LSS
		;

asignable	: IDENTIFICADOR
			| IDENTIFICADOR '[' e ']'  
			;
			
%%

private Proyecto proyecto;

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
	ConsolaManager.getInstance().escribirError("Sint�ctico [L�nea " + lineaActual() + "] "+ s);
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

public int getValor(int pos){
	return Integer.parseInt(proyecto.getTablaDeSimbolos().getToken(pos).getLexema());
}

public void setValor(int pos, Integer valorNuevo){
	//
	
}