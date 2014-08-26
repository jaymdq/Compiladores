package util;

import compiler.Compilador;

public class AS1 extends AccionSemantica {

	public AS1(Compilador compilador) {
		super(compilador);
	}

	@Override
	public void ejecutar(Simbolo s) {
		System.out.println("AS1: Iniciar lectura de identificador");
		Token token = new Token();
		token.agregarCaracter(s.getCaracter());
		comp.getAnalizadorLexico().setTokenActual(token);
	}

}
