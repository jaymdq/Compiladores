package lexico.as;

import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;

public class AS7 extends AccionSemantica {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p) {
		t = p.addTokenToTable(t);
	}

}
