package util;

import compiler.Compilador;
import compiler.TablaDeSimbolos;

public class AS7 extends AccionSemantica {

	public AS7(Compilador compilador) {
		super(compilador);
	}

	@Override
	public void ejecutar(Simbolo s) {
		Token token = comp.getAnalizadorLexico().getTokenActual();
		System.out.println("AS7: Buscar \"" + token.getLexema() + "\" en la tabla de simbolos");
		TablaDeSimbolos tabla = comp.getTablaDeSimbolos();
		if (tabla.contieneLexema(token.getLexema())){
			System.out.println("AS7: Existe en la tabla");
		}else{
			System.out.println("AS7: Agregar a la tabla");
			TablaDeSimbolosEntrada entrada = new TablaDeSimbolosEntrada(token);
			tabla.agregar(token.getLexema(), entrada);
		}
	}

}
