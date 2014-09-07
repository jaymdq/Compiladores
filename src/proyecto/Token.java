package proyecto;


public class Token {
	
	public enum TipoToken {
		PR_SI, PR_ENTONCES, PR_SINO, PR_IMPRIMIR, PR_ENTERO, PR_ENTERO_LSS,	PR_ITERAR, PR_HASTA, PR_VECTOR, PR_DE,
		IDENTIFICADOR, ENTERO, CADENA_MULTILINEA,
		OP_MAS, OP_MENOS, OP_POR, OP_DIVIDIDO, COMP_MAYOR, COMP_MAYOR_IGUAL, COMP_MENOR, COMP_MENOR_IGUAL, COMP_DISTINTO, COMP_IGUAL,
		PARENTESIS_ABIERTO, PARENTESIS_CERRADO,	CORCHETE_ABIERTO, CORCHETE_CERRADO,	LLAVE_ABIERTA, LLAVE_CERRADA,
		COMA, PUNTO_Y_COMA,	ASIGNACION, FIN_ARCHIVO;
		
		public String toString(){
			if (this.equals(OP_MAS))
				return "+";
			else if (this.equals(OP_MENOS))
				return "-";
			else if (this.equals(OP_POR))
				return "*";
			else if (this.equals(OP_DIVIDIDO))
				return "/";
			else if (this.equals(COMP_MAYOR))
				return ">";
			else if (this.equals(COMP_MAYOR_IGUAL))
				return ">=";
			else if (this.equals(COMP_MENOR))
				return "<";
			else if (this.equals(COMP_MENOR_IGUAL))
				return "<=";
			else if (this.equals(COMP_DISTINTO))
				return "^=";
			else if (this.equals(COMP_IGUAL))
				return "=";
			else if (this.equals(PARENTESIS_ABIERTO))
				return "(";
			else if (this.equals(PARENTESIS_CERRADO))
				return ")";
			else if (this.equals(CORCHETE_ABIERTO))
				return "[";
			else if (this.equals(CORCHETE_CERRADO))
				return "]";
			else if (this.equals(LLAVE_ABIERTA))
				return "{";
			else if (this.equals(LLAVE_CERRADA))
				return "}";
			else if (this.equals(COMA))
				return ",";
			else if (this.equals(PUNTO_Y_COMA))
				return ";";
			else if (this.equals(ASIGNACION))
				return ":=";
			else if (this.equals(FIN_ARCHIVO))
				return "EOF";
			else
				return super.toString();
		}
	}
	
	private String l;
	private TipoToken t;
	private boolean r;
	
	public Token() {
		this.setLexema("");
		this.t = null;
		this.r = false;
	}
	
	public Token(TipoToken t){
		this.t = t;
		this.setLexema("");
		this.setReservado(false);
	}
	
	public Token(TipoToken t, String l){
		this.t = t;
		this.setLexema(l);
		this.setReservado(false);
	}
	
	public Token(TipoToken t, String l, boolean r){
		this.t = t;
		this.setLexema(l);
		this.setReservado(r);
	}

	public void agregarCaracter(char c) {
		this.l += c;
	}

	public String getLexema() {
		return this.l;
	}

	public void setLexema(String lexema) {
		this.l = lexema;
	}
	
	public TipoToken getTipo() {
		return t;
	}
	
	public void setTipo(TipoToken t) {
		this.t = t;
	}
	
	public String toString() {
		// WARNING - ¿Porque retorna el tipo si lexema es vacio?
		if ( this.l.equals("") )
			return t.toString();
		return this.l;
	}
	
	public boolean isReservado() {
		return r;
	}

	public void setReservado(boolean r) {
		this.r = r;
	}

	public boolean isOperador(){
		boolean condicion = false;
		
		return condicion;
	}
	
}
