package compiler;

import java.util.HashMap;
import java.util.Map;

import util.TablaDeSimbolosEntrada;

public class TablaDeSimbolos {

	private Map<String, TablaDeSimbolosEntrada> tabla;
	//private Vector<>
	
	public TablaDeSimbolos(){
		tabla = new HashMap<String, TablaDeSimbolosEntrada>();
	}
	
	public boolean contieneLexema(String lexema) {
		return tabla.containsKey(lexema);
	}

	public void agregar(String lexema, TablaDeSimbolosEntrada entrada){
		tabla.put(lexema, entrada);
	}
	
}
