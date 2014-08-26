package compiler;

import util.MatrizTransicion;
import util.Simbolo;
import util.Token;
import util.MatrizTransicion.Estado;

public class AnalizadorLexico {

	private Compilador comp;
	private Token tokenActual;

	public AnalizadorLexico(Compilador compilador) {
		super();
		this.comp = compilador;
	}
	
	public Token getToken(){
		ArchivoFuente fuente = comp.getArchivoFuente();
		MatrizTransicion matriz = comp.getMatrizTransicion();
		while (!matriz.getEstado().equals(Estado.FINAL)){
			Simbolo s = new Simbolo(fuente.getChar());
			matriz.doTransicion(s);
		}
		return null;
	}
	
	// Para uso privado de las acciones semanticas
	public Token getTokenActual(){
		return tokenActual;
	}
	
	public void setTokenActual(Token token){
		tokenActual = token;
	}
	
}
