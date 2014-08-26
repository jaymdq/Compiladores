package util;

import compiler.Compilador;

public abstract class AccionSemantica {

	protected Compilador comp;
	
	public AccionSemantica(Compilador compilador){
		this.comp = compilador;
	}
	
	public abstract void ejecutar(Simbolo s);
	
}
