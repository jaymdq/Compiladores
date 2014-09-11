package lexico.as;

import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;
import proyecto.Token.TipoToken;

public class AS6 extends AccionSemantica {

	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p) {
		t.setTipo(TipoToken.CADENA_MULTILINEA);
	}

}
