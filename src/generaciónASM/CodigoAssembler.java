package generaciónASM;

import java.util.HashMap;

public class CodigoAssembler {
	
	public enum Operacion { ADD, SUB, MUL, IMUL, DIV, IDIV, MOV};

	String sentencias = new String();
	RegisterManager regManager = new RegisterManager(this);
	private HashMap<String,String> mapeoString = new HashMap<String,String>(); 
	
	public void agregarDeclaracionString(String cadena){
		//Se agrega el mapeo entre un string y su identificador en ASM
		mapeoString.put(cadena, "__str" + mapeoString.size());
	}
	
	public void agregarSentencia(Operacion operacion, String op1, String op2){
		String sent = operacion + " " + op1 + ", " + op2;
		System.out.println("Agregar: " + sent);
		sentencias.concat(sent + "\n");
	}
	
	public void agregarSentencia(Operacion operacion, Registro r1, Registro r2){
		String sent = operacion + " " + r1 + ", " + r2;
		System.out.println("Agregar: " + sent);
		sentencias.concat(sent + "\n");
	}

	public RegisterManager getRegManager() {
		return regManager;
	}
	
}
