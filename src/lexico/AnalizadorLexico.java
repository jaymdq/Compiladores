package lexico;

import java.util.HashMap;

import lexico.MatrizTransicion.Estado;
import lexico.as.ASDirecto;
import lexico.as.AS_CTE_Finish;
import lexico.as.AS_CTE_Start;
import lexico.as.AS_Caret_Unexpected;
import lexico.as.AS_Chain_Finish;
import lexico.as.AS_Chain_Start;
import lexico.as.AS_Chain_Unexpected;
import lexico.as.AS_Colon_Unexpected;
import lexico.as.AS_Continue;
import lexico.as.AS_Dot_Expected;
import lexico.as.AS_ID_Finish;
import lexico.as.AS_ID_Start;
import lexico.as.AS_Invalid_Finish;
import lexico.as.AS_Invalid_Start;
import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Simbolo.TipoSimbolo;
import proyecto.Token;
import proyecto.Token.TipoToken;
import sintactico.ParserVal;

public class AnalizadorLexico {

	private static MatrizTransicion matrizTransicion = null;
	private static HashMap<String,TipoToken> palabrasReservadas = new HashMap<String,TipoToken>();
	private static Proyecto proyecto;
	public static int cantErrores = 0;
	public static ParserVal yylval = null;

	public static int yylex(){
		// Se verifica si el archivo ya termino
		if (proyecto.isEOF()){
			proyecto.addTokenToList(new Token(Token.TipoToken.FIN_ARCHIVO,Token.TipoToken.FIN_ARCHIVO.toString()));
			return Token.TipoToken.FIN_ARCHIVO.getValor();
		}

		// Inicializamos el estado y el token
		Estado estado = Estado.INICIAL;
		Token token = new Token();

		// Mientras el achivo no termine y no nos encontremos en el estado final
		while ( ( estado != Estado.FINAL ) ) {
			// Obtengo el siguiente caracter y creo su simbolo
			char caracter = proyecto.getNextCaracter();
			Simbolo simbolo = new Simbolo(caracter);

			// Obtengo la transicion correspondiente de la matriz y la ejecuto
			Transicion transicion = matrizTransicion.getTransicion(estado,simbolo);
			estado = transicion.getNuevoEstado();
			transicion.ejecutarAccionSemantica(token,simbolo,proyecto);

			// Si el simbolo es una nueva linea, avanzo de linea en el proyecto
			if ( simbolo.getTipo().equals(Simbolo.TipoSimbolo.NUEVA_LINEA) )
				proyecto.avanzarLinea();
		}

		return token.getTipo().getValor();
	}

	public static void prepare(Proyecto p) {
		init();
		proyecto = p;
		setPalabrasReservadas();
		p.getTablaDeSimbolos().clear();
	}

	public static void setProyecto(Proyecto p){
		proyecto = p;
	}

	private static void setPalabrasReservadas() {
		palabrasReservadas.put("si", TipoToken.PR_SI);
		palabrasReservadas.put("entonces", TipoToken.PR_ENTONCES);
		palabrasReservadas.put("sino", TipoToken.PR_SINO);
		palabrasReservadas.put("imprimir", TipoToken.PR_IMPRIMIR);
		palabrasReservadas.put("entero", TipoToken.PR_ENTERO);
		palabrasReservadas.put("entero_lss", TipoToken.PR_ENTERO_LSS);
		palabrasReservadas.put("iterar", TipoToken.PR_ITERAR);
		palabrasReservadas.put("hasta", TipoToken.PR_HASTA);
		palabrasReservadas.put("vector", TipoToken.PR_VECTOR);
		palabrasReservadas.put("de", TipoToken.PR_DE);
	}

	public static boolean isPalabraReservada(String lexema){
		return palabrasReservadas.containsKey(lexema.toLowerCase());
	}

	public static  void setearTokenAPalabraReservada(Token t){
		t.setTipo(palabrasReservadas.get(t.getLexema().toLowerCase()));
		t.setReservado(true);
	}

