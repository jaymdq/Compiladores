package util;

public class Token {
	private String lexema;

	public Token(){
		lexema = new String();
	}

	public void agregarCaracter(char c) {
		lexema += c;
		System.out.println("Lexema " + lexema);
	}

	public String getLexema() {
		return lexema;
	}
	
}
