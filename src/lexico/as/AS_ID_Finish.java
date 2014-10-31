package lexico.as;

import lexico.AnalizadorLexico;
import gui.ConsolaManager;
import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;

public class AS_ID_Finish extends AccionSemantica {

	private boolean back;
	
	public AS_ID_Finish(boolean b) {
		this.back = b;
	}
	
	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p) {
	
		// Verificar que tiene menos de 12 caracteres un identificador o truncarlo
		if (t.getLexema().length() > 12){
			ConsolaManager.getInstance().escribirWarning("Léxico [Línea "+p.getLineaActual()+"] Se excedió la cantidad de carácteres del identificador \"" + t.getLexema()+"\" y se remplazo por '" + t.getLexema().substring(0, 12) + "'");
			t.setLexema(t.getLexema().substring(0, 12));
		}
			
		// Verificamos si en realidad es una palabra reservada. Y en caso de serlo, seteamos su tipo.
		if (AnalizadorLexico.isPalabraReservada(t.getLexema())){
			AnalizadorLexico.setearTokenAPalabraReservada(t);
			t.setLexema(t.getLexema().toLowerCase());
		}
				
		// Si no resulto ser una palabra reservada, se agrega a la tabla de simbolos.
		if (! t.isReservado() ){
			t = p.addTokenToTable(t);
		}else{
			p.addTokenToList(t);
		}
		
		// Si se debe volver, se vuelve
		if (back)
			p.back();
	}

}
