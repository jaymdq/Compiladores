package lexico.as;

import lexico.AnalizadorLexico;
import gui.ConsolaManager;
import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;

public class AS_Invalid_Finish extends AccionSemantica {

	private boolean back;
	
	public AS_Invalid_Finish(boolean b) {
		this.back = b;
	}
	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p) {
		ConsolaManager.getInstance().escribirError("Léxico [Línea "+p.getLineaActual()+"] ID o CTE invalido '" + t.getLexema() +"'");
		AnalizadorLexico.cantErrores ++;
		t.setTipo(null);
		t.setLexema("");
		if (this.back)
			p.back();
	}

}
