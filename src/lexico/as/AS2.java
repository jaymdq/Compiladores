package lexico.as;

import excepciones.ExcepcionCantidadCaracteres;
import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;

public class AS2 extends AccionSemantica {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p) throws ExcepcionCantidadCaracteres {
		
		if (t.getLexema().length() < 12)
			t.agregarCaracter(s.getCaracter());
		else{
			throw new ExcepcionCantidadCaracteres();
		}
			
	}

}
