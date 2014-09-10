package lexico.as;

import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;
import excepciones.ExcepcionCantidadCaracteres;

public class AS10 extends AccionSemantica {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p) throws ExcepcionCantidadCaracteres {
		t.agregarCaracter(s.getCaracter());		
	}
}
