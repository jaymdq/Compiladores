package util;

public class Token {
	public enum TipoToken {
		PR_SI, PR_ENTONCES, PR_SINO, 
		PR_IMPRIMIR, PR_ENTERO, PR_ENTERO_LSS,
		PR_ITERAR, PR_HASTA, PR_VECTOR, PR_DE,
		OP_MAS, OP_MENOS, OP_POR, OP_DIVIDIDO,
		COMP_MAYOR, COMP_MAYOR_IGUAL, COMP_MENOR, COMP_MENOR_IGUAL, COMP_DISTINTO,
		PARENTESIS_ABIERTO, PARENTESIS_CERRADO,
		CORCHETE_ABIERTO, CORCHETE_CERRADO,
		LLAVE_ABIERTO, LLAVE_CERRADO,
		COMA, PUNTO_COMA,
		ASIGNACION, IDENTIFICADOR, ENTERO;
	}
	
	private String lexema;
	private TipoToken tipo;
	private TablaDeSimbolosEntrada entrada;

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

	public TablaDeSimbolosEntrada getEntrada() {
		return entrada;
	}

	public void setEntrada(TablaDeSimbolosEntrada entrada) {
		this.entrada = entrada;
	}
		
}
