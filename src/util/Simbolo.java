package util;

public class Simbolo {
	public enum TipoSimbolo {
		LETRA, DIGITO, BLANCO, INVALIDO;
		
		@Override
		public String toString(){
			if (this.equals(TipoSimbolo.LETRA))
				return "Letra";
			else if (this.equals(TipoSimbolo.DIGITO)){
				return "Digito";
			}else if (this.equals(TipoSimbolo.BLANCO)){
				return "Blanco";
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
		}else if (c == ' '){
			tipo = TipoSimbolo.BLANCO;
		}else {
			tipo = TipoSimbolo.INVALIDO;			
		}
		
	}

	public TipoSimbolo getTipo(){
		return tipo;
	}
	
}
