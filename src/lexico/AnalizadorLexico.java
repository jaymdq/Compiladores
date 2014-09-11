package lexico;

import lexico.MatrizTransicion.Estado;
import lexico.as.AS1;
import lexico.as.AS10;
import lexico.as.AS2;
import lexico.as.AS3;
import lexico.as.AS4;
import lexico.as.AS5;
import lexico.as.AS6;
import lexico.as.AS7;
import lexico.as.AS8;
import lexico.as.AS9;
import lexico.as.ASDirecto;
import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Simbolo.TipoSimbolo;
import proyecto.TablaDeSimbolos;
import proyecto.Token;
import proyecto.Token.TipoToken;

public class AnalizadorLexico {

	private static MatrizTransicion matrizTransicion = null;

	public static Token getToken(Proyecto proyecto){
		// Se verifica si el archivo ya termino
		if (proyecto.isEOF())
			return new Token(Token.TipoToken.FIN_ARCHIVO);
		
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

		return token;
	}

	public static void prepare(Proyecto p) {
		init();
		setSimbols(p.getTablaDeSimbolos());		
	}

	public static void setSimbols(TablaDeSimbolos t) {
		t.clear();
		t.add(new Token(TipoToken.PR_SI,"si",true));
		t.add(new Token(TipoToken.PR_ENTONCES, "entonces",true));
		t.add(new Token(TipoToken.PR_SINO, "sino",true));
		t.add(new Token(TipoToken.PR_IMPRIMIR, "imprimir",true));
		t.add(new Token(TipoToken.PR_ENTERO, "entero",true));
		t.add(new Token(TipoToken.PR_ENTERO_LSS, "entero_lss",true));
		t.add(new Token(TipoToken.PR_ITERAR, "iterar",true));
		t.add(new Token(TipoToken.PR_HASTA, "hasta",true));
		t.add(new Token(TipoToken.PR_VECTOR, "vector",true));
		t.add(new Token(TipoToken.PR_DE, "de",true));
	}

