package util;

import util.Token.TipoToken;
import compiler.Compilador;

public class ASDirecto extends AccionSemantica {

	private TipoToken tipoToken;
	private boolean unget;

	public ASDirecto(Compilador compilador, TipoToken tipoToken) {
		this(compilador, tipoToken, false);
	}

	public ASDirecto(Compilador compilador, TipoToken tipoToken, boolean unget) {
		super(compilador);
		this.tipoToken = tipoToken;
		this.unget = unget;
	}

	@Override
	public void ejecutar(Simbolo s) {
		System.out.println("ASDirecto: Publicar token " + tipoToken);
		if(unget){
			System.out.println("ASDirecto: Unget caracter");
			comp.getArchivoFuente().ungetChar();
		}
	}

}
