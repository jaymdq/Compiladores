package proyecto;

public class ElementoTS {
	
	
	public enum TIPOS { 
		ENTERO, ENTERO_LSS, VECTOR_ENTERO, VECTOR_ENTERO_LSS, CADENA_MULTILINEA, NO_DECLARADO;
		
		public String toString(){
			if (this.equals(ENTERO))
				return "Entero";
			else if (this.equals(ENTERO_LSS))
				return "Entero LSS";
			else if (this.equals(VECTOR_ENTERO))
				return "Vector de enteros";
			else if (this.equals(VECTOR_ENTERO_LSS))
				return "Vector de enteros lss";
			else if (this.equals(CADENA_MULTILINEA))
				return "Cadena Multilinea";			
			else if (this.equals(NO_DECLARADO))
				return "No Declarado";	
			else
				return super.toString();
		}
	}
	
	public enum USOS  { 
		CONSTANTE, VARIABLE, ARREGLO;
		
		public String toString(){
			if (this.equals(CONSTANTE))
				return "Constante";
			else if (this.equals(VARIABLE))
				return "Variable";
			else if (this.equals(ARREGLO))
				return "Arreglo";
			else
				return super.toString();
		}
	
	}
		
	private TIPOS tipo;
	private USOS uso;
	private Integer lim_inf;
	private Integer lim_sup;
	private Token token;
	
	public ElementoTS(){
		this.tipo = null;
		this.uso = null;
		this.lim_inf = null;
		this.lim_sup = null;
		this.token = null;
	}

	public ElementoTS(Token t){
		this.tipo = null;
		this.uso = null;
		this.lim_inf = null;
		this.lim_sup = null;
		this.token = t;
	}

	public Integer getLim_sup() {
		return lim_sup;
	}
	public void setLim_sup(Integer lim_sup) {
		this.lim_sup = lim_sup;
	}
	public Integer getLim_inf() {
		return lim_inf;
	}
	public void setLim_inf(Integer lim_inf) {
		this.lim_inf = lim_inf;
	}
	
	public TIPOS getTipo() {
		return tipo;
	}
	public void setTipo(TIPOS tipo) {
		this.tipo = tipo;
	}
	public USOS getUso() {
		return uso;
	}
	public void setUso(USOS uso) {
		this.uso = uso;
	}
	
	//Ver si de verdad poner el token
		public Token getToken() {
			return token;
		}
		public void setToken(Token token) {
			this.token = token;
		}
	
}
