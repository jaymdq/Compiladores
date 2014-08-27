package util;

import util.Token.TipoToken;
import compiler.Compilador;

public class AS6 extends AccionSemantica {

	public AS6(Compilador compilador) {
		super(compilador);
	}

	@Override
	public void ejecutar(Simbolo s) {
		System.out.println("AS6: Iniciar lectura de cadena multilinea");
		Token token = new Token(TipoToken.CADENA_MULTILINEA);
		comp.getAnalizadorLexico().setTokenActual(token);
	}

}
