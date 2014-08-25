package util;

import java.util.HashMap;
import java.util.Map;

import util.Simbolo.TipoSimbolo;

public class MatrizTransicion {

	public enum Estado {
		INICIAL, FINAL, UNO;
	}
		
	private Estado estado;	
	Map<Estado, Map<TipoSimbolo, Transicion>> matriz;
	
	public MatrizTransicion(){
		estado = Estado.INICIAL;
		matriz = new HashMap<Estado, Map<TipoSimbolo, Transicion>>();
		
		initMatriz();
	}
	
	public Estado getEstado() {
		return estado;
	}

	public void setTransicion(Estado inicial, TipoSimbolo tipoSimbolo, Transicion celda){
		// Verificar que exista la fila
		if(!matriz.containsKey(inicial)){
			matriz.put(inicial, new HashMap<TipoSimbolo, Transicion>());
		}
		
		matriz.get(inicial).put(tipoSimbolo, celda);
	}
	
	public Transicion doTransicion(TipoSimbolo tipoSimbolo){
		if (!matriz.containsKey(estado)){
			System.out.println("[ERROR] El estado actual no esta definido");
			return null;
		}
		
		Transicion transicion = matriz.get(estado).get(tipoSimbolo);
		if (transicion == null){
			System.out.println("[ERROR] La transicion en el estado actual no esta definida");
			return null;
		}
		
		// Actualizar estado
		System.out.print("Transicion: " + estado + " -> " + tipoSimbolo + " -> ");
		estado = transicion.getNuevoEstado();
		System.out.println(estado);
		
		// Realizar accion semantica
		transicion.ejecutarAccionSemantica();
		
		return transicion;
	}
	
	private void initMatriz(){
		setTransicion(Estado.INICIAL, TipoSimbolo.LETRA, new Transicion(Estado.UNO, new AS1()));
		setTransicion(Estado.INICIAL, TipoSimbolo.DIGITO, new Transicion(Estado.INICIAL));
		
		setTransicion(Estado.UNO, TipoSimbolo.LETRA, new Transicion(Estado.UNO));
		setTransicion(Estado.UNO, TipoSimbolo.BLANCO, new Transicion(Estado.FINAL));
		
	}
}
