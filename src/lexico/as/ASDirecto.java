package lexico.as;

import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;
import proyecto.Token.TipoToken;

public class ASDirecto extends AccionSemantica {

	private boolean u;
	private TipoToken tipo;
	
	public ASDirecto(TipoToken tipo, boolean u) {
		this.tipo = tipo;
		this.u = u;
	}
	
	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p) {
		t.setTipo(this.tipo);
		if(u)
			p.back();
	}

}
