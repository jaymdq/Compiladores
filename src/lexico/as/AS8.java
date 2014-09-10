package lexico.as;

import excepciones.ExcepcionIdentificadorInvalido;
import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;

public class AS8 extends AccionSemantica{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void ejecutar(Token t, Simbolo s, Proyecto p) throws ExcepcionIdentificadorInvalido{
		t.setLexema("");
		t.setTipo(null);
		throw new ExcepcionIdentificadorInvalido();
	}
}
