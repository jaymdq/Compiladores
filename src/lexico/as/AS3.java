package lexico.as;

import gui.ConsolaManager;
import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;

public class AS3 extends AccionSemantica {

	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p) {
		t = p.addTokenToTable(t);

		//Verificar que tiene menos de 12 caracteres un identificador o truncarlo
		if (t.getTipo() == Token.TipoToken.IDENTIFICADOR){
			if (t.getLexema().length() > 12){
				t.setLexema(t.getLexema().substring(0, 11));
				ConsolaManager.getInstance().escribirWarning("[Línea "+p.getLineaActual()+"] Se excedió la cantidad de carácteres del identificador \"" + t.getLexema()+"\".");
			}
		}
		
		p.back();
		if ( s.getTipo() == Simbolo.TipoSimbolo.NUEVA_LINEA){
			//Arreglar linea actual del proyecto..
			p.retrocederLinea();
		}
	}

}
