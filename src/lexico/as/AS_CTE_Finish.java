package lexico.as;

import gui.ConsolaManager;
import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;


public class AS_CTE_Finish extends AccionSemantica {

	private boolean back;
	
	public AS_CTE_Finish(boolean b) {
		this.back = b;
	}
	
	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p){

		//Por ahora solo enteros positivos..
		//0 a 2^32-1 = 4294967295L
		Long a = new Long(t.getLexema());
		if (a.compareTo(4294967295L) > 0){
			ConsolaManager.getInstance().escribirError("L�xico [L�nea "+p.getLineaActual()+"] La constante entera \"" + t.getLexema() + "\" no se encuentra dentro del rango 0 y 2^32 -1.");
		}else if ( a > 32767){
			t.setTipo(Token.TipoToken.ENTERO_LSS);
			t = p.addTokenToTable(t);
		}else
			t = p.addTokenToTable(t);

		if (this.back)
			p.back();
	}

}
