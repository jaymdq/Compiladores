package util;

import compiler.Compilador;

public class AS2 extends AccionSemantica {

	public AS2(Compilador compilador) {
		super(compilador);
	}

	@Override
	public void ejecutar(Simbolo s) {
		System.out.println("AS2: Continuar lectura de identificador");
		comp.getAnalizadorLexico().getTokenActual().agregarCaracter(s.getCaracter());
	}

}
