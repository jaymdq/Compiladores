package util;

public class Simbolo {
	public enum TipoSimbolo {
		LETRA, DIGITO, BLANCO, TABULACION, NUEVA_LINEA, 
		DOS_PUNTOS, IGUAL, MAYOR, MENOR, INVALIDO;
		
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
			}/*else if (this.equals(TipoSimbolo.TABULACION)){
				return "Tabulacion";
			}else if (this.equals(TipoSimbolo.TABULACION)){
				return "Tabulacion";
			}*/else
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
		}else if (c == ':'){
			tipo = TipoSimbolo.DOS_PUNTOS;
		}else if (c == '='){
			tipo = TipoSimbolo.IGUAL;			
		}else if (c == '>'){
			tipo = TipoSimbolo.MAYOR;			
		}else if (c == '<'){
			tipo = TipoSimbolo.MENOR;			
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
