package compiler;

public class AnalizadorSintatico {
	
	private Compilador comp;

	public AnalizadorSintatico(Compilador compilador) {
		this.comp = compilador;
	}

	public void ejecutar() {
		for(int i = 0; i < 5; i++){
			System.out.println("[Sintactico] Obtener Token");
			comp.getAnalizadorLexico().getToken();
		}
	}

}
