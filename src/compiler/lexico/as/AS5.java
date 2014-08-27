package compiler.lexico.as;

import compiler.Compilador;
import compiler.TablaDeSimbolos;
import compiler.lexico.Simbolo;
import compiler.util.TablaDeSimbolosEntrada;
import compiler.util.Token;

public class AS5 extends AccionSemantica {

	public AS5(Compilador compilador) {
		super(compilador);
	}

	@Override
	public void ejecutar(Simbolo s) {
		System.out.println("AS5 Digito finalizado");
		
		Token token = comp.getAnalizadorLexico().getTokenActual();
		System.out.println("AS5: Buscar \"" + token.getLexema() + "\" en la tabla de simbolos");
		TablaDeSimbolos tabla = comp.getTablaDeSimbolos();
		if (tabla.contieneLexema(token.getLexema())){
			System.out.println("AS5: Existe en la tabla");
		}else{
			System.out.println("AS5: Agregar a la tabla");
			TablaDeSimbolosEntrada entrada = new TablaDeSimbolosEntrada(token);
			tabla.agregar(token.getLexema(), entrada);
		}
		
		System.out.println("AS5: Unget caracter");
		comp.getArchivoFuente().ungetChar();
	}

}
