package generaciónASM;

import arbol.sintactico.ArbolAbs;

public class Registro {

	private String nombre;
	private ArbolAbs operando;
	
	public Registro(String nombre) {
		super();
		this.nombre = nombre;
	}

	public boolean isLibre(){
		return operando == null;
	}
	
	public void liberar(){
		operando = null;
	}
	
	public void setOperando(ArbolAbs operando) {
		this.operando = operando;
	}

	public ArbolAbs getOperando(){
		return operando;
	}

	public String getName() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
