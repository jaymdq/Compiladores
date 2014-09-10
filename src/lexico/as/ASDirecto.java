package lexico.as;

import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;
import proyecto.Token.TipoToken;

public class ASDirecto extends AccionSemantica {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean doBack;
	private TipoToken tipo;
	
	public ASDirecto(TipoToken tipo, boolean u) {
		this.tipo = tipo;
		this.doBack = u;
	}
	
	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p) {
		t.setTipo(this.tipo);
		t.setLexema(tipo.toString());
		p.addTokenToList(t);
		if(doBack){
			p.back();
			//Areglar linea actual del proyecto
			p.retrocederLinea();
		}
			
	}

}
