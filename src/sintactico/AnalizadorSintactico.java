package sintactico;

import gui.ConsolaManager;
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
		
		Parser parser = new Parser(p);
		int salida = parser.yyparse();
		if (salida == 0){
			ConsolaManager.getInstance().escribirInfo("Sintáctico: Todo Resultó Bien.");
		}else{
			ConsolaManager.getInstance().escribirInfo("Sintáctico: Error Sintáctico.");
		}
			
		
	} 

}
