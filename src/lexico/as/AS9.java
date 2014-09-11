package lexico.as;

import gui.ConsolaManager;
import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;

public class AS9 extends AccionSemantica {

	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p){
		ConsolaManager.getInstance().escribirError("[Línea "+p.getLineaActual()+"] Carácter inválido."); 
	}
}
