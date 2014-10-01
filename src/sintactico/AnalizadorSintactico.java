package sintactico;

import gui.ConsolaManager;
import proyecto.Proyecto;

public class AnalizadorSintactico {

	public static void ejecutar(Proyecto proyecto) {
				
		Parser parser = new Parser(proyecto);
		
		//Se comienza a parsear
		parser.yyparse();
		
		//Se cuenta la cantidad de errores
		int salida = parser.getCantidadErrores();
	
		if (salida == 0){
			ConsolaManager.getInstance().escribirInfo("Sint�ctico: El programa se parseo correctamente.");
		}else{
			ConsolaManager.getInstance().escribirError("Sint�ctico: No se pudo completar la lectura del programa: "+ salida +" errores.");
		}
			
	}
	

}
