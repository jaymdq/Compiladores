package util;

import compiler.Compilador;

public class AS3 extends AccionSemantica {

	public AS3(Compilador compilador) {
		super(compilador);
	}

	@Override
	public void ejecutar(Simbolo s) {
		Token token = comp.getAnalizadorLexico().getTokenActual();
		System.out.println("AS3: Buscar \"" + token.getLexema() + "\" en la tabla de simbolos");
		System.out.println("AS3: Unget caracter");
		comp.getArchivoFuente().ungetChar();
	}

}
