package lexico.as;

import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;


public class AS5 extends AccionSemantica {

	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p) {
		if ( ! p.getTablaDeSimbolos().containsToken(t.getLexema()) )
			p.getTablaDeSimbolos().add(t);
		p.back();
	}

}
