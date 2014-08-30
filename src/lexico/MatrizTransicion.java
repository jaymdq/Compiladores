package lexico;

import java.util.HashMap;
import java.util.Map;

import proyecto.Simbolo;
import proyecto.Simbolo.TipoSimbolo;

public class MatrizTransicion {

	public enum Estado {
		INICIAL, FINAL, UNO, DOS, TRES, CUATRO, CINCO, SEIS, SIETE, OCHO, NUEVE, DIEZ;
	}
		
	Map<Estado, Map<TipoSimbolo, Transicion>> matriz;
	Map<Estado, Transicion> defaults;
	
	public MatrizTransicion(){
		matriz = new HashMap<Estado, Map<TipoSimbolo,Transicion>>();
		defaults = new HashMap<Estado, Transicion>();
	}
	
	public void setTransicion(Estado e, TipoSimbolo ts, Transicion t){
		// Verificar que exista la fila
		if(!matriz.containsKey(e)){
			matriz.put(e, new HashMap<TipoSimbolo, Transicion>());
		}
		matriz.get(e).put(ts, t);
	}
	
	public Transicion getTransicion(Estado e, Simbolo s){
		Transicion t = matriz.get(e).get(s.getTipo());
		if (t != null)
			return t;
		return defaults.get(e);
	}
	
	public void setDefault(Estado e,Transicion t) {
		defaults.put(e,t);
	}
}
