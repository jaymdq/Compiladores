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
	public final static short COMP_MAYOR_IGUAL=271;
	public final static short COMP_MENOR_IGUAL=272;
	public final static short COMP_DISTINTO=273;
	public final static short ASIGNACION=274;
	public final static short YYERRCODE=256;
	
	
	public enum TipoToken {
			
		//Palabras Reservadas
		PR_SI(Token.PR_SI),
		PR_ENTONCES(Token.PR_ENTONCES),
		PR_SINO(Token.PR_SINO), 
		PR_IMPRIMIR(Token.PR_IMPRIMIR), 
		PR_ENTERO(Token.PR_ENTERO), 
		PR_ENTERO_LSS(Token.PR_ENTERO_LSS),	
		PR_ITERAR(Token.PR_ITERAR), 
		PR_HASTA(Token.PR_HASTA), 
		PR_VECTOR(Token.PR_VECTOR), 
		PR_DE(Token.PR_DE),
		
		//No Terminales
		IDENTIFICADOR(Token.IDENTIFICADOR),
		ENTERO(Token.ENTERO),
		ENTERO_LSS(Token.ENTERO_LSS),
		CADENA_MULTILINEA(Token.CADENA_MULTILINEA),
		
		//Literales
		OP_MAS((char) '+'), 
		OP_MENOS((char) '-'), 
		OP_POR((char) '*'), 
		OP_DIVIDIDO((char) '/'), 
		COMP_MAYOR((char) '>'), 
		COMP_MENOR((char) '<'), 
		COMP_IGUAL((char) '='),		
		PARENTESIS_ABIERTO((char) '('), 
		PARENTESIS_CERRADO((char) ')'),	
		CORCHETE_ABIERTO((char) '['), 
		CORCHETE_CERRADO((char) ']'),	
		LLAVE_ABIERTA((char) '{'), 
		LLAVE_CERRADA((char) '}'),
		COMA((char) ','), 
		PUNTO_Y_COMA((char) ';'),	
		
		
		//No Literales importantes
		COMP_MAYOR_IGUAL(Token.COMP_MAYOR_IGUAL), 
		COMP_MENOR_IGUAL(Token.COMP_MENOR_IGUAL), 
		COMP_DISTINTO(Token.COMP_DISTINTO), 
		ASIGNACION(Token.ASIGNACION), 
		FIN_ARCHIVO(0);
		
	
		private int valor;
		private TipoToken(int id){
			this.setValor(id);
		}
		
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

		public int getValor() {
			return valor;
		}

		public void setValor(int valor) {
			this.valor = valor;
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
