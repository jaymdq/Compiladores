package arbol.sintactico;

import generaciónASM.CodigoAssembler;
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

	public String getName(){
		return "_" + contenido.getToken().getLexema();
	}
	
	@Override
	public ElementoTS.TIPOS getTipo() {
		return this.contenido.getTipo();
	}
	
	public ArbolAbs clone(){
		Hoja salida = new Hoja(contenido);
		return salida;
	}

	public boolean esHoja(){
		return true;
	}

	@Override
	public ArbolAbs getArbolConHijosMasIzq() {
		return null;
	}

	@Override
	public void generarAssembler(CodigoAssembler codigo) {
		// TODO Auto-generated method stub
	}
}
