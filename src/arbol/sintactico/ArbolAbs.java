package arbol.sintactico;

import generaciónASM.CodigoAssembler;
import proyecto.ElementoTS;
import proyecto.ElementoTS.TIPOS;

public interface ArbolAbs {

	public String toString();
	public TIPOS getTipo();
	public String toString(Integer espacios);
	public ArbolAbs clone();
	public boolean esHoja();
	public ArbolAbs getArbolConHijosMasIzq();
	public String getName();
	public void generarAssembler(CodigoAssembler codigo);
	public boolean is16bits();
	public void convertirATipo(ElementoTS.TIPOS tipo);
}
