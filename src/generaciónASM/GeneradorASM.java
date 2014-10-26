package generaciónASM;

import java.util.Vector;

import proyecto.ElementoTS;
import proyecto.TablaDeSimbolos;
import arbol.sintactico.ArbolAbs;

public class GeneradorASM {

	private Vector<ArbolAbs> sentencias;
	private TablaDeSimbolos tablaSimbolos;
	private String codigoGenerado = "";
	
	
	public GeneradorASM(Vector<ArbolAbs> sentencias,TablaDeSimbolos tablaSimbolos){
		this.sentencias = sentencias;
		this.tablaSimbolos = tablaSimbolos;
	}
	
	//Hay que hacer todo lo que genere ASM
	public void generarCodigo(){
		String codigo = "";
		
		//Se especifica el comienzo del programa declarando la memoria a utilizar
		codigo += "\t\t.data\n";
		
		codigo += getVariables();
		
		
		//Se especifican las instrucciones del programa
		codigo += "\t\t.text\n";
		codigo += "\t\t.global start";
		codigo += "\nstart: \t";
		
		//Codigo de programa
		
		codigo += "\n";
		
		
		//Finalización del programa
		codigo += "\t\t ret";
		
		//Asignamos al final el resultado obtenido
		codigoGenerado = codigo;
	}

	public String getCodigoGenerado() {
		return codigoGenerado;
	}
	
	private String getVariables (){
		String salida = "";
		
		//Agregamos las variables de tipo entero
		for (ElementoTS e : tablaSimbolos.getLista()){
			if ( e.getTipo().toString().equals("Entero") && e.getUso().toString().equals("Variable")){
				if (e.getToken().getLexema().length() >= 8)
					salida += e.getToken().getLexema() + ":\t.int " + "[POSICION DE MEMORIA A DEFINIR]" + "\n";
				else
					salida += e.getToken().getLexema() + ":\t\t.int " + "[POSICION DE MEMORIA A DEFINIR]" + "\n";
			}
			
		}
		
		//Agregamos las variables de tipo entero_lss
		for (ElementoTS e : tablaSimbolos.getLista()){
			if ( e.getTipo().toString().equals("Entero LSS") && e.getUso().toString().equals("Variable")){
				if (e.getToken().getLexema().length() >= 8)
					salida += e.getToken().getLexema() + ":\t.int " + "[POSICION DE MEMORIA A DEFINIR ENTERO LSS]" + "\n";
				else
					salida += e.getToken().getLexema() + ":\t\t.int " + "[POSICION DE MEMORIA A DEFINIR ENTERO LSS]" + "\n";
			}
			
		}
		
		return salida;
	}
	
	
	
	private String sumar(){
		String salida = "";
		
		return salida;
	}
}
