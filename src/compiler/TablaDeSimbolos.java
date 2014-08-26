package compiler;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import util.TablaDeSimbolosEntrada;
import util.Token.TipoToken;

public class TablaDeSimbolos {

	private Map<String, TablaDeSimbolosEntrada> tabla;
	
	public TablaDeSimbolos(){
		tabla = new HashMap<String, TablaDeSimbolosEntrada>();
		
		initPalabrasReservadas();
	}

	public boolean contieneLexema(String lexema) {
		return tabla.containsKey(lexema);
	}

	public void agregar(String lexema, TablaDeSimbolosEntrada entrada){
		tabla.put(lexema, entrada);
	}

	private void initPalabrasReservadas() {
		agregarPalabraReservada(TipoToken.PR_SI, "si");
		agregarPalabraReservada(TipoToken.PR_ENTONCES, "entonces");
		agregarPalabraReservada(TipoToken.PR_SINO, "sino");
		agregarPalabraReservada(TipoToken.PR_IMPRIMIR, "imprimir");
		agregarPalabraReservada(TipoToken.PR_ENTERO, "entero");
		agregarPalabraReservada(TipoToken.PR_ENTERO_LSS, "entero_lss");
		agregarPalabraReservada(TipoToken.PR_ITERAR, "iterar");
		agregarPalabraReservada(TipoToken.PR_HASTA, "hasta");
		agregarPalabraReservada(TipoToken.PR_VECTOR, "vector");
		agregarPalabraReservada(TipoToken.PR_DE, "de");
	}

	private void agregarPalabraReservada(TipoToken tipo, String lexema) {
		tabla.put(lexema, new TablaDeSimbolosEntrada(tipo, lexema, true));
	}

	public Vector<TablaDeSimbolosEntrada> getVector() {
		return new Vector<TablaDeSimbolosEntrada>(tabla.values());
	}
	
}
