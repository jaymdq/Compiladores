package sintactico;

import lexico.AnalizadorLexico;
import gui.ConsolaManager;
import proyecto.Proyecto;

public class AnalizadorSintactico {

	public static void ejecutar(Proyecto proyecto) {
				
		Parser parser = new Parser(proyecto);
		
		//Se comienza a parsear
		int salida = parser.yyparse();
		
		//Se cuenta la cantidad de errores
		int errores = parser.getCantidadErrores();
	
		if (salida == 0 && errores == 0){
			ConsolaManager.getInstance().escribirInfo("Sint�ctico: El programa se parse� correctamente.");
		}else{
			ConsolaManager.getInstance().escribirError("L�xico    :  Errores: "+ AnalizadorLexico.cantErrores +".");	
			ConsolaManager.getInstance().escribirError("Sint�ctico:  Errores: "+ errores +".");
		}
			
		AnalizadorLexico.cantErrores = 0;
	}
	

}
