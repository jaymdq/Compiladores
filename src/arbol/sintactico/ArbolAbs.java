package arbol.sintactico;

import proyecto.ElementoTS.TIPOS;

public interface ArbolAbs {

	public String toString();
	public TIPOS getTipo();
	public String toString(Integer espacios);
	public ArbolAbs clone();
	public boolean esHoja();
	public ArbolAbs getArbolConHijosMasIzq();
	public String getName();
}
