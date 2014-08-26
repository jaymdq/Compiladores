package util;

import java.util.HashMap;
import java.util.Map;

import compiler.Compilador;
import util.Simbolo.TipoSimbolo;
import util.Token.TipoToken;

public class MatrizTransicion {

	public enum Estado {
		INICIAL, FINAL, UNO, DOS, TRES, CUATRO, CINCO, SEIS, SIETE, OCHO, NUEVE, DIEZ;
	}
		
	private Compilador comp;
	private Estado estado;	
	Map<Estado, Map<TipoSimbolo, Transicion>> matriz;
	Map<Estado, Transicion> defaults;
	
	public MatrizTransicion(Compilador comp){
		this.comp = comp;
		
		estado = Estado.INICIAL;
		matriz = new HashMap<Estado, Map<TipoSimbolo, Transicion>>();
		defaults = new HashMap<Estado, Transicion>();
		
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
	
	public void setDefaultTransicion(Estado actual, Transicion transicion){
		defaults.put(actual, transicion);
	}
	
	public Transicion doTransicion(Simbolo s){
		TipoSimbolo tipoSimbolo = s.getTipo();
		
		if (!matriz.containsKey(estado)){
			System.out.println("[ERROR] El estado actual no esta definido");
			return null;
		}
		
		Transicion transicion = matriz.get(estado).get(tipoSimbolo);
		if (transicion == null && defaults.containsKey(estado)){
			System.out.println("Transicion por defecto");
			transicion = defaults.get(estado);
		}
		
		if (transicion == null){
			System.out.println("[ERROR] La transicion en el estado actual no esta definida para " + tipoSimbolo);
			return null;
		}
		
		// Actualizar estado
		System.out.print("Transicion: " + estado + " -> " + tipoSimbolo + " -> ");
		estado = transicion.getNuevoEstado();
		System.out.println(estado);
		
		// Realizar accion semantica
		transicion.ejecutarAccionSemantica(s);
		
		return transicion;
	}
	
	public void volverAInicio(){
		estado = Estado.INICIAL;
	}
	
	private void initMatriz(){
		// Estado Inicial
		
		// Descartables
		setTransicion(Estado.INICIAL, TipoSimbolo.BLANCO, new Transicion(Estado.INICIAL));
		setTransicion(Estado.INICIAL, TipoSimbolo.TABULACION, new Transicion(Estado.INICIAL));
		setTransicion(Estado.INICIAL, TipoSimbolo.NUEVA_LINEA, new Transicion(Estado.INICIAL));
		setTransicion(Estado.INICIAL, TipoSimbolo.RETORNO, new Transicion(Estado.INICIAL));
		
		// Directos
		setTransicion(Estado.INICIAL, TipoSimbolo.MAS, new Transicion(Estado.FINAL, new ASDirecto(comp, TipoToken.OP_MAS)));
		setTransicion(Estado.INICIAL, TipoSimbolo.MENOS, new Transicion(Estado.FINAL, new ASDirecto(comp, TipoToken.OP_MENOS)));
		setTransicion(Estado.INICIAL, TipoSimbolo.BARRA, new Transicion(Estado.FINAL, new ASDirecto(comp, TipoToken.OP_DIVIDIDO)));
		setTransicion(Estado.INICIAL, TipoSimbolo.ASTERISCO, new Transicion(Estado.FINAL, new ASDirecto(comp, TipoToken.OP_POR)));
		setTransicion(Estado.INICIAL, TipoSimbolo.PARENTESIS_ABIERTO, new Transicion(Estado.FINAL, new ASDirecto(comp, TipoToken.PARENTESIS_ABIERTO)));
		setTransicion(Estado.INICIAL, TipoSimbolo.PARENTESIS_CERRADO, new Transicion(Estado.FINAL, new ASDirecto(comp, TipoToken.PARENTESIS_CERRADO)));
		setTransicion(Estado.INICIAL, TipoSimbolo.LLAVE_ABIERTA, new Transicion(Estado.FINAL, new ASDirecto(comp, TipoToken.LLAVE_ABIERTA)));
		setTransicion(Estado.INICIAL, TipoSimbolo.LLAVE_CERRADA, new Transicion(Estado.FINAL, new ASDirecto(comp, TipoToken.LLAVE_CERRADA)));
		setTransicion(Estado.INICIAL, TipoSimbolo.COMA, new Transicion(Estado.FINAL, new ASDirecto(comp, TipoToken.COMA)));
		setTransicion(Estado.INICIAL, TipoSimbolo.PUNTO_Y_COMA, new Transicion(Estado.FINAL, new ASDirecto(comp, TipoToken.PUNTO_Y_COMA)));
		setTransicion(Estado.INICIAL, TipoSimbolo.CORCHETE_ABIERTO, new Transicion(Estado.FINAL, new ASDirecto(comp, TipoToken.CORCHETE_ABIERTO)));
		setTransicion(Estado.INICIAL, TipoSimbolo.CORCHETE_CERRADO, new Transicion(Estado.FINAL, new ASDirecto(comp, TipoToken.CORCHETE_CERRADO)));
		
		// Indirectos
		setTransicion(Estado.INICIAL, TipoSimbolo.LETRA, new Transicion(Estado.UNO, new AS1(comp)));
		setTransicion(Estado.INICIAL, TipoSimbolo.DIGITO, new Transicion(Estado.DOS, new AS4(comp)));
		setTransicion(Estado.INICIAL, TipoSimbolo.DOS_PUNTOS, new Transicion(Estado.TRES));
		setTransicion(Estado.INICIAL, TipoSimbolo.MAYOR, new Transicion(Estado.CUATRO));
		setTransicion(Estado.INICIAL, TipoSimbolo.MENOR, new Transicion(Estado.CINCO));
		setTransicion(Estado.INICIAL, TipoSimbolo.ACENTO_CIRCUNFLEJO, new Transicion(Estado.SEIS));
		setTransicion(Estado.INICIAL, TipoSimbolo.ASTERISCO, new Transicion(Estado.SIETE));
		setTransicion(Estado.INICIAL, TipoSimbolo.COMILLA, new Transicion(Estado.NUEVE));
		
		// Estado 1
		setTransicion(Estado.UNO, TipoSimbolo.LETRA, new Transicion(Estado.UNO, new AS2(comp)));
		setTransicion(Estado.UNO, TipoSimbolo.DIGITO, new Transicion(Estado.UNO, new AS2(comp)));
		setTransicion(Estado.UNO, TipoSimbolo.GUION_BAJO, new Transicion(Estado.UNO, new AS2(comp)));
		setTransicion(Estado.UNO, TipoSimbolo.AMPERSAND, new Transicion(Estado.UNO, new AS2(comp)));
		setDefaultTransicion(Estado.UNO, new Transicion(Estado.FINAL, new AS3(comp)));
		
		// Estado 2
		setTransicion(Estado.DOS, TipoSimbolo.DIGITO, new Transicion(Estado.DOS, new AS2(comp)));
		setDefaultTransicion(Estado.DOS, new Transicion(Estado.FINAL, new AS5(comp)));
		
		// Estado 3
		setTransicion(Estado.TRES, TipoSimbolo.IGUAL, new Transicion(Estado.FINAL, new ASDirecto(comp, TipoToken.ASIGNACION)));
		
		// Estado 4
		setTransicion(Estado.CUATRO, TipoSimbolo.IGUAL, new Transicion(Estado.FINAL, new ASDirecto(comp, TipoToken.COMP_MAYOR_IGUAL)));
		setDefaultTransicion(Estado.CUATRO, new Transicion(Estado.FINAL, new ASDirecto(comp, TipoToken.COMP_MAYOR, true)));
		
		// Estado 5
		setTransicion(Estado.CINCO, TipoSimbolo.IGUAL, new Transicion(Estado.FINAL, new ASDirecto(comp, TipoToken.COMP_MENOR_IGUAL)));
		setDefaultTransicion(Estado.CINCO, new Transicion(Estado.FINAL, new ASDirecto(comp, TipoToken.COMP_MENOR, true)));
		
		// Estado 6
		setTransicion(Estado.SEIS, TipoSimbolo.IGUAL, new Transicion(Estado.FINAL, new ASDirecto(comp, TipoToken.COMP_DISTINTO)));
		
		// Estado 7
		setTransicion(Estado.SIETE, TipoSimbolo.ASTERISCO, new Transicion(Estado.OCHO));
		setDefaultTransicion(Estado.SIETE, new Transicion(Estado.FINAL, new ASDirecto(comp, TipoToken.OP_POR, true)));
		
		// Estado 8
		setTransicion(Estado.OCHO, TipoSimbolo.NUEVA_LINEA, new Transicion(Estado.INICIAL));
		setDefaultTransicion(Estado.OCHO, new Transicion(Estado.OCHO));
		
		// Estado 9
		setTransicion(Estado.NUEVE, TipoSimbolo.NUEVA_LINEA, new Transicion(Estado.DIEZ));
		//setTransicion(Estado.NUEVE, TipoSimbolo.COMILLA, new Transicion(Estado.FINAL, new AS(comp)));
		//setDefaultTransicion(Estado.NUEVE, new Transicion(Estado.NUEVE, new AS(comp)));
		
		// Estado 10
		setTransicion(Estado.DIEZ, TipoSimbolo.BLANCO, new Transicion(Estado.DIEZ));
		setTransicion(Estado.DIEZ, TipoSimbolo.TABULACION, new Transicion(Estado.DIEZ));
		setTransicion(Estado.DIEZ, TipoSimbolo.NUEVA_LINEA, new Transicion(Estado.DIEZ));
		setTransicion(Estado.DIEZ, TipoSimbolo.MAS, new Transicion(Estado.NUEVE));
	}
}
