package lexico.as;

import gui.ConsolaManager;
import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;
import proyecto.Simbolo.TipoSimbolo;

public class AS_Chain_Unexpected extends AccionSemantica {

	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p) {
		t.setLexema("");
		t.setTipo(null);
		p.back();
		if (s.getTipo() ==TipoSimbolo.FIN_ARCHIVO)
			ConsolaManager.getInstance().escribirError("Léxico [Línea "+p.getLineaActual()+"] No se especificó el fin de la cadena multilínea.");
		else
		ConsolaManager.getInstance().escribirError("Léxico [Línea "+p.getLineaActual()+"] No se especificó al caracter \"+\" en la cadena multilínea.");
	}

}
