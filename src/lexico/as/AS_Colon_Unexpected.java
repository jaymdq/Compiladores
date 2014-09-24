package lexico.as;

import gui.ConsolaManager;
import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;

public class AS_Colon_Unexpected extends AccionSemantica {

	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p) {
		t.setLexema("");
		t.setTipo(null);		
		p.back();
		ConsolaManager.getInstance().escribirError("L�xico [L�nea "+p.getLineaActual()+"] No se especific� al caracter \"=\" luego del \":\".");
	}

}
