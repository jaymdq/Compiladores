package lexico.as;

import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;
import proyecto.Token.TipoToken;

public class AS_CTE_Start extends AccionSemantica {

	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p) {
		t.setTipo(TipoToken.ENTERO);
		t.agregarCaracter(s.getCaracter());
	}

}
