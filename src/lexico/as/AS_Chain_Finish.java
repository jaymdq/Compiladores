package lexico.as;

import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;

public class AS_Chain_Finish extends AccionSemantica {

	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p) {
		t.setLexema("\"" + t.getLexema() + "\"");
		t = p.addTokenToTable(t);
	}

}
