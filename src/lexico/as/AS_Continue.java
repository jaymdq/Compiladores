package lexico.as;

import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;

public class AS_Continue extends AccionSemantica {

	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p){
		t.agregarCaracter(s.getCaracter());
	}

}
