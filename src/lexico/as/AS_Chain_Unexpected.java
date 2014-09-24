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
			ConsolaManager.getInstance().escribirError("L�xico [L�nea "+p.getLineaActual()+"] No se especific� el fin de la cadena multil�nea.");
		else
		ConsolaManager.getInstance().escribirError("L�xico [L�nea "+p.getLineaActual()+"] No se especific� al caracter \"+\" en la cadena multil�nea.");
	}

}
