package lexico;

import lexico.MatrizTransicion.Estado;
import lexico.as.AccionSemantica;
import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;

public class Transicion {
	
	private Estado ne;
	private AccionSemantica as;

 	public Transicion(Estado ne, AccionSemantica as) {
		super();
		this.ne = ne;
		this.as = as;
	}
 	 	
	public Transicion(Estado ne) {
		super();
		this.ne = ne;
		this.as = null;
	}

	public Estado getNuevoEstado() {
		return ne;
	}

	public void ejecutarAccionSemantica(Token t, Simbolo s, Proyecto p) {
		if(as != null)
			as.ejecutar(t,s,p);
	}
	
	public boolean isEnd() {
		return (ne == Estado.FINAL);
	}
	
}
