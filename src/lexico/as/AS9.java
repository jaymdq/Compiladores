package lexico.as;

import excepciones.ExcepcionInvalido;
import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;
import proyecto.Token.TipoToken;

public class AS9 extends AccionSemantica {

	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p)throws ExcepcionInvalido {
		throw new ExcepcionInvalido(); 
	}
}