	private static void init() {
		if (matrizTransicion == null) {
			matrizTransicion = new MatrizTransicion();

			// Descartables
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.BLANCO, new Transicion(Estado.INICIAL));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.TABULACION, new Transicion(Estado.INICIAL));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.NUEVA_LINEA, new Transicion(Estado.INICIAL));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.RETORNO, new Transicion(Estado.INICIAL));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.INVALIDO, new Transicion(Estado.INICIAL,new AS9()));
			
			// Directos al final
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

			// Indirectos
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.LETRA, new Transicion(Estado.UNO, new AS1()));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.DIGITO, new Transicion(Estado.DOS, new AS4()));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.DOS_PUNTOS, new Transicion(Estado.TRES));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.MAYOR, new Transicion(Estado.CUATRO));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.MENOR, new Transicion(Estado.CINCO));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.ACENTO_CIRCUNFLEJO, new Transicion(Estado.SEIS));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.ASTERISCO, new Transicion(Estado.SIETE));
			matrizTransicion.setTransicion(Estado.INICIAL, TipoSimbolo.COMILLA, new Transicion(Estado.NUEVE, new AS6()));

			// Estado 1
			matrizTransicion.setTransicion(Estado.UNO, TipoSimbolo.LETRA, new Transicion(Estado.UNO, new AS2()));
			matrizTransicion.setTransicion(Estado.UNO, TipoSimbolo.DIGITO, new Transicion(Estado.UNO, new AS2()));
			matrizTransicion.setTransicion(Estado.UNO, TipoSimbolo.GUION_BAJO, new Transicion(Estado.UNO, new AS2()));
			matrizTransicion.setTransicion(Estado.UNO, TipoSimbolo.AMPERSAND, new Transicion(Estado.UNO, new AS2()));
			matrizTransicion.setDefault(Estado.UNO, new Transicion(Estado.FINAL, new AS3()));

			// Estado 2
			matrizTransicion.setTransicion(Estado.DOS, TipoSimbolo.DIGITO, new Transicion(Estado.DOS, new AS2()));
			matrizTransicion.setTransicion(Estado.DOS, TipoSimbolo.LETRA, new Transicion(Estado.INICIAL, new AS8()));
			matrizTransicion.setTransicion(Estado.DOS, TipoSimbolo.AMPERSAND, new Transicion(Estado.INICIAL, new AS8()));
			matrizTransicion.setTransicion(Estado.DOS, TipoSimbolo.GUION_BAJO, new Transicion(Estado.INICIAL, new AS8()));
			matrizTransicion.setDefault(Estado.DOS, new Transicion(Estado.FINAL, new AS5()));

			// Estado 3
			matrizTransicion.setTransicion(Estado.TRES, TipoSimbolo.IGUAL, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.ASIGNACION,false)));

			// Estado 4
			matrizTransicion.setTransicion(Estado.CUATRO, TipoSimbolo.IGUAL, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.COMP_MAYOR_IGUAL,false)));
			matrizTransicion.setDefault(Estado.CUATRO, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.COMP_MAYOR, true)));

			// Estado 5
			matrizTransicion.setTransicion(Estado.CINCO, TipoSimbolo.IGUAL, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.COMP_MENOR_IGUAL,false)));
			matrizTransicion.setDefault(Estado.CINCO, new Transicion(Estado.FINAL, new ASDirecto( TipoToken.COMP_MENOR, true)));

			// Estado 6
			matrizTransicion.setTransicion(Estado.SEIS, TipoSimbolo.IGUAL, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.COMP_DISTINTO,false)));

			// Estado 7
			matrizTransicion.setTransicion(Estado.SIETE, TipoSimbolo.ASTERISCO, new Transicion(Estado.OCHO));
			matrizTransicion.setDefault(Estado.SIETE, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.OP_POR, true)));

			// Estado 8
			matrizTransicion.setTransicion(Estado.OCHO, TipoSimbolo.NUEVA_LINEA, new Transicion(Estado.INICIAL));
			matrizTransicion.setTransicion(Estado.OCHO, TipoSimbolo.RETORNO, new Transicion(Estado.INICIAL));
			matrizTransicion.setTransicion(Estado.OCHO, TipoSimbolo.FIN_ARCHIVO, new Transicion(Estado.FINAL));
			matrizTransicion.setDefault(Estado.OCHO, new Transicion(Estado.OCHO));

			// Estado 9
			matrizTransicion.setTransicion(Estado.NUEVE, TipoSimbolo.NUEVA_LINEA, new Transicion(Estado.DIEZ));
			matrizTransicion.setTransicion(Estado.NUEVE, TipoSimbolo.RETORNO, new Transicion(Estado.DIEZ));
			matrizTransicion.setTransicion(Estado.NUEVE, TipoSimbolo.COMILLA, new Transicion(Estado.FINAL, new AS7()));
			matrizTransicion.setDefault(Estado.NUEVE, new Transicion(Estado.NUEVE, new AS2()));

			// Estado 10
			matrizTransicion.setTransicion(Estado.DIEZ, TipoSimbolo.BLANCO, new Transicion(Estado.DIEZ));
			matrizTransicion.setTransicion(Estado.DIEZ, TipoSimbolo.TABULACION, new Transicion(Estado.DIEZ));
			matrizTransicion.setTransicion(Estado.DIEZ, TipoSimbolo.NUEVA_LINEA, new Transicion(Estado.DIEZ));
			matrizTransicion.setTransicion(Estado.DIEZ, TipoSimbolo.RETORNO, new Transicion(Estado.DIEZ));
			matrizTransicion.setTransicion(Estado.DIEZ, TipoSimbolo.MAS, new Transicion(Estado.NUEVE));
			matrizTransicion.setDefault(Estado.DIEZ, new Transicion(Estado.INICIAL, new AS10()));

		}
	}

}
