%token IDENTIFICADOR ENTERO ENTERO_LSS CADENA_MULTILINEA PR_SI PR_ENTONCES PR_SINO PR_IMPRIMIR PR_ENTERO PR_ENTERO_LSS	PR_ITERAR PR_HASTA PR_VECTOR PR_DE COMP_MAYOR_IGUAL COMP_MENOR_IGUAL COMP_DISTINTO ASIGNACION 
		 
		 
		 
		
%left '-' '+'
%left '*' '/'

%%
programa	: sent_decl sent_ejec
			;
		
sent_decl	: tipo_dato lista_variables '\;'
			| IDENTIFICADOR'\['ENTERO '\..' ENTERO'\]' PR_VECTOR PR_DE tipo_dato '\;'
			| sent_decl tipo_dato lista_variables '\;'
			| sent_decl IDENTIFICADOR'\['ENTERO '\..' ENTERO'\]' PR_VECTOR PR_DE tipo_dato '\;'
			;
			
lista_variables	: IDENTIFICADOR
				| lista_variables '\,' IDENTIFICADOR
				;
			
tipo_dato	: PR_ENTERO
			| PR_ENTERO_LSS
			;
			
sent_ejec	: sentencia
			| sent_ejec sentencia
			;
			
sentencia	: seleccion '\;'
			| iteracion '\;'
			| impresion '\;'
			| asignacion '\;'
			;
			
seleccion	: PR_SI '\(' condicion '\)' PR_ENTONCES bloque
			| PR_SI '\(' condicion '\)' PR_ENTONCES bloque PR_SINO bloque
			;
			
iteracion	: PR_ITERAR bloque PR_HASTA condicion
			;
			
impresion	: PR_IMPRIMIR '\(' CADENA_MULTILINEA '\)'
			;
			
asignacion	: asignable ASIGNACION e
			;

bloque		: '\{' lista_sentencias '\}'

lista_sentencias	: sentencia
					| lista_sentencias sentencia
					;
					
condicion	: e comparador e
			;
			
comparador	: '\>'
			| COMP_MAYOR_IGUAL
			| '\<'
			| COMP_MENOR_IGUAL
			| '\='
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
	
f	: asignable
	| ENTERO
	| ENTERO_LSS
	;
	
asignable	: IDENTIFICADOR
			| IDENTIFICADOR '\[' e '\]'
			;