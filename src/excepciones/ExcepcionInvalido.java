package excepciones;

import gui.ConsolaManager;
import proyecto.Proyecto;
import proyecto.Token;

public class ExcepcionInvalido extends ExcepcionAccionSemantica {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void avisarPorConsola(Proyecto p, Token t) {
		ConsolaManager.getInstance().escribirError("[L�nea "+p.getLineaActual()+"] Car�cter inv�lido.");
	}

}
