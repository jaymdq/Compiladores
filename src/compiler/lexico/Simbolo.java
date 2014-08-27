package compiler.lexico;

public class Simbolo {
	public enum TipoSimbolo {
		LETRA, DIGITO, BLANCO, TABULACION, NUEVA_LINEA, RETORNO,
		DOS_PUNTOS, IGUAL, MAYOR, MENOR, MENOS, MAS, BARRA,
		ASTERISCO, ACENTO_CIRCUNFLEJO, GUION_BAJO, AMPERSAND,
		PUNTO_Y_COMA, COMA, PARENTESIS_ABIERTO, PARENTESIS_CERRADO,
		CORCHETE_ABIERTO, CORCHETE_CERRADO, LLAVE_ABIERTA, LLAVE_CERRADA, COMILLA, INVALIDO;
		
		@Override
		public String toString(){
			if (this.equals(TipoSimbolo.LETRA))
				return "Letra";
			else if (this.equals(TipoSimbolo.DIGITO)){
				return "Digito";
			}else if (this.equals(TipoSimbolo.BLANCO)){
				return "Blanco";
			}else if (this.equals(TipoSimbolo.TABULACION)){
				return "Tabulacion";
			}else if (this.equals(TipoSimbolo.NUEVA_LINEA)){
				return "NuevaLinea";
			}else if (this.equals(TipoSimbolo.RETORNO)){
				return "Retorno";
			}else if (this.equals(TipoSimbolo.DOS_PUNTOS)){
				return "Dos Puntos";
			}else if (this.equals(TipoSimbolo.IGUAL)){
				return "Igual";
			}else if (this.equals(TipoSimbolo.MAYOR)){
				return "Mayor";
			}else if (this.equals(TipoSimbolo.MENOR)){
				return "Menor";
			}else if (this.equals(TipoSimbolo.MENOS)){
				return "Menos";
			}else if (this.equals(TipoSimbolo.MAS)){
				return "Mas";
			}else if (this.equals(TipoSimbolo.BARRA)){
				return "Barra";
			}else if (this.equals(TipoSimbolo.ASTERISCO)){
				return "Asterisco";
			}else if (this.equals(TipoSimbolo.ACENTO_CIRCUNFLEJO)){
				return "Acento Circunflejo";
			}else if (this.equals(TipoSimbolo.GUION_BAJO)){
				return "Guion Bajo";
			}else if (this.equals(TipoSimbolo.AMPERSAND)){
				return "Ampersand";
			}else if (this.equals(TipoSimbolo.PUNTO_Y_COMA)){
				return "Punto y coma";
			}else if (this.equals(TipoSimbolo.COMA)){
				return "Coma";
			}else if (this.equals(TipoSimbolo.PARENTESIS_ABIERTO)){
				return "Parentesis abierto";
			}else if (this.equals(TipoSimbolo.PARENTESIS_CERRADO)){
				return "Parentesis cerrado";
			}else if (this.equals(TipoSimbolo.CORCHETE_ABIERTO)){
				return "Corchete abierto";
			}else if (this.equals(TipoSimbolo.CORCHETE_CERRADO)){
				return "Corchete cerrado";
			}else if (this.equals(TipoSimbolo.LLAVE_ABIERTA)){
				return "Llave abierta";
			}else if (this.equals(TipoSimbolo.LLAVE_CERRADA)){
				return "Llave cerrada";
			}else if (this.equals(TipoSimbolo.COMILLA)){
				return "Comilla";
			}else
				return "Invalido";
		}
	}
	
	private char c;
	private TipoSimbolo tipo;
	
	public Simbolo(char c){
		this.c = c;
		
		if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')){
			tipo = TipoSimbolo.LETRA;
		}else if (c >= '0' && c <= '9'){
			tipo = TipoSimbolo.DIGITO;
		}else if (c == ' '){
			tipo = TipoSimbolo.BLANCO;
		}else if (c == '\t'){
			tipo = TipoSimbolo.TABULACION;
		}else if (c == '\n'){
			tipo = TipoSimbolo.NUEVA_LINEA;
		}else if (c == '\r'){
			tipo = TipoSimbolo.RETORNO;
		}else if (c == ':'){
			tipo = TipoSimbolo.DOS_PUNTOS;
		}else if (c == '='){
			tipo = TipoSimbolo.IGUAL;			
		}else if (c == '>'){
			tipo = TipoSimbolo.MAYOR;			
		}else if (c == '<'){
			tipo = TipoSimbolo.MENOR;			
		}else if (c == '-'){
			tipo = TipoSimbolo.MENOS;			
		}else if (c == '+'){
			tipo = TipoSimbolo.MAS;			
		}else if (c == '/'){
			tipo = TipoSimbolo.BARRA;			
		}else if (c == '*'){
			tipo = TipoSimbolo.ASTERISCO;			
		}else if (c == '^'){
			tipo = TipoSimbolo.ACENTO_CIRCUNFLEJO;			
		}else if (c == '_'){
			tipo = TipoSimbolo.GUION_BAJO;			
		}else if (c == '&'){
			tipo = TipoSimbolo.AMPERSAND;			
		}else if (c == ';'){
			tipo = TipoSimbolo.PUNTO_Y_COMA;			
		}else if (c == ','){
			tipo = TipoSimbolo.COMA;			
		}else if (c == '('){
			tipo = TipoSimbolo.PARENTESIS_ABIERTO;			
		}else if (c == ')'){
			tipo = TipoSimbolo.PARENTESIS_CERRADO;			
		}else if (c == '['){
			tipo = TipoSimbolo.CORCHETE_ABIERTO;			
		}else if (c == ']'){
			tipo = TipoSimbolo.CORCHETE_CERRADO;			
		}else if (c == '{'){
			tipo = TipoSimbolo.LLAVE_ABIERTA;			
		}else if (c == '}'){
			tipo = TipoSimbolo.LLAVE_CERRADA;			
		}else if (c == '\''){
			tipo = TipoSimbolo.COMILLA;			
		}else {
			tipo = TipoSimbolo.INVALIDO;
		}
		
	}

	public TipoSimbolo getTipo(){
		return tipo;
	}
	
	public char getCaracter(){
		return c;
	}
	
}
