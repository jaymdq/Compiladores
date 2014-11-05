package generaciónASM;

import generaciónASM.CodigoAssembler.Operacion;

import java.util.HashMap;
import java.util.Vector;

public class CodigoAssembler {
	
	public enum Operacion { ADD, SUB, MUL, IMUL, DIV, IDIV, MOV, INVOKE, JLE, JBE, CMP, JGE, JAE, JB, JL, JG, JA, JNE, JE, JMP};

	private String sentencias = new String();
	private RegisterManager regManager = new RegisterManager(this);
	private HashMap<String,String> mapeoString = new HashMap<String,String>(); 
	private Integer label = 0;
	private Vector<String> pilaEtiquetas = new Vector<String>();
	
	
	public void agregarDeclaracionString(String cadena){
		//Se agrega el mapeo entre un string y su identificador en ASM
		mapeoString.put(cadena, "__str" + mapeoString.size());
	}
	
	public void agregarSentencia(Operacion operacion, String op1, String op2){
		String sent = operacion + " " + op1 + ", " + op2;
		System.out.println("Agregar: " + sent);
		sentencias += sent + "\n";
	}
	
	public void agregarSentencia(Operacion operacion, Registro r1, Registro r2){
		String sent = operacion + " " + r1 + ", " + r2;
		System.out.println("Agregar: " + sent);
		sentencias += sent + "\n";
	}

	public RegisterManager getRegManager() {
		return regManager;
	}

	public void agregarSentencia(Operacion operacion, String op1) {
		String sent = operacion + " " + op1;
		System.out.println("Agregar: " + sent);
		sentencias += sent + "\n";		
	}
	
	public String getSentencias(){
		return sentencias;
	}

	public void agregarSentencia(Operacion operacion, String op1, Registro r1) {
		String sent = operacion + " " + op1 + ", " + r1;
		System.out.println("Agregar: " + sent);
		sentencias += sent + "\n";		
	}
	
	private String getEtiqueta(){
		String salida;
		salida = "label" + label;
		label++;
		return salida;
	}
	
	public String apilarEtiqueta(){
		String salida;
		salida = getEtiqueta();
		pilaEtiquetas.add(salida);
		return salida;
	}
	
	public void agregarEtiqueta(String etiqueta){
		sentencias += etiqueta + ":\n";
	}
	
	public String desapilarEtiqueta(){
		String salida;
		salida = pilaEtiquetas.lastElement(); 
		pilaEtiquetas.remove(pilaEtiquetas.size() - 1);
		return salida;
	}
}
