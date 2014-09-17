package sintactico;

import lexico.AnalizadorLexico;
import proyecto.Proyecto;
import proyecto.Token;

public class AnalizadorSintactico {

	public static void ejecutar(Proyecto p) {
		/*Token t;
		do {
			t = AnalizadorLexico.yylex();
		} while (t!= null && t.getTipo()!= null && !t.getTipo().equals(Token.TipoToken.FIN_ARCHIVO));
		*/
		
	//	Thread t = new Thread( new Parser());
	//	t.start();
		
		Parser parser = new Parser();
		parser.yyparse();
		
	} 

}
