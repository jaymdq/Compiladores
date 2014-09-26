package lexico.as;

import gui.ConsolaManager;
import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;
import proyecto.Simbolo.TipoSimbolo;

public class AS_Dot_Expected extends AccionSemantica {

	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p) {
		t.setTipo(null);
		t.setLexema("");
		
		ConsolaManager.getInstance().escribirError("Léxico [Línea "+p.getLineaActual()+"] Se esperaba un '..'.");
		
	}

}
