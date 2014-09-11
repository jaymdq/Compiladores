package lexico.as;

import gui.ConsolaManager;
import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;

public class AS8 extends AccionSemantica{

	public void ejecutar(Token t, Simbolo s, Proyecto p){
		t.setLexema("");
		t.setTipo(null);
		ConsolaManager.getInstance().escribirError("[Línea "+p.getLineaActual()+"] Identificador inválido.");
	}
}
