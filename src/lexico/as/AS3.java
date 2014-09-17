package lexico.as;

import lexico.AnalizadorLexico;
import gui.ConsolaManager;
import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;

public class AS3 extends AccionSemantica {

	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p) {
	
		//Verificar que tiene menos de 12 caracteres un identificador o truncarlo
		if (t.getTipo() == Token.TipoToken.IDENTIFICADOR){
			if (t.getLexema().length() > 12){
				t.setLexema(t.getLexema().substring(0, 11));
				ConsolaManager.getInstance().escribirWarning("Léxico [Línea "+p.getLineaActual()+"] Se excedió la cantidad de carácteres del identificador \"" + t.getLexema()+"\".");
			}
			
			//Verificamos si en realidad es una palabra reservada. Y en caso de serlo, seteamos su tipo.
			if (AnalizadorLexico.isPalabraReservada(t.getLexema())){
				AnalizadorLexico.setearTokenAPalabraReservada(t);
				t.setLexema(t.getLexema().toLowerCase());
			}
			
		}
		
		//Si no resulto ser una palabra reservada, se agrega a la tabla de simbolos.
		if (! t.isReservado() ){
			t = p.addTokenToTable(t);
		}else{
			p.addTokenToList(t);
		}

		p.back();
		if ( s.getTipo() == Simbolo.TipoSimbolo.NUEVA_LINEA){
			//Arreglar linea actual del proyecto..
			p.retrocederLinea();
		}
	}

}
