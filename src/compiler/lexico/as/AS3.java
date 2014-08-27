package compiler.lexico.as;

import compiler.Compilador;
import compiler.TablaDeSimbolos;
import compiler.lexico.Simbolo;
import compiler.util.TablaDeSimbolosEntrada;
import compiler.util.Token;

public class AS3 extends AccionSemantica {

	public AS3(Compilador compilador) {
		super(compilador);
	}

	@Override
	public void ejecutar(Simbolo s) {
		Token token = comp.getAnalizadorLexico().getTokenActual();
		System.out.println("AS3: Buscar \"" + token.getLexema() + "\" en la tabla de simbolos");
		TablaDeSimbolos tabla = comp.getTablaDeSimbolos();
		if (tabla.contieneLexema(token.getLexema())){
			System.out.println("AS3: Existe en la tabla");
		}else{
			System.out.println("AS3: Agregar a la tabla");
			TablaDeSimbolosEntrada entrada = new TablaDeSimbolosEntrada(token);
			tabla.agregar(token.getLexema(), entrada);
		}
		
		System.out.println("AS3: Unget caracter");
		comp.getArchivoFuente().ungetChar();
	}

}
