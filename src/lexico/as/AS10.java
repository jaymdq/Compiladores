package lexico.as;

import gui.ConsolaManager;
import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;

public class AS10 extends AccionSemantica {

	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p) {
		t.setLexema("");
		t.setTipo(null);		
		p.back();
		
		ConsolaManager.getInstance().escribirError("[L�nea "+p.getLineaActual()+"] Error no se especific� al caracter \"+\" en la cadena multil�nea.");

	}

}
