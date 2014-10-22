package Arbol2;

import proyecto.ElementoTS;

public class Hoja implements ArbolAbs {

	private ElementoTS contenido;
	
	public Hoja(ElementoTS contenido){
		this.contenido = contenido;
	}
	
	@Override
	public String toString() {
		return "[" + contenido.getToken().getLexema() + "," + contenido.getTipo().toString() +"]";
	}

	public String toString(Integer espacios) {
		return "[" + contenido.getToken().getLexema() + "," + contenido.getTipo().toString() +"]";
	}

	@Override
	public String getTipo() {
		return this.contenido.getTipo().toString();
	}
	
	public ArbolAbs clone(){
		Hoja salida = new Hoja(contenido);
		return salida;
	}

}
