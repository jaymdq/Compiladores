package proyecto;


public class Token {
	
	public final static short IDENTIFICADOR=257;
	public final static short ENTERO=258;
	public final static short ENTERO_LSS=259;
	public final static short CADENA_MULTILINEA=260;
	public final static short PR_SI=261;
	public final static short PR_ENTONCES=262;
	public final static short PR_SINO=263;
	public final static short PR_IMPRIMIR=264;
	public final static short PR_ENTERO=265;
	public final static short PR_ENTERO_LSS=266;
	public final static short PR_ITERAR=267;
	public final static short PR_HASTA=268;
	public final static short PR_VECTOR=269;
	public final static short PR_DE=270;
	public final static short YYERRCODE=256;
	
	public final static short COMA = ',';
	
	public enum TipoToken {
				
		//PR_SI(Token.PR_SI),
		
		PR_SI,PR_ENTONCES, PR_SINO, PR_IMPRIMIR, PR_ENTERO, PR_ENTERO_LSS,	PR_ITERAR, PR_HASTA, PR_VECTOR, PR_DE,
		IDENTIFICADOR, ENTERO,ENTERO_LSS, CADENA_MULTILINEA,
		OP_MAS, OP_MENOS, OP_POR, OP_DIVIDIDO, COMP_MAYOR, COMP_MAYOR_IGUAL, COMP_MENOR, COMP_MENOR_IGUAL, COMP_DISTINTO, COMP_IGUAL,
		PARENTESIS_ABIERTO, PARENTESIS_CERRADO,	CORCHETE_ABIERTO, CORCHETE_CERRADO,	LLAVE_ABIERTA, LLAVE_CERRADA,
		COMA, PUNTO_Y_COMA,	ASIGNACION, FIN_ARCHIVO;
	/*	
		private int valor;
		private TipoToken(int id){
			this.valor = id;
		}*/
		
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
		
	private String lexema;
	private TipoToken tipo;
	private boolean reservado;
	
	public Token() {
		this.setLexema("");
		this.tipo = null;
		this.reservado = false;
	}
	
	public Token(TipoToken t){
		this.tipo = t;
		this.setLexema("");
		this.setReservado(false);
	}
	
	public Token(TipoToken t, String l){
		this.tipo = t;
		this.setLexema(l);
		this.setReservado(false);
	}
	
	public Token(TipoToken t, String l, boolean r){
		this.tipo = t;
		this.setLexema(l);
		this.setReservado(r);
	}

	public void agregarCaracter(char c) {
		this.lexema += c;
	}

	public String getLexema() {
		return this.lexema;
	}

	public void setLexema(String lexema) {
		this.lexema = lexema;
	}
	
	public TipoToken getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoToken t) {
		this.tipo = t;
	}
	
	public String toString() {
		if ( this.lexema.equals("") )
			return tipo.toString();
		return this.lexema;
	}
	
	public boolean isReservado() {
		return reservado;
	}

	public void setReservado(boolean r) {
		this.reservado = r;
	}
	
	
}
