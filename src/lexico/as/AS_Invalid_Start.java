package lexico.as;

import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;

public class AS_Invalid_Start extends AccionSemantica {

	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p) {
		t.setTipo(null);
		t.agregarCaracter(s.getCaracter());
	}

}
