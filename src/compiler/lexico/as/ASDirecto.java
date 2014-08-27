package compiler.lexico.as;

import compiler.Compilador;
import compiler.lexico.Simbolo;
import compiler.util.Token;
import compiler.util.Token.TipoToken;

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
		comp.getAnalizadorLexico().setTokenActual(new Token(tipoToken));
		
		if(unget){
			System.out.println("ASDirecto: Unget caracter");
			comp.getArchivoFuente().ungetChar();
		}
	}

}
