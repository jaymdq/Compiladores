package util;

public class Token {
	enum TipoToken {
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
