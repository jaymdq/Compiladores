package excepciones;

import proyecto.Proyecto;
import proyecto.Token;

public abstract class ExcepcionAccionSemantica extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract void avisarPorConsola(Proyecto p,Token t);
}
