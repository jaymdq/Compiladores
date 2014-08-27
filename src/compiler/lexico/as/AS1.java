package compiler.lexico.as;

import compiler.Compilador;
import compiler.lexico.Simbolo;
import compiler.util.Token;
import compiler.util.Token.TipoToken;

public class AS1 extends AccionSemantica {

	public AS1(Compilador compilador) {
		super(compilador);
	}

	@Override
	public void ejecutar(Simbolo s) {
		System.out.println("AS1: Iniciar lectura de identificador");
		Token token = new Token(TipoToken.IDENTIFICADOR);
		token.agregarCaracter(s.getCaracter());
		comp.getAnalizadorLexico().setTokenActual(token);
	}

}
