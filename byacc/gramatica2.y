%{
import gui.ConsolaManager;
import lexico.AnalizadorLexico;
import proyecto.Proyecto;
%}

%token IDENTIFICADOR ENTERO ENTERO_LSS CADENA_MULTILINEA PR_SI PR_ENTONCES PR_SINO PR_IMPRIMIR PR_ENTERO PR_ENTERO_LSS	PR_ITERAR PR_HASTA PR_VECTOR PR_DE COMP_MAYOR_IGUAL COMP_MENOR_IGUAL COMP_DISTINTO ASIGNACION PUNTO_PUNTO FUERA_RANGO
	
%left '-' '+'
%left '*' '/'
%left NEG
%right PR_ENTONCES
%right PR_SINO

%%
programa	: sent_declarativa sent_ejecutable
			;
		
sent_declarativa 	: sent_declarativa declaracion
					| 
					; 

declaracion : tipo_dato lista_variables ';' { indicarSentencia("Declaración de tipo básico"); } 
				| IDENTIFICADOR '[' ENTERO PUNTO_PUNTO ENTERO ']' PR_VECTOR PR_DE tipo_dato ';' { indicarSentencia("Declaración de tipo vector"); }
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
					
sentencia	:  { indicarSentencia("Selección");} seleccion
			|  { indicarSentencia("Iteración");} iteracion ';'  				
			|  { indicarSentencia("Impresión");} impresion ';'  				
			|  asignacion ';'  { indicarSentencia("Asignación");}			
			;
			
seleccion	: PR_SI '(' condicion ')' PR_ENTONCES bloque									
			| PR_SI '(' condicion ')' PR_ENTONCES bloque PR_SINO bloque	
			/*| PR_SI condicion ')' error ';'											{escribirError("Falta '(' en sentencia si.");}
			| PR_SI '(' ')' error ';'												{escribirError("Falta condición en sentencia si.");}
			| PR_SI '(' condicion PR_ENTONCES error ';'								{escribirError("Falta ')' en sentencia si.");}
			| error ';'																{escribirError("Sentencia si mal escrita.");}
			*/;
			
iteracion	: PR_ITERAR bloque PR_HASTA condicion
			;
			
impresion	: PR_IMPRIMIR  '(' CADENA_MULTILINEA ')' 
			;
			
asignacion	: asignable ASIGNACION e
			;

bloque		: sentencia
			| '{' sent_ejecutable '}'
			;
					
condicion	: e comparador e
			| error ';' 			{escribirError("Condición inválida.");}
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
	ConsolaManager.getInstance().escribirError("Sintáctico [Línea " + lineaActual() + "] "+ s);
}

private void indicarSentencia(String s){
	proyecto.addSentenciaToList("Línea " +lineaActual()+ " -> " + s + ".");
}

private boolean chequearNegativo(Integer valor){
	//Chequear valor del negativo
	return false;
}

public Parser(Proyecto p){
	this.proyecto = p;
}
