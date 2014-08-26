package compiler;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import util.PalabraReservada;
import util.TablaDeSimbolosEntrada;
import util.Token.TipoToken;

public class TablaDeSimbolos {

	private Map<String, TablaDeSimbolosEntrada> tabla;
	private Vector<PalabraReservada> palabras;
	
	public TablaDeSimbolos(){
		tabla = new HashMap<String, TablaDeSimbolosEntrada>();
		palabras = new Vector<PalabraReservada>();
		initPalabrasReservadas();
	}

	public boolean contieneLexema(String lexema) {
		return tabla.containsKey(lexema);
	}

	public void agregar(String lexema, TablaDeSimbolosEntrada entrada){
		tabla.put(lexema, entrada);
	}

	private void initPalabrasReservadas() {
		palabras.add(new PalabraReservada(TipoToken.PR_SI, "si"));
		palabras.add(new PalabraReservada(TipoToken.PR_ENTONCES, "entonces"));
		//palabras.add(new PalabraReservada(TipoToken.PR_SI, "si"));
		//palabras.add(new PalabraReservada(TipoToken.PR_SI, "si"));
	}
	
}
