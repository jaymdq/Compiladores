package compiler.lexico;

import compiler.lexico.MatrizTransicion.Estado;
import compiler.lexico.as.AccionSemantica;

public class Transicion {
	private Estado nuevoEstado;
	private AccionSemantica accionSemantica;
	
	public Transicion(Estado nuevoEstado, AccionSemantica accionSemantica) {
		super();
		this.nuevoEstado = nuevoEstado;
		this.accionSemantica = accionSemantica;
	}

	public Transicion(Estado nuevoEstado) {
		super();
		this.nuevoEstado = nuevoEstado;
	}

	public Estado getNuevoEstado() {
		return nuevoEstado;
	}

	public void ejecutarAccionSemantica(Simbolo s) {
		if(accionSemantica != null)
			accionSemantica.ejecutar(s);
	}

}
