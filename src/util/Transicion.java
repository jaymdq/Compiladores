package util;

import util.MatrizTransicion.Estado;

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

	public AccionSemantica getAccionSemantica() {
		return accionSemantica;
	}

}
