package compiler;

public class AnalizadorSintatico {
	
	private Compilador comp;

	public AnalizadorSintatico(Compilador compilador) {
		this.comp = compilador;
	}

	public void ejecutar() {
		System.out.println("[Sintactico] Obtener Token");
		comp.getAnalizadorLexico().getToken();
		comp.getAnalizadorLexico().getToken();
		//comp.getAnalizadorLexico().getToken();
	}

}
