package generaciónASM;

import java.util.HashMap;

public class CodigoAssembler {

	String sentencias = new String();
	RegisterManager regManager = new RegisterManager(this);
	private HashMap<String,String> mapeoString = new HashMap<String,String>(); 
	
	public void agregarDeclaracionString(String cadena){
		//Se agrega el mapeo entre un string y su identificador en ASM
		mapeoString.put(cadena, "__str" + mapeoString.size());
	}
	
	public void agregarSentencia(String operacion, String op1, String op2){
		String sent = operacion + " " + op1 + ", " + op2;
		System.out.println("Agregar: " + sent);
		sentencias.concat(sent + "\n");
	}

	public RegisterManager getRegManager() {
		return regManager;
	}
	
}
