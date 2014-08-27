package compiler.lexico.as;

import compiler.Compilador;
import compiler.lexico.Simbolo;

public abstract class AccionSemantica {

	protected Compilador comp;
	
	public AccionSemantica(Compilador compilador){
		this.comp = compilador;
	}
	
	public abstract void ejecutar(Simbolo s);
	
}
