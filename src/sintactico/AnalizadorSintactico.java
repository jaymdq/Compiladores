package sintactico;

import lexico.AnalizadorLexico;
import generaci�nASM.CodigoASMManager;
import generaci�nASM.GeneradorASM;
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
			ConsolaManager.getInstance().escribirInfo("Sint�ctico: El programa se parse� correctamente.");
			// Se pasa a generar el c�digo
			generarCodigo = true;

		}else{
			ConsolaManager.getInstance().escribirError("L�xico    			:  Errores: "+ AnalizadorLexico.cantErrores +".");	
			ConsolaManager.getInstance().escribirError("Sint�ctico			:  Errores: "+ errores +".");
			ConsolaManager.getInstance().escribirError("Generaci�n de c�digo:  Errores: "+ erroresGenCod +".");
		}

		//Se debe reiniciar esta variable
		AnalizadorLexico.cantErrores = 0;
		
		
		if (generarCodigo){
			//Mostramos el �rbol sint�ctico obtenido
			proyecto.setArbol(parser.getSentencias());
			//Pasamos a generar el c�digo
			GeneradorASM generador = new GeneradorASM(parser.getSentencias(),proyecto.getTablaDeSimbolos());
			generador.generarCodigo();
			CodigoASMManager.getInstance().setCodigo(generador.getCodigoGenerado());
			condicion = true;
		}
		
		return condicion;
	}


}
