package lexico.as;

import lexico.AnalizadorLexico;
import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;
import sintactico.ParserVal;

public class AS_Chain_Finish extends AccionSemantica {

	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p) {
		t = p.addTokenToTable(t);
	}

}
