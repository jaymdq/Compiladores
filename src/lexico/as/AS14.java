package lexico.as;

import gui.ConsolaManager;
import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;

public class AS14 extends AccionSemantica {

	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p) {
		ConsolaManager.getInstance().escribirError("L�xico [L�nea "+p.getLineaActual()+"] Car�cter s�lo."); 
	}

}
