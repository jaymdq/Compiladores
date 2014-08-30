package lexico;

import lexico.Transicion;
import proyecto.Simbolo.TipoSimbolo;
import lexico.as.AS1;
import lexico.as.AS2;
import lexico.as.AS3;
import lexico.as.AS4;
import lexico.as.AS5;
import lexico.as.AS6;
import lexico.as.AS7;
import lexico.as.ASDirecto;
import proyecto.Token.TipoToken;
import lexico.MatrizTransicion.Estado;
import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.TablaDeSimbolos;
import proyecto.Token;

public class AnalizadorLexico {

	private static MatrizTransicion mt = null;

	public static int getToken(Proyecto p){
	
		Estado e = Estado.INICIAL;
		Token t = new Token();
		
		while ( (!p.isEOF()) && (e != Estado.FINAL) ) {
			char c = p.getNext();
			Simbolo s = new Simbolo(c);
			Transicion tr = mt.getTransicion(e,s);
			e = tr.getNuevoEstado();
			tr.ejecutarAccionSemantica(t,s,p);
		}
		if (e == Estado.FINAL) {
			if (t.getLexema() == "")
				return getToken(p);
			return p.addToken(t);
		}
		return -1;
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
		if (mt == null) {
			mt = new MatrizTransicion();
			
			// Descartables
			mt.setTransicion(Estado.INICIAL, TipoSimbolo.BLANCO, new Transicion(Estado.INICIAL));
			mt.setTransicion(Estado.INICIAL, TipoSimbolo.TABULACION, new Transicion(Estado.INICIAL));
			mt.setTransicion(Estado.INICIAL, TipoSimbolo.NUEVA_LINEA, new Transicion(Estado.INICIAL));
			mt.setTransicion(Estado.INICIAL, TipoSimbolo.RETORNO, new Transicion(Estado.INICIAL));
			
			// Directos al final
			mt.setTransicion(Estado.INICIAL, TipoSimbolo.MAS, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.OP_MAS,false)));
			mt.setTransicion(Estado.INICIAL, TipoSimbolo.MENOS, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.OP_MENOS,false)));
			mt.setTransicion(Estado.INICIAL, TipoSimbolo.BARRA, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.OP_DIVIDIDO,false)));
			mt.setTransicion(Estado.INICIAL, TipoSimbolo.IGUAL, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.COMP_IGUAL,false)));
			mt.setTransicion(Estado.INICIAL, TipoSimbolo.PARENTESIS_ABIERTO, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.PARENTESIS_ABIERTO,false)));
			mt.setTransicion(Estado.INICIAL, TipoSimbolo.PARENTESIS_CERRADO, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.PARENTESIS_CERRADO,false)));
			mt.setTransicion(Estado.INICIAL, TipoSimbolo.LLAVE_ABIERTA, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.LLAVE_ABIERTA,false)));
			mt.setTransicion(Estado.INICIAL, TipoSimbolo.LLAVE_CERRADA, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.LLAVE_CERRADA,false)));
			mt.setTransicion(Estado.INICIAL, TipoSimbolo.COMA, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.COMA,false)));
			mt.setTransicion(Estado.INICIAL, TipoSimbolo.PUNTO_Y_COMA, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.PUNTO_Y_COMA,false)));
			mt.setTransicion(Estado.INICIAL, TipoSimbolo.CORCHETE_ABIERTO, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.CORCHETE_ABIERTO,false)));
			mt.setTransicion(Estado.INICIAL, TipoSimbolo.CORCHETE_CERRADO, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.CORCHETE_CERRADO,false)));
			mt.setTransicion(Estado.INICIAL, TipoSimbolo.FIN_ARCHIVO, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.FIN_ARCHIVO,false)));
			
			// Indirectos
			mt.setTransicion(Estado.INICIAL, TipoSimbolo.LETRA, new Transicion(Estado.UNO, new AS1()));
			mt.setTransicion(Estado.INICIAL, TipoSimbolo.DIGITO, new Transicion(Estado.DOS, new AS4()));
			mt.setTransicion(Estado.INICIAL, TipoSimbolo.DOS_PUNTOS, new Transicion(Estado.TRES));
			mt.setTransicion(Estado.INICIAL, TipoSimbolo.MAYOR, new Transicion(Estado.CUATRO));
			mt.setTransicion(Estado.INICIAL, TipoSimbolo.MENOR, new Transicion(Estado.CINCO));
			mt.setTransicion(Estado.INICIAL, TipoSimbolo.ACENTO_CIRCUNFLEJO, new Transicion(Estado.SEIS));
			mt.setTransicion(Estado.INICIAL, TipoSimbolo.ASTERISCO, new Transicion(Estado.SIETE));
			mt.setTransicion(Estado.INICIAL, TipoSimbolo.COMILLA, new Transicion(Estado.NUEVE, new AS6()));
			
			// Estado 1
			mt.setTransicion(Estado.UNO, TipoSimbolo.LETRA, new Transicion(Estado.UNO, new AS2()));
			mt.setTransicion(Estado.UNO, TipoSimbolo.DIGITO, new Transicion(Estado.UNO, new AS2()));
			mt.setTransicion(Estado.UNO, TipoSimbolo.GUION_BAJO, new Transicion(Estado.UNO, new AS2()));
			mt.setTransicion(Estado.UNO, TipoSimbolo.AMPERSAND, new Transicion(Estado.UNO, new AS2()));
			mt.setDefault(Estado.UNO, new Transicion(Estado.FINAL, new AS3()));
			
			// Estado 2
			mt.setTransicion(Estado.DOS, TipoSimbolo.DIGITO, new Transicion(Estado.DOS, new AS2()));
			mt.setDefault(Estado.DOS, new Transicion(Estado.FINAL, new AS5()));
			
			// Estado 3
			mt.setTransicion(Estado.TRES, TipoSimbolo.IGUAL, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.ASIGNACION,false)));
			
			// Estado 4
			mt.setTransicion(Estado.CUATRO, TipoSimbolo.IGUAL, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.COMP_MAYOR_IGUAL,false)));
			mt.setDefault(Estado.CUATRO, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.COMP_MAYOR, true)));
			
			// Estado 5
			mt.setTransicion(Estado.CINCO, TipoSimbolo.IGUAL, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.COMP_MENOR_IGUAL,false)));
			mt.setDefault(Estado.CINCO, new Transicion(Estado.FINAL, new ASDirecto( TipoToken.COMP_MENOR, true)));
			
			// Estado 6
			mt.setTransicion(Estado.SEIS, TipoSimbolo.IGUAL, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.COMP_DISTINTO,false)));
			
			// Estado 7
			mt.setTransicion(Estado.SIETE, TipoSimbolo.ASTERISCO, new Transicion(Estado.OCHO));
			mt.setDefault(Estado.SIETE, new Transicion(Estado.FINAL, new ASDirecto(TipoToken.OP_POR, true)));
			
			// Estado 8
			mt.setTransicion(Estado.OCHO, TipoSimbolo.NUEVA_LINEA, new Transicion(Estado.INICIAL));
			mt.setTransicion(Estado.OCHO, TipoSimbolo.RETORNO, new Transicion(Estado.INICIAL));
			mt.setDefault(Estado.OCHO, new Transicion(Estado.OCHO));
			
			// Estado 9
			mt.setTransicion(Estado.NUEVE, TipoSimbolo.NUEVA_LINEA, new Transicion(Estado.DIEZ));
			mt.setTransicion(Estado.NUEVE, TipoSimbolo.RETORNO, new Transicion(Estado.DIEZ));
			mt.setTransicion(Estado.NUEVE, TipoSimbolo.COMILLA, new Transicion(Estado.FINAL, new AS7()));
			mt.setDefault(Estado.NUEVE, new Transicion(Estado.NUEVE, new AS2()));
			
			// Estado 10
			mt.setTransicion(Estado.DIEZ, TipoSimbolo.BLANCO, new Transicion(Estado.DIEZ));
			mt.setTransicion(Estado.DIEZ, TipoSimbolo.TABULACION, new Transicion(Estado.DIEZ));
			mt.setTransicion(Estado.DIEZ, TipoSimbolo.NUEVA_LINEA, new Transicion(Estado.DIEZ));
			mt.setTransicion(Estado.DIEZ, TipoSimbolo.RETORNO, new Transicion(Estado.DIEZ));
			mt.setTransicion(Estado.DIEZ, TipoSimbolo.MAS, new Transicion(Estado.NUEVE));
		}
	}

}
