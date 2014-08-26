package util;

import util.Token.TipoToken;

public class PalabraReservada {
	
	TipoToken codigo;
	String lexema;

	public PalabraReservada(TipoToken codigo, String lexema) {
		this.codigo = codigo;
		this.lexema = lexema;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lexema == null) ? 0 : lexema.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PalabraReservada other = (PalabraReservada) obj;
		if (lexema == null) {
			if (other.lexema != null)
				return false;
		} else if (!lexema.equals(other.lexema))
			return false;
		return true;
	}

}