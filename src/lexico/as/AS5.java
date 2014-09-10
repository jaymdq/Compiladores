package lexico.as;

import excepciones.ExcepcionRangoEntero;
import proyecto.Proyecto;
import proyecto.Simbolo;
import proyecto.Token;


public class AS5 extends AccionSemantica {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void ejecutar(Token t, Simbolo s, Proyecto p) throws ExcepcionRangoEntero{


		//Por ahora solo enteros positivos..
		//0 a 2^32-1 = 4294967295L
		Long a = new Long(t.getLexema());
		if (a.compareTo(4294967295L) > 0)
			throw new ExcepcionRangoEntero();
		else if ( a > 32767){
			t.setTipo(Token.TipoToken.ENTERO_LSS);
			t = p.addTokenToTable(t);
		}else
			t = p.addTokenToTable(t);
			
		p.back();
		//Arreglar linea actual del proyecto..
		p.retrocederLinea();

	}

}
