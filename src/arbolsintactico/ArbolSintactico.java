package arbolsintactico;

import java.util.HashMap;
import java.util.Vector;

//import proyecto.Token;

public class ArbolSintactico {

	private Vector<HashMap<String, Nodo>> tabla = new Vector<HashMap<String, Nodo>>();
	
	public void crearHoja(String clave, String valor){
		HashMap<String, Nodo> nuevo = (HashMap<String, Nodo>) tabla.lastElement().clone();
		nuevo.put(clave, new NodoHoja(valor));
		System.out.println(nuevo);
		tabla.add(nuevo);
	}
}
