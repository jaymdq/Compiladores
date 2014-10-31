package arbol.sintactico;

import generaciónASM.CodigoAssembler;
import generaciónASM.RegisterManager;
import generaciónASM.Registro;
import proyecto.ElementoTS;

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
			return operacion +"\n¦   IZQ " + izquierdo.toString(espacios) + "\n¦   DER " + derecho.toString(espacios);
		else
			return operacion +"\n¦   IZQ " + izquierdo.toString(espacios) + "\n¦   DER NULL";
	}
	
	@Override
	public String toString(Integer espacios) {
		espacios++;
		String aux = "";
		for (int i = 0; i < espacios; i++){
			aux+="¦   ";
		}
		if (derecho != null)
			return operacion + "\n"+aux+"IZQ " + izquierdo.toString(espacios) + "\n"+aux+"DER " + derecho.toString(espacios);
		else
			return operacion + "\n"+aux+"IZQ " + izquierdo.toString(espacios) + "\n"+aux+"DER NULL";
	}

	@Override
	public ElementoTS.TIPOS getTipo() {
		ElementoTS.TIPOS tipo_izq = this.izquierdo.getTipo();
		ElementoTS.TIPOS tipo_der = this.derecho.getTipo();
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
		ArbolAbs der = null;
		if (derecho != null)
			derecho.clone();
		Arbol salida = new Arbol(operacion,izq,der);
		return salida;
	}
	
	public ArbolAbs dameMasDerecho(){
		return (this.derecho == null) ? this : ((Arbol) this.derecho).dameMasDerecho();
	}
	
	public ArbolAbs getArbolConHijosMasIzq(){
		ArbolAbs salida = null;
		if (this.izquierdo.esHoja() && this.derecho.esHoja())
			salida = this;
		else{
			salida = this.izquierdo.getArbolConHijosMasIzq();
			if (salida == null)
				salida = this.derecho.getArbolConHijosMasIzq();
		}
		
		return salida;
	}
	
	public boolean esHoja(){
		return false;
	}

	@Override
	public String getName() {
		return "intermedio";
	}

	@Override
	public void generarAssembler(CodigoAssembler codigo) {
		if (izquierdo != null)
			izquierdo.generarAssembler(codigo);
		
		if (derecho != null)
			derecho.generarAssembler(codigo);
		
		// TODO Auto-generated method stub
		String oper = null;
		boolean conmutativa = false;
		int bits = 16;
		//if ()
		
		if (operacion.equals("Suma \"+\"")){
			conmutativa = true;
			oper = "ADD";
			System.out.println("[ARBOL] Crear Suma");
		}else if (operacion.equals("Resta \"-\"")){
			conmutativa = false;
			oper = "SUB";
			System.out.println("[ARBOL] Crear Resta");
		}else {
			return;
		}
		
		/* ---------------------------------------------------------------------------------- */
		
		RegisterManager regManager = codigo.getRegManager();
		Registro regIzq = regManager.findRegistro(izquierdo);
		Registro regDer = regManager.findRegistro(derecho);
		
		String op1, op2;
		Registro r;
		
		if (regIzq == null &&  regDer == null){
			// Situacion 1:	Operacion entre 2 variables y/o constantes
			System.out.println("Situacion 1: Operacion entre 2 variables y/o constantes");
			
			r = regManager.getRegistroLibre(izquierdo);							// Obtener registro libre
			op1 = r.getName(); op2 = derecho.getName();							// Setear operandos
		}else if (regIzq != null && regDer == null){
			// Situacion 2: Operacion entre un registro y una variable o constante
			System.out.println("Situacion 2: Operacion entre un registro y una variable o constante");

			r = regIzq;
			op1 = regIzq.getName(); op2 = derecho.getName();
		}else if (regIzq != null && regDer != null){
			// Situacion 3: Operacion entre dos registros
			System.out.println("Situacion 3: Operacion entre dos registros");

			r = regIzq;
			op1 = regIzq.getName(); op2 = regDer.getName();
			regDer.liberar();
		}else if (conmutativa){
			// Situacion 4.a: Operacion conmutativa entre una variable (o constante) y un registro
			System.out.println("Situacion 4.a: Operacion conmutativa entre una variable (o constante) y un registro");
			
			r = regDer;
			op1 = regDer.getName(); op2 = izquierdo.getName();
		}else{
			// Situacion 4.b: Operacion no conmutativa entre una variable (o constante) y un registro
			System.out.println("Situacion 4.b: Operacion no conmutativa entre una variable (o constante) y un registro");
			
			r = regManager.getRegistroLibre(izquierdo);							// Obtener registro libre
			op1 = r.getName(); op2 = regDer.getName();							// Setear operandos
			regDer.liberar();
		}

		/* ---------------------------------------------------------------------------------- */
		
		codigo.agregarSentencia(oper, op1, op2);								// Realizar operacion
		r.setOperando(this);													// Actualizar referencia al resultado
		
		//Verificar Overflow + Entero/Entero sin signo + distintos tipos

	}
}
