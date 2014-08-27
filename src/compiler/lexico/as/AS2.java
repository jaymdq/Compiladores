package compiler.lexico.as;

import compiler.Compilador;
import compiler.lexico.Simbolo;

public class AS2 extends AccionSemantica {

	public AS2(Compilador compilador) {
		super(compilador);
	}

	@Override
	public void ejecutar(Simbolo s) {
		System.out.println("AS2: Continuar lectura");
		comp.getAnalizadorLexico().getTokenActual().agregarCaracter(s.getCaracter());
	}

}
