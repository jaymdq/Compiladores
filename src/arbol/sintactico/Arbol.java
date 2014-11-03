package arbol.sintactico;

import generaciónASM.CodigoAssembler;
import generaciónASM.CodigoAssembler.Operacion;
import generaciónASM.RegisterManager;
import generaciónASM.Registro;
import proyecto.ElementoTS;
import proyecto.ElementoTS.TIPOS;

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

		if (tipo_izq == ElementoTS.TIPOS.VECTOR_ENTERO )
			tipo_izq = ElementoTS.TIPOS.ENTERO;
		if (tipo_izq == ElementoTS.TIPOS.VECTOR_ENTERO_LSS )
			tipo_izq = ElementoTS.TIPOS.ENTERO_LSS;
		if (tipo_der == ElementoTS.TIPOS.VECTOR_ENTERO )
			tipo_der = ElementoTS.TIPOS.ENTERO;
		if (tipo_der == ElementoTS.TIPOS.VECTOR_ENTERO_LSS )
			tipo_der = ElementoTS.TIPOS.ENTERO_LSS;
		
		
		
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
		CodigoAssembler.Operacion oper = null;
		boolean conmutativa = false;
		boolean n16bits = true;
		boolean destFijo = false;
		//if ()
		
		if (operacion.equals("Suma \"+\"")){
			System.out.println("[ARBOL] Crear Suma");
			conmutativa = true;
			oper = Operacion.ADD;
		}else if (operacion.equals("Resta \"-\"")){
			System.out.println("[ARBOL] Crear Resta");
			conmutativa = false;
			oper = Operacion.SUB;
		}else if (operacion.equals("Multiplicación \"*\"")){
			System.out.println("[ARBOL] Crear Multiplicacion");
			conmutativa = true;
			destFijo = true;
			if (getTipo().equals(TIPOS.ENTERO_LSS)){
				oper = Operacion.MUL;
				n16bits = false;
			}else {
				oper = Operacion.IMUL;
			}
		}else if (operacion.equals("División \"/\"")){
			System.out.println("[ARBOL] Crear Division");
			conmutativa = false;
			destFijo = true;
			if (getTipo().equals(TIPOS.ENTERO_LSS)){
				oper = Operacion.DIV;
				n16bits = false;
			}else {
				oper = Operacion.IDIV;
			}
		}else {
			return;
		}
		
		/* ---------------------------------------------------------------------------------- */
		
		RegisterManager regManager = codigo.getRegManager();
		Registro regIzq = regManager.findRegistro(izquierdo);
		Registro regDer = regManager.findRegistro(derecho);
		
		String op1, op2;
		Registro r;
		
		if (oper.equals(Operacion.MUL) || oper.equals(Operacion.IMUL)){
			r = regManager.ocuparRegistro(new Registro("EAX", "AX"), izquierdo, n16bits); 		// Parte baja: operando1
			Registro r2 = regManager.ocuparRegistro(new Registro("EDX", "DX"), 0, n16bits); 	// Parte alta: 0 (evitar perdida de datos)
			op1 = r.getName(n16bits);
			op2 = derecho.getName();
			
			codigo.agregarSentencia(oper, op1, op2);											// Realizar operacion
			r.setOperando(this);
			r2.liberar();																		// Liberar segundo registro
			// Verificar overflow
		}else if (oper.equals(Operacion.DIV) || oper.equals(Operacion.IDIV)){
			r = regManager.ocuparRegistro(new Registro("EAX", "AX"), izquierdo, n16bits); 		// Parte baja: operando1
			Registro r2 = regManager.ocuparRegistro(new Registro("EDX", "DX"), 0, n16bits); 	// Parte alta: 0 (evitar calculo erroneo)
			
			if (regDer == null)
				regDer = regManager.ocuparRegistroLibre(derecho, n16bits);

			op2 = regDer.getName(n16bits);
			
			codigo.agregarSentencia(oper, op2);													// Realizar operacion
			r.setOperando(this);
			
			r2.liberar();																		// Liberar segundo registro
			regDer.liberar();

		}else if (oper.equals(Operacion.ADD) || oper.equals(Operacion.SUB)){
			if (regIzq == null &&  regDer == null){
				// Situacion 1:	Operacion entre 2 variables y/o constantes
				System.out.println("Situacion 1: Operacion entre 2 variables y/o constantes");
				
				r = regManager.ocuparRegistroLibre(izquierdo, n16bits);							// Obtener registro libre
				op1 = r.getName(n16bits); op2 = derecho.getName();							// Setear operandos
			}else if (regIzq != null && regDer == null){
				// Situacion 2: Operacion entre un registro y una variable o constante
				System.out.println("Situacion 2: Operacion entre un registro y una variable o constante");
	
				r = regIzq;
				op1 = regIzq.getName(n16bits); op2 = derecho.getName();
			}else if (regIzq != null && regDer != null){
				// Situacion 3: Operacion entre dos registros
				System.out.println("Situacion 3: Operacion entre dos registros");
	
				r = regIzq;
				op1 = regIzq.getName(n16bits); op2 = regDer.getName(n16bits);
				regDer.liberar();
			}else if (conmutativa){
				// Situacion 4.a: Operacion conmutativa entre una variable (o constante) y un registro
				System.out.println("Situacion 4.a: Operacion conmutativa entre una variable (o constante) y un registro");
				
				r = regDer;
				op1 = regDer.getName(n16bits); op2 = izquierdo.getName();
			}else{
				// Situacion 4.b: Operacion no conmutativa entre una variable (o constante) y un registro
				System.out.println("Situacion 4.b: Operacion no conmutativa entre una variable (o constante) y un registro");
				
				r = regManager.ocuparRegistroLibre(izquierdo, n16bits);								// Obtener registro libre
				op1 = r.getName(n16bits); op2 = regDer.getName(n16bits);							// Setear operandos
				regDer.liberar();
			}
		
			/* ---------------------------------------------------------------------------------- */
			
			codigo.agregarSentencia(oper, op1, op2);								// Realizar operacion
			r.setOperando(this);													// Actualizar referencia al resultado

		}
	}
}
