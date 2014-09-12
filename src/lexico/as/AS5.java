package lexico.as;

import gui.ConsolaManager;
import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;


public class AS5 extends AccionSemantica {

	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p){


		//Por ahora solo enteros positivos..
		//0 a 2^32-1 = 4294967295L
		Long a = new Long(t.getLexema());
		if (a.compareTo(4294967295L) > 0){
			ConsolaManager.getInstance().escribirError("Léxico [Línea "+p.getLineaActual()+"] La constante entera \"" + t.getLexema() + "\" no se encuentra dentro del rango 0 y 2^32 -1.");
		}else if ( a > 32767){
			t.setTipo(Token.TipoToken.ENTERO_LSS);
			t = p.addTokenToTable(t);
		}else
			t = p.addTokenToTable(t);

		p.back();
		if ( s.getTipo() == Simbolo.TipoSimbolo.NUEVA_LINEA){
			//Arreglar linea actual del proyecto..
			p.retrocederLinea();
		}

	}

}
