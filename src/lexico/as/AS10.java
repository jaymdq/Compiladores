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
		
		ConsolaManager.getInstance().escribirError("[Línea "+p.getLineaActual()+"] Error no se especificó al caracter \"+\" en la cadena multilínea.");

	}

}
