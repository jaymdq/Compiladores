package lexico.as;

import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;
import proyecto.Token.TipoToken;

public class AS4 extends AccionSemantica {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p) {
		t.setTipo(TipoToken.ENTERO);
		t.agregarCaracter(s.getCaracter());
	}

}
