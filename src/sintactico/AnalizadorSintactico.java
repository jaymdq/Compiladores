package sintactico;

import lexico.AnalizadorLexico;


import proyecto.Proyecto;

public class AnalizadorSintactico {

	public static void ejecutar(Proyecto p) {
		int i = -1;
		do {
			i = AnalizadorLexico.getToken(p);
		} while (i != -1);
	}

}
