package compiler;

import gui.ConsolaManager;
import compiler.util.Token;

public class AnalizadorSintatico {
	
	private Compilador comp;

	public AnalizadorSintatico(Compilador compilador) {
		this.comp = compilador;
	}

	public void ejecutar() {
		Token token;
		do {
			ConsolaManager.getInstance().escribirInfo("[Sintactico] Obtener Token");
			System.out.println("[Sintactico] Obtener Token");
			token = comp.getAnalizadorLexico().getToken();
		} while (token != null);
	}

}
