package util;

import util.Token.TipoToken;

public class TablaDeSimbolosEntrada {

	private TipoToken tipo;
	private String lexema;
	private boolean reservada;

	public TablaDeSimbolosEntrada(Token token) {
		super();
		this.setTipo(token.getTipo());
		this.setLexema(token.getLexema());
	}

	public TablaDeSimbolosEntrada(TipoToken tipo, String lexema, boolean reservada) {
		this.setTipo(tipo);
		this.setLexema(lexema);
		this.setReservada(reservada);
	}

	public TipoToken getTipo() {
		return tipo;
	}

	public void setTipo(TipoToken tipo) {
		this.tipo = tipo;
	}

	public String getLexema() {
		return lexema;
	}

	public void setLexema(String lexema) {
		this.lexema = lexema;
	}

	public boolean isReservada() {
		return reservada;
	}

	public void setReservada(boolean reservada) {
		this.reservada = reservada;
	}
	
}
