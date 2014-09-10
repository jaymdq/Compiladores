package lexico.as;

import excepciones.ExcepcionAccionSemantica;
import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;

public abstract class AccionSemantica extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void ejecutar(Token t, Simbolo s, Proyecto p) throws ExcepcionAccionSemantica{};
	
}
