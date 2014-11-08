package arbol.sintactico;

import generaciónASM.CodigoAssembler;
import generaciónASM.CodigoAssembler.Operacion;
import generaciónASM.GeneradorASM;
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

		//Este codigo permite disfrazar a una CTE de tipo ENTERO para poder operar con una ENTERLO_LSS
		if (izquierdo.esHoja() && derecho.esHoja()){
			if (izquierdo.getTipo() == ElementoTS.TIPOS.ENTERO && derecho.getTipo() == ElementoTS.TIPOS.ENTERO_LSS){
				if ( ! izquierdo.getName().startsWith("_")){
					tipo_izq = ElementoTS.TIPOS.ENTERO_LSS;
				}
			}
			if (izquierdo.getTipo() == ElementoTS.TIPOS.ENTERO_LSS && derecho.getTipo() == ElementoTS.TIPOS.ENTERO){
				if ( ! derecho.getName().startsWith("_")){
					tipo_der = ElementoTS.TIPOS.ENTERO_LSS;
				}
			}
		}



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
		if (this.derecho instanceof Hoja){
			return this;
		}
		return (this.derecho == null) ? this :  ((Arbol) this.derecho).dameMasDerecho();
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
		//return "intermedio";
		return this.operacion;
	}

	@Override
	public void generarAssembler(CodigoAssembler codigo) {

		CodigoAssembler.Operacion oper = null;
		boolean conmutativa = false;
		//CAMBIOS A 32
		boolean n16bits = false;
		boolean destFijo = false;

		//Declaración de los registros a utilizar
		RegisterManager regManager = codigo.getRegManager();
		String op1 = null, op2;
		Registro r;

		//IZQUIERDA #################################################################################

		//Iteración .. Se debe incluir la etiqueta antes que nada
		if (operacion.equals("Iteración")){
			String label = codigo.apilarEtiqueta();
			codigo.agregarEtiqueta(label);
		}

		//Se recorre el arbol
		if (izquierdo != null)
			izquierdo.generarAssembler(codigo);

		//MEDIO#######################################################################################

		//Selección
		if (operacion.equals("Selección")){
			//Se generó ya la comparación !
			//Se debe poner el salto y apilarlo
			//Se agrega el salto
			String comparador = ((Arbol) this.izquierdo).getOperacion().split(" ")[1];
			String etiqueta = codigo.apilarEtiqueta();

			n16bits = true;
			if (izquierdo.getTipo() == ElementoTS.TIPOS.ENTERO_LSS)
				n16bits = false;
			oper = getNegacionComparador(comparador,n16bits);

			//Se agrega la sentencia
			codigo.agregarSentencia(oper, etiqueta);
		}

		//Cuerpo
		if (operacion.equals("Cuerpo")){
			String etiquetaPrimera = codigo.desapilarEtiqueta();

			if (derecho != null){
				String etiqueta = codigo.apilarEtiqueta();
				oper = Operacion.JMP;

				//Se agrega la sentencia
				codigo.agregarSentencia(oper, etiqueta);

			}
			//Primer Label
			codigo.agregarEtiqueta(etiquetaPrimera);
		}

		//DERECHA ####################################################################################

		//Se recorre el arbol
		if (derecho != null)
			derecho.generarAssembler(codigo);



		//NODO MISMO-------------------------------------------------------------------------------------------

		Registro regIzq = regManager.findRegistro(izquierdo);
		Registro regDer = regManager.findRegistro(derecho);


		//Nodos que no generan problemas
		if (operacion.equals("Programa")){
			return;
		}
		if (operacion.equals("Sentencia")){
			return;
		}
		if (operacion.equals("Sentencia General")){
			return;
		}

		//Cuerpo
		if (operacion.equals("Cuerpo")){
			if (derecho!= null){
				String etiqueta = codigo.desapilarEtiqueta();
				codigo.agregarEtiqueta(etiqueta);
			}
			codigo.agregarLineaBlanco();
			return;
		}


		//Iteración salida poner etiqueta
		if (operacion.equals("Iteración")){
			//Se agrega el salto
			String comparador = ((Arbol) this.derecho).getOperacion().split(" ")[1];
			String etiqueta = codigo.desapilarEtiqueta();

			n16bits = true;
			if (derecho.getTipo() == ElementoTS.TIPOS.ENTERO_LSS)
				n16bits = false;
			oper = getNegacionComparador(comparador,n16bits);

			//Se agrega la sentencia
			codigo.agregarSentencia(oper, etiqueta);

			codigo.agregarLineaBlanco();
			return;
		}


		//Impresión
		if (operacion.equals("Impresión")){
			oper = Operacion.INVOKE;
			String referencia = GeneradorASM.getMapStringsASM().get(izquierdo.getName());
			codigo.agregarSentencia(oper, "MessageBox, NULL, addr " + referencia + ", addr " + referencia + ", MB_OK");	

			codigo.agregarLineaBlanco();
			return;
		}


		//Asignación
		if (operacion.equals("Asignación")){
			
			n16bits = false;
			oper = Operacion.MOV;
			op1 = izquierdo.getName();
			Registro aGuardar = regManager.findRegistro(derecho);
			if (aGuardar == null){
				//Moverlo
				aGuardar = regManager.ocuparRegistroLibre(derecho, n16bits);
			}
			n16bits = true;
			if (derecho.getTipo() == ElementoTS.TIPOS.ENTERO_LSS)
				n16bits = false;
			
			if (derecho.getName().startsWith("[")){
				codigo.agregarSentencia(Operacion.MOV, aGuardar.getName(n16bits), derecho.getName());
			}
			
			codigo.agregarSentencia(oper, op1, aGuardar.getName(n16bits));


			//Se libre todo!!
			regManager.liberarTodos();

			codigo.agregarLineaBlanco();

			return ;
		}


		//Comparación
		if (operacion.startsWith("Comparador")){
			//Operacion CMP
			oper = Operacion.CMP;

			if (regIzq == null){
				//Ocupar el izquierdo
				regIzq = regManager.ocuparRegistroLibre(izquierdo, n16bits);
			}

			if (regDer == null){
				//Ocupar el derecho
				regDer = regManager.ocuparRegistroLibre(derecho, n16bits);
			}

			//Se agrega la CMP
			codigo.agregarSentencia(oper, regIzq.getName(n16bits), regDer.getName(n16bits));

			//Se liberan los registros
			regIzq.liberar();
			regDer.liberar();

			return;
		}


		if (operacion.equals("Índice")){
			//Se tiene la expresión calculada.
			//Se hace el chequeo de límites.
			n16bits = false;
			String nombreArreglo = izquierdo.getName();		//Sin '_'
			ElementoTS elementoTabla = proyecto.TablaDeSimbolos.getElementoParaArreglo(nombreArreglo);
			Integer limInf = elementoTabla.getLim_inf();
			Integer limSup = elementoTabla.getLim_sup();
			ElementoTS elementoInf = proyecto.TablaDeSimbolos.getElementoParaArreglo(limInf.toString());
			ElementoTS elementoSup = proyecto.TablaDeSimbolos.getElementoParaArreglo(limSup.toString());

			Registro regIndice = regManager.findRegistro(derecho);
			if (regIndice == null){
				regIndice = regManager.ocuparRegistroLibre(derecho, n16bits);
			}


			//Se chequea el límite INFERIOR.

			//Se hace la comparación
			oper = Operacion.CMP;
			codigo.agregarSentencia(oper, regIndice.getName(n16bits), limInf.toString());

			String salto = codigo.apilarEtiqueta();
			oper = Operacion.JGE;
			codigo.agregarSentencia(oper, salto);

			oper = Operacion.INVOKE;
			codigo.agregarSentencia(oper, "MessageBox, NULL, addr _@E1, addr _@E1, MB_OK");
			codigo.agregarSentencia(oper, "ExitProcess, 0");

			codigo.agregarEtiqueta(salto);
			codigo.desapilarEtiqueta();

			//Se chequea el límite SUPERIOR.

			//Se hace la comparación
			oper = Operacion.CMP;
			codigo.agregarSentencia(oper, regIndice.getName(n16bits), limSup.toString());

			salto = codigo.apilarEtiqueta();
			oper = Operacion.JLE;
			codigo.agregarSentencia(oper, salto);

			oper = Operacion.INVOKE;
			codigo.agregarSentencia(oper, "MessageBox, NULL, addr _@E2, addr _@E2, MB_OK");
			codigo.agregarSentencia(oper, "ExitProcess, 0");

			codigo.agregarEtiqueta(salto);
			codigo.desapilarEtiqueta();

			//Necesitamos saber a que posición de memoria acceder
			// (i - limInf) * Tam

			//Se resta el limite inferior
			oper = Operacion.SUB;
			codigo.agregarSentencia(oper, regIndice.getName(n16bits), limInf.toString());

			//Se mueve a EAX
			Registro EAX = new Registro("EAX", "AX");
			if ( ! regIndice.getName(true).equals("AX") )
				EAX = regManager.ocuparRegistro(EAX, regIndice, n16bits);

			Integer tam = 2;
			if (izquierdo.getTipo() == ElementoTS.TIPOS.VECTOR_ENTERO_LSS)
				tam = 4;

			oper = Operacion.IMUL;
			codigo.agregarSentencia(oper, EAX.getName(n16bits), tam.toString());


			//--
			if (izquierdo.getTipo() == ElementoTS.TIPOS.VECTOR_ENTERO)
				n16bits = true;

			if (! regIndice.getName(true).equals("AX")){
				oper = Operacion.MOV;
				codigo.agregarSentencia(oper, regIndice.getName(n16bits), EAX.getName(n16bits));
				EAX.liberar();
			}

			//Seteamos el operando así es encontrado
			regIndice.setOperando(this);
			this.operacion = "[ _" + nombreArreglo + " + "+ regIndice.getName(n16bits) + " ]";

			return ;
		}


		//---------------------------------------------------------------------------------------------------------------


		if (operacion.equals("Suma \"+\"")){
			System.out.println("[ARBOL] Crear Suma");
			conmutativa = true;
			oper = Operacion.ADD;
			n16bits = (this.getTipo() == ElementoTS.TIPOS.ENTERO) ? true : false;
		}else if (operacion.equals("Resta \"-\"")){
			System.out.println("[ARBOL] Crear Resta");
			conmutativa = false;
			oper = Operacion.SUB;
			n16bits = (this.getTipo() == ElementoTS.TIPOS.ENTERO) ? true : false;
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

		//CAMBIOS a 32
		n16bits = false;

		if (izquierdo != null && izquierdo.getName().startsWith("[")){
			op1 = izquierdo.getName();
			codigo.agregarSentencia(Operacion.MOV, regIzq.getName(n16bits), op1);
		}


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
				op1 = r.getName(n16bits); 
				op2 = derecho.getName();								
			}else if (regIzq != null && regDer == null){
				// Situacion 2: Operacion entre un registro y una variable o constante
				System.out.println("Situacion 2: Operacion entre un registro y una variable o constante");

				r = regIzq;
				op1 = regIzq.getName(n16bits); 
				op2 = derecho.getName();

			}else if (regIzq != null && regDer != null){
				// Situacion 3: Operacion entre dos registros
				System.out.println("Situacion 3: Operacion entre dos registros");

				r = regIzq;
				if (op1 == null) 
					op1 = izquierdo.getName();
				if (op1.startsWith("["))
					op1 = regIzq.getName(n16bits);

				op2 = regDer.getName(n16bits);


				if (derecho != null && derecho.getName().startsWith("[")){
					op2 = derecho.getName();
				}

				regDer.liberar();
			}else if (conmutativa){
				// Situacion 4.a: Operacion conmutativa entre una variable (o constante) y un registro
				System.out.println("Situacion 4.a: Operacion conmutativa entre una variable (o constante) y un registro");
				r = regDer;
				op1 = regDer.getName(n16bits); 
				op2 = izquierdo.getName();

				if (derecho != null && derecho.getName().startsWith("[")){
					op1 = derecho.getName();
					codigo.agregarSentencia(Operacion.MOV, regDer.getName(n16bits), op1);
				}

				if (op1.startsWith("["))
					op1 = regDer.getName(n16bits);


			}else{
				// Situacion 4.b: Operacion no conmutativa entre una variable (o constante) y un registro
				System.out.println("Situacion 4.b: Operacion no conmutativa entre una variable (o constante) y un registro");

				r = regManager.ocuparRegistroLibre(izquierdo, n16bits);								// Obtener registro libre
				op1 = r.getName(n16bits); 

				op2 = regDer.getName(n16bits);		
				if (derecho != null && derecho.getName().startsWith("[")){
					op2 = derecho.getName();
					codigo.agregarSentencia(Operacion.MOV, regDer.getName(n16bits), op2);
				}

				if (op2.startsWith("["))
					op2 = regDer.getName(n16bits);


				regDer.liberar();
			}

			/* ---------------------------------------------------------------------------------- */

			codigo.agregarSentencia(oper, op1, op2);								// Realizar operacion
			r.setOperando(this);													// Actualizar referencia al resultado
			this.operacion = r.getName(n16bits);

		}
	}


	private Operacion getNegacionComparador(String comparador,boolean n16bits){
		Operacion oper = null;
		if (comparador.equals("\">\"")){
			//Saltas por Menor Igual
			oper = (n16bits) ? Operacion.JLE : Operacion.JBE;
		}
		if (comparador.equals("\"<\"")){
			//Saltas por Mayor Igual
			oper = (n16bits) ? Operacion.JGE : Operacion.JAE;
		}
		if (comparador.equals("\">=\"")){
			//Saltas por Menor
			oper = (n16bits) ? Operacion.JL : Operacion.JB;
		}
		if (comparador.equals("\"<=\"")){
			//Saltas por Mayor
			oper = (n16bits) ? Operacion.JG : Operacion.JA;
		}
		if (comparador.equals("\"=\"")){
			//Saltas por Distinto
			oper = (n16bits) ? Operacion.JNE : Operacion.JNE;	//Esta bien asi.
		}
		if (comparador.equals("\"^=\"")){
			//Saltas por Igual
			oper = (n16bits) ? Operacion.JE : Operacion.JE;		//Esta bien asi.
		}
		return oper;
	}
}
