package lexico;

import lexico.MatrizTransicion.Estado;
import lexico.as.AccionSemantica;
import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;

public class Transicion {
	
	private Estado nuevoEstado;
	private AccionSemantica accionSemantica;

 	public Transicion(Estado ne, AccionSemantica as) {
		super();
		this.nuevoEstado = ne;
		this.accionSemantica = as;
	}
 	 	
	public Transicion(Estado ne) {
		super();
		this.nuevoEstado = ne;
		this.accionSemantica = null;
	}

	public Estado getNuevoEstado() {
		return nuevoEstado;
	}

	public void ejecutarAccionSemantica(Token t, Simbolo s, Proyecto p) {
		if(accionSemantica != null)
			accionSemantica.ejecutar(t,s,p);
	}
	
	public boolean isEnd() {
		return (nuevoEstado == Estado.FINAL);
	}
	
}
