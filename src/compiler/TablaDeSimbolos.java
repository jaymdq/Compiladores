package compiler;

import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Vector;

import userInterface.ConsolaManager;
import util.TablaDeSimbolosEntrada;
import util.Token.TipoToken;

public class TablaDeSimbolos extends Observable {

	private Map<String, TablaDeSimbolosEntrada> tabla;
	
	public TablaDeSimbolos(){
		reset();
	}

	public boolean contieneLexema(String lexema) {
		return tabla.containsKey(lexema);
	}

	public void agregar(String lexema, TablaDeSimbolosEntrada entrada){
		ConsolaManager.getInstance().escribirInfo("[Lexico] Agregar entrada " + lexema);
		tabla.put(lexema, entrada);
		setChanged();
		notifyObservers();
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
		ConsolaManager.getInstance().escribirInfo("[Lexico] Agregar entrada " + lexema);
		tabla.put(lexema, new TablaDeSimbolosEntrada(tipo, lexema, true));
	}

	public Vector<TablaDeSimbolosEntrada> getVector() {
		return new Vector<TablaDeSimbolosEntrada>(tabla.values());
	}

	public void reset() {
		tabla = new HashMap<String, TablaDeSimbolosEntrada>();
		
		initPalabrasReservadas();
		
	}
	
}
