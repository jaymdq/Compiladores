package compiler;

import util.Archivo;
import util.MatrizTransicion;
import util.Simbolo;
import util.Token;
import util.MatrizTransicion.Estado;

public class AnalizadorLexico {

	private Archivo fuente;
	private MatrizTransicion matriz;

	public AnalizadorLexico(Archivo fuente) {
		super();
		this.fuente = fuente;
		this.matriz = new MatrizTransicion();
	}
	
	public Token getToken(){
		while (!matriz.getEstado().equals(Estado.FINAL)){
			Simbolo s = new Simbolo(fuente.getChar());
			matriz.doTransicion(s.getTipo());
		}
		return null;
	}
	
}