	private static void init() {
		if (matrizTransicion == null) {
			matrizTransicion = new MatrizTransicion();

			// Estado Inicial
			// Complejos
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.LETRA, new Transicion(Estado.UNO, new AS_ID_Start()));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.DIGITO, new Transicion(Estado.DOS, new AS_CTE_Start()));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.COMILLA, new Transicion(Estado.NUEVE, new AS_Chain_Start()));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.ASTERISCO, new Transicion(Estado.SIETE));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.DOS_PUNTOS, new Transicion(Estado.TRES));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.MAYOR, new Transicion(Estado.CUATRO));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.MENOR, new Transicion(Estado.CINCO));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.ACENTO_CIRCUNFLEJO, new Transicion(Estado.SEIS));

			// Simples
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.MAS, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.OP_MAS,false)));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.MENOS, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.OP_MENOS,false)));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.BARRA, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.OP_DIVIDIDO,false)));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.IGUAL, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.COMP_IGUAL,false)));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.PARENTESIS_ABIERTO, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.PARENTESIS_ABIERTO,false)));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.PARENTESIS_CERRADO, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.PARENTESIS_CERRADO,false)));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.LLAVE_ABIERTA, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.LLAVE_ABIERTA,false)));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.LLAVE_CERRADA, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.LLAVE_CERRADA,false)));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.COMA, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.COMA,false)));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.PUNTO_Y_COMA, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.PUNTO_Y_COMA,false)));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.CORCHETE_ABIERTO, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.CORCHETE_ABIERTO,false)));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.CORCHETE_CERRADO, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.CORCHETE_CERRADO,false)));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.FIN_ARCHIVO, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.FIN_ARCHIVO,false)));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.PUNTO, new Transicion(Estado.ONCE, new AS_Continue()));
			
			// Ignorados
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.NUEVA_LINEA, new Transicion(Estado.INICIAL));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.TABULACION, new Transicion(Estado.INICIAL));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.RETORNO, new Transicion(Estado.INICIAL));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.BLANCO, new Transicion(Estado.INICIAL));
			// Default
			matrizTransicion.setDefault(Estado.INICIAL, new Transicion(Estado.TRECE, new AS_Invalid_Start()));

			// Estado 1
			// Continuar
			matrizTransicion.setTransicion(Estado.UNO, TipoSimbolo.LETRA, new Transicion(Estado.UNO, new AS_Continue()));
			matrizTransicion.setTransicion(Estado.UNO, TipoSimbolo.DIGITO, new Transicion(Estado.UNO, new AS_Continue()));
			matrizTransicion.setTransicion(Estado.UNO, TipoSimbolo.GUION_BAJO, new Transicion(Estado.UNO, new AS_Continue()));
			matrizTransicion.setTransicion(Estado.UNO, TipoSimbolo.AMPERSAND, new Transicion(Estado.UNO, new AS_Continue()));
			// Finalización normal
			matrizTransicion.setTransicion(Estado.UNO, TipoSimbolo.BLANCO, new Transicion(Estado.FINAL, new AS_ID_Finish(false)));
			matrizTransicion.setTransicion(Estado.UNO, TipoSimbolo.NUEVA_LINEA, new Transicion(Estado.FINAL, new AS_ID_Finish(false)));
			matrizTransicion.setTransicion(Estado.UNO, TipoSimbolo.TABULACION, new Transicion(Estado.FINAL, new AS_ID_Finish(false)));
			matrizTransicion.setTransicion(Estado.UNO, TipoSimbolo.RETORNO, new Transicion(Estado.FINAL, new AS_ID_Finish(false)));
			// Simbolo invalido
			matrizTransicion.setTransicion(Estado.UNO, TipoSimbolo.INVALIDO, new Transicion(Estado.TRECE, new AS_Invalid_Start()));
			// Default
			matrizTransicion.setDefault(Estado.UNO, new Transicion(Estado.FINAL, new AS_ID_Finish(true)));

			// Estado 2
			// Continuar
			matrizTransicion.setTransicion(Estado.DOS, TipoSimbolo.DIGITO, new Transicion(Estado.DOS, new AS_Continue()));
			// Simbolo invalido
			matrizTransicion.setTransicion(Estado.DOS, TipoSimbolo.AMPERSAND, new Transicion(Estado.TRECE, new AS_Invalid_Start()));
			matrizTransicion.setTransicion(Estado.DOS, TipoSimbolo.GUION_BAJO, new Transicion(Estado.TRECE, new AS_Invalid_Start()));
			matrizTransicion.setTransicion(Estado.DOS, TipoSimbolo.INVALIDO, new Transicion(Estado.TRECE, new AS_Invalid_Start()));
			matrizTransicion.setTransicion(Estado.DOS, TipoSimbolo.LETRA, new Transicion(Estado.TRECE, new AS_Invalid_Start()));
			// Finalización normal
			matrizTransicion.setTransicion(Estado.DOS, TipoSimbolo.BLANCO, new Transicion(Estado.FINAL, new  AS_CTE_Finish(false)));
			matrizTransicion.setTransicion(Estado.DOS, TipoSimbolo.NUEVA_LINEA, new Transicion(Estado.FINAL, new  AS_CTE_Finish(false)));
			matrizTransicion.setTransicion(Estado.DOS, TipoSimbolo.TABULACION, new Transicion(Estado.FINAL, new  AS_CTE_Finish(false)));
			matrizTransicion.setTransicion(Estado.DOS, TipoSimbolo.RETORNO, new Transicion(Estado.FINAL, new  AS_CTE_Finish(false)));	
			// Default
			matrizTransicion.setDefault(Estado.DOS, new Transicion(Estado.FINAL, new AS_CTE_Finish(true)));

			// Estado 3
			// Finalizacion
			matrizTransicion.setTransicion(Estado.TRES, TipoSimbolo.IGUAL, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.ASIGNACION,false)));
			// WARNING HERE --> COMO HACES PARA DEVOLVER ;
			// Default
			matrizTransicion.setDefault(Estado.TRES, new Transicion(Estado.INICIAL, new AS_Colon_Unexpected()));

			// Estado 4
			// Finalizacion >=
			matrizTransicion.setTransicion(Estado.CUATRO, TipoSimbolo.IGUAL, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.COMP_MAYOR_IGUAL,false)));
			// Finalizacion sin back
			matrizTransicion.setTransicion(Estado.CUATRO, TipoSimbolo.BLANCO, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.COMP_MAYOR,false)));
			matrizTransicion.setTransicion(Estado.CUATRO, TipoSimbolo.TABULACION, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.COMP_MAYOR,false)));
			matrizTransicion.setTransicion(Estado.CUATRO, TipoSimbolo.NUEVA_LINEA, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.COMP_MAYOR,false)));
			matrizTransicion.setTransicion(Estado.CUATRO, TipoSimbolo.RETORNO, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.COMP_MAYOR,false)));
			// Finalizacion >
			matrizTransicion.setDefault(Estado.CUATRO, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.COMP_MAYOR, true)));

			// Estado 5
			// Finalizacion <=
			matrizTransicion.setTransicion(Estado.CINCO, TipoSimbolo.IGUAL, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.COMP_MENOR_IGUAL,false)));
			// Finalizacion sin back	
			matrizTransicion.setTransicion(Estado.CINCO, TipoSimbolo.BLANCO, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.COMP_MENOR,false)));
			matrizTransicion.setTransicion(Estado.CINCO, TipoSimbolo.TABULACION, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.COMP_MENOR,false)));
			matrizTransicion.setTransicion(Estado.CINCO, TipoSimbolo.NUEVA_LINEA, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.COMP_MENOR,false)));
			matrizTransicion.setTransicion(Estado.CINCO, TipoSimbolo.RETORNO, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.COMP_MENOR,false)));
			// Finalizacion <
			matrizTransicion.setDefault(Estado.CINCO, new Transicion(Estado.FINAL, new ASDirecto( TipoToken.COMP_MENOR, true)));

			// Estado 6
			// Finalizacion ^=
			matrizTransicion.setTransicion(Estado.SEIS, TipoSimbolo.IGUAL, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.COMP_DISTINTO,false)));
			// WARNING HERE --> COMO HACES PARA DEVOLVER OTRA COSA
			// Default
			matrizTransicion.setDefault(Estado.SEIS, new Transicion(Estado.INICIAL, new AS_Caret_Unexpected()));

			// Estado 7
			// Inicia comentario
			matrizTransicion.setTransicion(Estado.SIETE, TipoSimbolo.ASTERISCO, new Transicion(Estado.OCHO));
			// Finaliza sin back
			matrizTransicion.setTransicion(Estado.SIETE, TipoSimbolo.BLANCO, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.OP_POR,false)));
			matrizTransicion.setTransicion(Estado.SIETE, TipoSimbolo.TABULACION, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.OP_POR,false)));
			matrizTransicion.setTransicion(Estado.SIETE, TipoSimbolo.NUEVA_LINEA, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.OP_POR,false)));
			matrizTransicion.setTransicion(Estado.SIETE, TipoSimbolo.RETORNO, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.OP_POR,false)));
			// Default
			matrizTransicion.setDefault(Estado.SIETE, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.OP_POR, true)));

			// Estado 8
			// Finaliza comentario
			matrizTransicion.setTransicion(Estado.OCHO, TipoSimbolo.NUEVA_LINEA, new Transicion(Estado.INICIAL));
			matrizTransicion.setTransicion(Estado.OCHO, TipoSimbolo.RETORNO, new Transicion(Estado.INICIAL));
			// Fin inesperado del archivo
			matrizTransicion.setTransicion(Estado.OCHO, TipoSimbolo.FIN_ARCHIVO, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.FIN_ARCHIVO,false)));
			// Default
			matrizTransicion.setDefault(Estado.OCHO, new Transicion(Estado.OCHO));

			// Estado 9
			// Salto de linea
			matrizTransicion.setTransicion(Estado.NUEVE, TipoSimbolo.NUEVA_LINEA, new Transicion(Estado.DIEZ));
			matrizTransicion.setTransicion(Estado.NUEVE, TipoSimbolo.RETORNO, new Transicion(Estado.DIEZ));
			// Finaliza comentario
			matrizTransicion.setTransicion(Estado.NUEVE, TipoSimbolo.COMILLA, new Transicion(Estado.FINAL, new AS_Chain_Finish()));
			// Inesperado
			matrizTransicion.setTransicion(Estado.NUEVE, TipoSimbolo.FIN_ARCHIVO, new Transicion(Estado.INICIAL, new AS_Chain_Unexpected()));
			// Default
			matrizTransicion.setDefault(Estado.NUEVE, new Transicion(Estado.NUEVE, new AS_Continue()));

			// Estado 10
			// Decartado de blancos
			matrizTransicion.setTransicion(Estado.DIEZ, TipoSimbolo.BLANCO, new Transicion(Estado.DIEZ));
			matrizTransicion.setTransicion(Estado.DIEZ, TipoSimbolo.TABULACION, new Transicion(Estado.DIEZ));
			matrizTransicion.setTransicion(Estado.DIEZ, TipoSimbolo.NUEVA_LINEA, new Transicion(Estado.DIEZ));
			matrizTransicion.setTransicion(Estado.DIEZ, TipoSimbolo.RETORNO, new Transicion(Estado.DIEZ));
			// Aparicion del +
			matrizTransicion.setTransicion(Estado.DIEZ, TipoSimbolo.MAS, new Transicion(Estado.NUEVE));
			// Inesperado
			matrizTransicion.setDefault(Estado.DIEZ, new Transicion(Estado.INICIAL, new AS_Chain_Unexpected()));

			// Estado 11
			matrizTransicion.setTransicion(Estado.ONCE, TipoSimbolo.PUNTO, new Transicion(Estado.FINAL,new ASDirecto(TipoToken.PUNTO_PUNTO,false)));
			// Descartados de blancos
			matrizTransicion.setTransicion(Estado.ONCE, TipoSimbolo.BLANCO, new Transicion(Estado.INICIAL, new AS_Dot_Expected(false) ));
			matrizTransicion.setTransicion(Estado.ONCE, TipoSimbolo.TABULACION, new Transicion(Estado.INICIAL, new AS_Dot_Expected(false)));
			matrizTransicion.setTransicion(Estado.ONCE, TipoSimbolo.NUEVA_LINEA, new Transicion(Estado.INICIAL, new AS_Dot_Expected(false)));
			matrizTransicion.setTransicion(Estado.ONCE, TipoSimbolo.RETORNO, new Transicion(Estado.INICIAL, new AS_Dot_Expected(false)));
			// Default
			matrizTransicion.setDefault(Estado.ONCE, new Transicion(Estado.INICIAL, new AS_Dot_Expected(true)));

			// Estado 13
			// Sigue token
			matrizTransicion.setTransicion(Estado.TRECE, TipoSimbolo.LETRA, new Transicion(Estado.TRECE, new AS_Continue()));
			matrizTransicion.setTransicion(Estado.TRECE, TipoSimbolo.DIGITO, new Transicion(Estado.TRECE, new AS_Continue()));
			matrizTransicion.setTransicion(Estado.TRECE, TipoSimbolo.AMPERSAND, new Transicion(Estado.TRECE, new AS_Continue()));
			matrizTransicion.setTransicion(Estado.TRECE, TipoSimbolo.GUION_BAJO, new Transicion(Estado.TRECE, new AS_Continue()));
			matrizTransicion.setTransicion(Estado.TRECE, TipoSimbolo.INVALIDO, new Transicion(Estado.TRECE, new AS_Continue()));
			// Finalizado sin back
			matrizTransicion.setTransicion(Estado.TRECE, TipoSimbolo.BLANCO, new Transicion(Estado.INICIAL, new AS_Invalid_Finish(false)));
			matrizTransicion.setTransicion(Estado.TRECE, TipoSimbolo.TABULACION, new Transicion(Estado.INICIAL, new AS_Invalid_Finish(false)));
			matrizTransicion.setTransicion(Estado.TRECE, TipoSimbolo.NUEVA_LINEA, new Transicion(Estado.INICIAL, new AS_Invalid_Finish(false)));
			matrizTransicion.setTransicion(Estado.TRECE, TipoSimbolo.RETORNO, new Transicion(Estado.INICIAL, new AS_Invalid_Finish(false)));
			// Finalizado con back
			matrizTransicion.setDefault(Estado.TRECE, new Transicion(Estado.INICIAL, new AS_Invalid_Finish(true)));

		}
	}

}
