package Arbol2;

public class Arbol implements ArbolAbs {

	private ArbolAbs izquierdo;
	private ArbolAbs derecho;
	
	public Arbol(ArbolAbs izquierdo, ArbolAbs derecho){
		this.izquierdo = izquierdo;
		this.derecho = derecho;
	}
	
	@Override
	public String toString() {
		return izquierdo.toString() + "\n" + derecho.toString() + "\n";
	}

	@Override
	public String getTipo() {
		String tipo_izq = this.izquierdo.getTipo();
		String tipo_der = this.derecho.getTipo();
		return (tipo_izq.equals(tipo_der) ? tipo_izq : null);
	}

}
