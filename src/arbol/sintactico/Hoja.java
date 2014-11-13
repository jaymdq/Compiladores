package arbol.sintactico;

import generaciónASM.CodigoAssembler;
import proyecto.ElementoTS;
import proyecto.ElementoTS.TIPOS;
import proyecto.ElementoTS.USOS;

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
		String salida = null;
		if (contenido.getUso() == USOS.VARIABLE) 
			salida = "_" + contenido.getToken().getLexema();

		if (contenido.getUso() == USOS.CONSTANTE)
			salida = contenido.getToken().getLexema();

		if (contenido.getUso() == USOS.ARREGLO) 
			salida = contenido.getToken().getLexema();

		
		return salida;
	}

	@Override
	public TIPOS getTipo() {
		return contenido.getTipo();
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
	
	@Override
	public boolean is16bits() {
		return (getTipo().equals(TIPOS.ENTERO) || getTipo().equals(TIPOS.VECTOR_ENTERO));
	}
}
