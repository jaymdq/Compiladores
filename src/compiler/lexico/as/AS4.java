package compiler.lexico.as;

import compiler.Compilador;
import compiler.lexico.Simbolo;
import compiler.util.Token;
import compiler.util.Token.TipoToken;

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
