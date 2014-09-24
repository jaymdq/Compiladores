%token IDENTIFICADOR ENTERO ENTERO_LSS CADENA_MULTILINEA PR_SI PR_ENTONCES PR_SINO PR_IMPRIMIR PR_ENTERO PR_ENTERO_LSS	PR_ITERAR PR_HASTA PR_VECTOR PR_DE COMP_MAYOR_IGUAL COMP_MENOR_IGUAL COMP_DISTINTO ASIGNACION PUNTO_PUNTO
	
%left '-' '+'
%left '*' '/'
%left NEG
%right PR_ENTONCES
%right PR_SINO

%%
programa	: sent_declarativa sent_ejecutable
			;
		
sent_declarativa	: tipo_dato lista_variables ';'																	{ indicarSentencia("Declaraci�n de tipo b�sico");}														
					| IDENTIFICADOR'\['ENTERO PUNTO_PUNTO ENTERO'\]' PR_VECTOR PR_DE tipo_dato ';'					{ indicarSentencia("Declaraci�n de tipo vector");}				
					| sent_declarativa tipo_dato lista_variables ';'												{ indicarSentencia("Declaraci�n de lista de tipo b�sico");}				
					| sent_declarativa IDENTIFICADOR'['ENTERO PUNTO_PUNTO ENTERO']' PR_VECTOR PR_DE tipo_dato ';'	{ indicarSentencia("Declaraci�n de lista de tipo vector");}
					;
			
lista_variables	: IDENTIFICADOR
				| lista_variables ',' IDENTIFICADOR
				;
			
tipo_dato	: PR_ENTERO
			| PR_ENTERO_LSS
			;
			
sent_ejecutable	: sentencia
					|  sentencia sent_ejecutable
					;
					
sentencia	:  { indicarSentencia("Selecci�n");} seleccion
			|  iteracion ';'  	{ indicarSentencia("Iteraci�n");}			
			|  impresion ';'  	{ indicarSentencia("Impresi�n");}			
			|  asignacion ';' 	{ indicarSentencia("Asignaci�n");}		
			;
			
seleccion	: PR_SI '(' condicion ')' PR_ENTONCES bloque									
			| PR_SI '(' condicion ')' PR_ENTONCES bloque PR_SINO bloque	
			;
			
iteracion	: PR_ITERAR bloque PR_HASTA condicion
			;
			
impresion	: PR_IMPRIMIR  '(' CADENA_MULTILINEA ')' 
			;
			
asignacion	: asignable ASIGNACION e
			;

bloque		: '{' sent_ejecutable '}'
			| sentencia 
			;
					
condicion	: e comparador e
			| error ';' 			{escribirError("Condici�n inv�lida.");}
			;
			
comparador	: '>'
			| COMP_MAYOR_IGUAL
			| '<'
			| COMP_MENOR_IGUAL
			| '='
			| COMP_DISTINTO
			;
			
e	: e '+' t			{ $$.ival = $1.ival + $3.ival; }
	| e '-' t			{ $$.ival = $1.ival - $3.ival; }
	| t					{ $$.ival = $1.ival; }
	| '-' e %prec NEG	{ chequearNegativo($2.ival); $$.ival = -$2.ival; }
	;
	
t	: t '*' f			{ $$.ival = $1.ival * $3.ival; }
	| t "/" f			{ $$.ival = $1.ival / $3.ival; }
	| f					{ $$.ival = $1.ival; }
	;
	
f	: asignable
	| ENTERO
	| ENTERO_LSS
	;
	
asignable	: IDENTIFICADOR
			| IDENTIFICADOR '[' e ']'
			| error ';'					{escribirError("Asignaci�n inv�lida.");}
			;
			
%%

private Proyecto proyecto;

private void yyerror(String string) {
	System.out.println(string);	
}

private int yylex(){
	return AnalizadorLexico.yylex();
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

private boolean chequearNegativo(Integer valor){
	//Chequear valor del negativo
}

public Parser(Proyecto p){
	this.proyecto = p;
}