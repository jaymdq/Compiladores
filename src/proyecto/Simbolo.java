package proyecto;

public class Simbolo {
	
	public enum TipoSimbolo {
		LETRA, DIGITO, BLANCO, TABULACION, NUEVA_LINEA, RETORNO,
		DOS_PUNTOS, IGUAL, MAYOR, MENOR, MENOS, MAS, BARRA,
		ASTERISCO, ACENTO_CIRCUNFLEJO, GUION_BAJO, AMPERSAND,
		PUNTO_Y_COMA, COMA, PARENTESIS_ABIERTO, PARENTESIS_CERRADO,
		CORCHETE_ABIERTO, CORCHETE_CERRADO, LLAVE_ABIERTA, LLAVE_CERRADA, COMILLA, INVALIDO, FIN_ARCHIVO;
	}
	
	
	private char c;
	private TipoSimbolo t;
	
	public Simbolo(char c) {
		this.c = c;
		if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')){
			this.t = TipoSimbolo.LETRA;
		}else if (c >= '0' && c <= '9'){
			this.t = TipoSimbolo.DIGITO;
		}else if (c == ' '){
			this.t = TipoSimbolo.BLANCO;
		}else if (c == '\t'){
			this.t = TipoSimbolo.TABULACION;
		}else if (c == '\n'){
			this.t = TipoSimbolo.NUEVA_LINEA;
		}else if (c == '\r'){
			this.t = TipoSimbolo.RETORNO;
		}else if (c == ':'){
			this.t = TipoSimbolo.DOS_PUNTOS;
		}else if (c == '='){
			this.t = TipoSimbolo.IGUAL;			
		}else if (c == '>'){
			this.t = TipoSimbolo.MAYOR;			
		}else if (c == '<'){
			this.t = TipoSimbolo.MENOR;			
		}else if (c == '-'){
			this.t = TipoSimbolo.MENOS;			
		}else if (c == '+'){
			this.t = TipoSimbolo.MAS;			
		}else if (c == '/'){
			this.t = TipoSimbolo.BARRA;			
		}else if (c == '*'){
			this.t = TipoSimbolo.ASTERISCO;			
		}else if (c == '^'){
			this.t = TipoSimbolo.ACENTO_CIRCUNFLEJO;			
		}else if (c == '_'){
			this.t = TipoSimbolo.GUION_BAJO;			
		}else if (c == '&'){
			this.t = TipoSimbolo.AMPERSAND;			
		}else if (c == ';'){
			this.t = TipoSimbolo.PUNTO_Y_COMA;			
		}else if (c == ','){
			this.t = TipoSimbolo.COMA;			
		}else if (c == '('){
			this.t = TipoSimbolo.PARENTESIS_ABIERTO;			
		}else if (c == ')'){
			this.t = TipoSimbolo.PARENTESIS_CERRADO;			
		}else if (c == '['){
			this.t = TipoSimbolo.CORCHETE_ABIERTO;			
		}else if (c == ']'){
			this.t = TipoSimbolo.CORCHETE_CERRADO;			
		}else if (c == '{'){
			this.t = TipoSimbolo.LLAVE_ABIERTA;			
		}else if (c == '}'){
			this.t = TipoSimbolo.LLAVE_CERRADA;			
		}else if (c == '\''){
			this.t = TipoSimbolo.COMILLA;			
		}else {
			this.t = TipoSimbolo.INVALIDO;
		}	
	}

	public TipoSimbolo getTipo() {
		return this.t;
	}
	
	public char getCaracter() {
		return this. c;
	}
	
	public String toString() {
		if (this.t == TipoSimbolo.LETRA)
			return "Letra";
		else if (this.t == TipoSimbolo.DIGITO){
			return "Digito";
		}else if (this.t == TipoSimbolo.BLANCO){
			return "Blanco";
		}else if (this.t == TipoSimbolo.TABULACION){
			return "Tabulacion";
		}else if (this.t == TipoSimbolo.NUEVA_LINEA){
			return "NuevaLinea";
		}else if (this.t == TipoSimbolo.RETORNO){
			return "Retorno";
		}else if (this.t == TipoSimbolo.DOS_PUNTOS){
			return "Dos Puntos";
		}else if (this.t == TipoSimbolo.IGUAL){
			return "Igual";
		}else if (this.t == TipoSimbolo.MAYOR){
			return "Mayor";
		}else if (this.t == TipoSimbolo.MENOR){
			return "Menor";
		}else if (this.t == TipoSimbolo.MENOS){
			return "Menos";
		}else if (this.t == TipoSimbolo.MAS){
			return "Mas";
		}else if (this.t == TipoSimbolo.BARRA){
			return "Barra";
		}else if (this.t == TipoSimbolo.ASTERISCO){
			return "Asterisco";
		}else if (this.t == TipoSimbolo.ACENTO_CIRCUNFLEJO){
			return "Acento Circunflejo";
		}else if (this.t == TipoSimbolo.GUION_BAJO){
			return "Guion Bajo";
		}else if (this.t == TipoSimbolo.AMPERSAND){
			return "Ampersand";
		}else if (this.t == TipoSimbolo.PUNTO_Y_COMA){
			return "Punto y coma";
		}else if (this.t == TipoSimbolo.COMA){
			return "Coma";
		}else if (this.t == TipoSimbolo.PARENTESIS_ABIERTO){
			return "Parentesis abierto";
		}else if (this.t == TipoSimbolo.PARENTESIS_CERRADO){
			return "Parentesis cerrado";
		}else if (this.t == TipoSimbolo.CORCHETE_ABIERTO){
			return "Corchete abierto";
		}else if (this.t == TipoSimbolo.CORCHETE_CERRADO){
			return "Corchete cerrado";
		}else if (this.t == TipoSimbolo.LLAVE_ABIERTA){
			return "Llave abierta";
		}else if (this.t == TipoSimbolo.LLAVE_CERRADA){
			return "Llave cerrada";
		}else if (this.t == TipoSimbolo.COMILLA){
			return "Comilla";
		}else
			return "Invalido";
	}
		
}
