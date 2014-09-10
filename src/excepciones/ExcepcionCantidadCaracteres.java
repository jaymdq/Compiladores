package excepciones;

import proyecto.Proyecto;
import proyecto.Token;
import gui.ConsolaManager;

public class ExcepcionCantidadCaracteres extends ExcepcionAccionSemantica {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void avisarPorConsola(Proyecto p, Token t) {
		ConsolaManager.getInstance().escribirWarning("[L�nea "+p.getLineaActual()+"] Se excedi� la cantidad de car�cteres del identificador \"" + t.getLexema()+"\".");

	}

}
