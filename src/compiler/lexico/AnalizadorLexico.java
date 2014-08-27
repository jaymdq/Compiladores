package compiler.lexico;

import java.util.Observable;

import compiler.Compilador;
import compiler.lexico.MatrizTransicion.Estado;
import compiler.util.Token;

public class AnalizadorLexico extends Observable{

	private Compilador comp;
	private Token tokenActual;

	public AnalizadorLexico(Compilador compilador) {
		super();
		this.comp = compilador;
	}
	
	public Token getToken(){
		ArchivoFuente fuente = comp.getArchivoFuente();
		MatrizTransicion matriz = comp.getMatrizTransicion();
		
		matriz.volverAInicio();
		do {
			char c = fuente.getChar();
			if (c == 0){
				return null;
			}
			Simbolo s = new Simbolo(c);
			matriz.doTransicion(s);
		}while (!matriz.getEstado().equals(Estado.FINAL));
		
		setChanged();
		notifyObservers();
		
		return tokenActual;
	}
	
	// Para uso privado de las acciones semanticas
	public Token getTokenActual(){
		return tokenActual;
	}
	
	public void setTokenActual(Token token){
		tokenActual = token;
	}
	
}
