package arbol.sintactico;

public class Arbol implements ArbolAbs {

	private ArbolAbs izquierdo;
	private ArbolAbs derecho;
	private String operacion;
	
	public Arbol(String operacion,ArbolAbs izquierdo, ArbolAbs derecho){
		this.operacion = operacion;
		this.izquierdo = izquierdo;
		this.derecho = derecho;
	}
	
	@Override
	public String toString() {
		Integer espacios = 1;
		if (derecho != null)
			return operacion + "\n  IZQ-" + izquierdo.toString(espacios) + "\n  DER-" + derecho.toString(espacios);
		else
			return operacion + "\n  IZQ-" + izquierdo.toString(espacios) + "\n  DER- NULL";
	}
	
	@Override
	public String toString(Integer espacios) {
		espacios++;
		String aux = "";
		for (int i = 0; i < espacios; i++){
			aux+="  ";
		}
		if (derecho != null)
			return operacion + "\n"+aux+"IZQ-" + izquierdo.toString(espacios) + "\n"+aux+"DER-" + derecho.toString(espacios);
		else
			return operacion + "\n"+aux+"IZQ-" + izquierdo.toString(espacios) + "\n"+aux+"DER- NULL";
	}

	@Override
	public String getTipo() {
		String tipo_izq = this.izquierdo.getTipo();
		String tipo_der = this.derecho.getTipo();
		return (tipo_izq.equals(tipo_der) ? tipo_izq : null);
	}

	public String getOperacion() {
		return this.operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	
	public ArbolAbs getIzquierdo(){
		return this.izquierdo;
	}
	
	public ArbolAbs getDerecho(){
		return this.derecho;
	}
	
	public void setDerecho(ArbolAbs arbol){
		this.derecho = arbol;
	}

	public ArbolAbs clone(){
		ArbolAbs izq = izquierdo.clone();
		ArbolAbs der = derecho.clone();
		Arbol salida = new Arbol(operacion,izq,der);
		return salida;
	}
}
