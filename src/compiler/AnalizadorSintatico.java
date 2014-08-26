package compiler;

import userInterface.ConsolaManager;

public class AnalizadorSintatico {
	
	private Compilador comp;

	public AnalizadorSintatico(Compilador compilador) {
		this.comp = compilador;
	}

	public void ejecutar() {
		for(int i = 0; i < 5; i++){
			ConsolaManager.getInstance().escribirInfo("[Sintactico] Obtener Token");
			System.out.println("[Sintactico] Obtener Token");
			comp.getAnalizadorLexico().getToken();
		}
	}

}
