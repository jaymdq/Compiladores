package sintactico;

import lexico.AnalizadorLexico;
import gui.ConsolaManager;
import proyecto.Proyecto;

public class AnalizadorSintactico {

	public static void ejecutar(Proyecto proyecto) {
		/*int t;
		do {
			t = AnalizadorLexico.yylex();
		} while (t!=0);*/
				
		Parser parser = new Parser(proyecto);
		
		int salida = parser.yyparse();
	
		if (salida == 0){
			ConsolaManager.getInstance().escribirInfo("Sint�ctico: Se complet� la lectura del programa.");
		}else{
			ConsolaManager.getInstance().escribirInfo("Sint�ctico: No se pudo completar la lectura del programa.");
		}
			
	}
	

}
