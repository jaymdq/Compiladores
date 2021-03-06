package generaciónASM;

import arbol.sintactico.ArbolAbs;


public class Registro {

	private String nombre16;
	private String nombre32;
	
	private ArbolAbs operando;
	private boolean inmediato = false;
	private boolean enMemoria = false;
	private RegisterManager regManager;
	
	public Registro(String nombre32, String nombre16, RegisterManager regManager) {
		super();
		this.nombre32 = nombre32;
		this.nombre16 = nombre16;
		this.regManager = regManager;
	}
	
	public Registro(String nombre32, String nombre16) {
		this(nombre32, nombre16, null);
	}
	
	public void setInmediato(){ inmediato = true; regManager.encolar(this); }
	
	public void setEnMemoria(boolean enMemoria){ 
		this.enMemoria = enMemoria; 
		regManager.encolar(this); // TODO No se si va aca
	}

	public boolean isLibre(){
		return operando == null && !inmediato;
	}
	
	public void liberar(){
		operando = null;
		inmediato = false;
		enMemoria = false;
		regManager.desencolar(this);
	}
	
	public void setOperando(ArbolAbs operando) {
		this.operando = operando;
		regManager.encolar(this);
	}
	
	public void setOperando(ArbolAbs operando, boolean enMemoria) {
		this.operando = operando;
		this.enMemoria = enMemoria;
		regManager.encolar(this);
	}

	public ArbolAbs getOperando(){
		return operando;
	}
	
	public String getName(boolean n16bits) {
		if (enMemoria)
			return operando.getName();
		
		if (n16bits){
			return nombre16;
		}
		
		return nombre32;
	}
	
	public String getName(boolean n16bits, boolean onlyString) {		
		if (n16bits){
			return nombre16;
		}
		
		return nombre32;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nombre16 == null) ? 0 : nombre16.hashCode());
		result = prime * result
				+ ((nombre32 == null) ? 0 : nombre32.hashCode());
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
		Registro other = (Registro) obj;
		if (nombre16 == null) {
			if (other.nombre16 != null)
				return false;
		} else if (!nombre16.equals(other.nombre16))
			return false;
		if (nombre32 == null) {
			if (other.nombre32 != null)
				return false;
		} else if (!nombre32.equals(other.nombre32))
			return false;
		return true;
	}
	
}
