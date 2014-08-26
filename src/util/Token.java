package util;

public class Token {
	public enum TipoToken {
		PR_SI, PR_ENTONCES, PR_SINO, 
		PR_IMPRIMIR, PR_ENTERO, PR_ENTERO_LSS,
		PR_ITERAR, PR_HASTA, PR_VECTOR, PR_DE,
		IDENTIFICADOR, ENTERO;
	}
	
	private String lexema;
	private TipoToken tipo;

	public Token(TipoToken tipo){
		this.tipo = tipo;
		lexema = new String();
	}

	public void agregarCaracter(char c) {
		lexema += c;
		System.out.println("Lexema " + lexema);
	}

	public String getLexema() {
		return lexema;
	}

	public TipoToken getTipo() {
		return tipo;
	}
		
}
