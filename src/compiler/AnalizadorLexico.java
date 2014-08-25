package compiler;

import util.MatrizTransicion;
import util.Simbolo;
import util.Token;
import util.MatrizTransicion.Estado;

public class AnalizadorLexico {

	private Compilador comp;
	private MatrizTransicion matriz;

	public AnalizadorLexico(Compilador compilador) {
		super();
		this.comp = compilador;
		this.matriz = new MatrizTransicion();
	}
	
	public Token getToken(){
		ArchivoFuente fuente = comp.getArchivoFuente();
		while (!matriz.getEstado().equals(Estado.FINAL)){
			Simbolo s = new Simbolo(fuente.getChar());
			matriz.doTransicion(s.getTipo());
		}
		return null;
	}
	
}
