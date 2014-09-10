package excepciones;

import gui.ConsolaManager;
import proyecto.Proyecto;
import proyecto.Token;

public class ExcepcionRangoEntero extends ExcepcionAccionSemantica {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void avisarPorConsola(Proyecto p, Token t) {
		ConsolaManager.getInstance().escribirError("[Línea "+p.getLineaActual()+"] La constante entera \"" + t.getLexema() + "\" no se encuentra dentro del rango 0 y 2^32 -1.");
	}

}
