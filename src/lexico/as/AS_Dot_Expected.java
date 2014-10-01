package lexico.as;

import gui.ConsolaManager;
import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;

public class AS_Dot_Expected extends AccionSemantica {
	
	private boolean back;
	
	public AS_Dot_Expected(boolean b){
		this.back = b;
	}
	
	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p) {
		t.setTipo(null);
		t.setLexema("");
		
		if (back)
			p.back();
		ConsolaManager.getInstance().escribirError("Léxico [Línea "+p.getLineaActual()+"] Se esperaba un '..'.");
		
	}

}
