package sintactico;

import lexico.AnalizadorLexico;
import generaciónASM.CodigoASMManager;
import generaciónASM.GeneradorASM;
import gui.ConsolaManager;
import proyecto.Proyecto;

public class AnalizadorSintactico {

	public static boolean ejecutar(Proyecto proyecto) {
		boolean condicion = false;
		Parser parser = new Parser(proyecto);
		boolean generarCodigo = false;
		//Se comienza a parsear
		int salida = parser.yyparse();

		//Se cuenta la cantidad de errores
		int errores = parser.getCantidadErrores();
		int erroresGenCod = parser.getCantidadErroresGenCod();

		if (salida == 0 && errores == 0 && erroresGenCod == 0){
			ConsolaManager.getInstance().escribirInfo("Sintáctico: El programa se parseó correctamente.");
			// Se pasa a generar el código
			generarCodigo = true;

		}else{
			ConsolaManager.getInstance().escribirError("Léxico    			:  Errores: "+ AnalizadorLexico.cantErrores +".");	
			ConsolaManager.getInstance().escribirError("Sintáctico			:  Errores: "+ errores +".");
			ConsolaManager.getInstance().escribirError("Generación de código:  Errores: "+ erroresGenCod +".");
		}

		//Se debe reiniciar esta variable
		AnalizadorLexico.cantErrores = 0;
		
		
		if (generarCodigo){
			//Mostramos el árbol sintáctico obtenido
			proyecto.setArbol(parser.getSentencias());
			//Pasamos a generar el código
			GeneradorASM generador = new GeneradorASM(parser.getSentencias(),proyecto.getTablaDeSimbolos());
			generador.generarCodigo();
			CodigoASMManager.getInstance().setCodigo(generador.getCodigoGenerado());
			condicion = true;
		}
		
		return condicion;
	}


}
