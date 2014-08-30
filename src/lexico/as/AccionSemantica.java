package lexico.as;

import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;

public abstract class AccionSemantica {

	public void ejecutar(Token t, Simbolo s, Proyecto p){};
	
}
