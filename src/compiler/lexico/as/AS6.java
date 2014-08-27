package compiler.lexico.as;

import compiler.Compilador;
import compiler.lexico.Simbolo;
import compiler.util.Token;
import compiler.util.Token.TipoToken;

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
