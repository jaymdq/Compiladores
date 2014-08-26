package util;

import util.Token.TipoToken;
import compiler.Compilador;

public class AS4 extends AccionSemantica {

	public AS4(Compilador compilador) {
		super(compilador);
	}

	@Override
	public void ejecutar(Simbolo s) {
		System.out.println("AS4: Iniciar lectura de constante");
		Token token = new Token(TipoToken.ENTERO);
		token.agregarCaracter(s.getCaracter());
		comp.getAnalizadorLexico().setTokenActual(token);
	}

}
