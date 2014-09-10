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
		ConsolaManager.getInstance().escribirWarning("[Línea "+p.getLineaActual()+"] Se excedió la cantidad de carácteres del identificador \"" + t.getLexema()+"\".");

	}

}
