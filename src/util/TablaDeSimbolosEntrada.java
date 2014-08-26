package util;

import util.Token.TipoToken;

public class TablaDeSimbolosEntrada {

	private TipoToken tipo;
	private String lexema;
	private boolean reservada;

	public TablaDeSimbolosEntrada(Token token) {
		super();
		this.tipo = token.getTipo();
		this.lexema = token.getLexema();
	}

	public TablaDeSimbolosEntrada(TipoToken tipo, String lexema, boolean reservada) {
		this.tipo = tipo;
		this.lexema = lexema;
		this.reservada = reservada;
	}
	
}
