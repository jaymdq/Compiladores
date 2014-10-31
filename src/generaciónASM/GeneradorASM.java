package generaciónASM;

import java.util.Vector;

import proyecto.ElementoTS;
import proyecto.TablaDeSimbolos;
import arbol.sintactico.ArbolAbs;

public class GeneradorASM {

	private ArbolAbs sentencias;
	private TablaDeSimbolos tablaSimbolos;
	private String codigoGenerado = "";
	private Vector<String> declaracionesSentencias;

	public GeneradorASM(ArbolAbs sentencias,TablaDeSimbolos tablaSimbolos){
		this.sentencias = sentencias;
		this.tablaSimbolos = tablaSimbolos;
		this.declaracionesSentencias = new Vector<String>();
	}

	//Hay que hacer todo lo que genere ASM
	public void generarCodigo(){
		String codigo = "";

		codigo += ".DATA\n";
		tratarDeclaraciones();
		for (String s : declaracionesSentencias)
			codigo += s + "\n";

		//Asignamos al final el resultado obtenido
		codigoGenerado = codigo;
	}

	public String getCodigoGenerado() {
		return codigoGenerado;
	}

	private void tratarDeclaraciones (){

		//Agregamos las variables de tipo entero
		for (ElementoTS e : tablaSimbolos.getLista()){
			if ( e.getTipo().toString().equals("Entero") && e.getUso().toString().equals("Variable")){
				String declaracion;
				if (e.getToken().getLexema().length() > 6)
					declaracion = "_" + e.getToken().getLexema() + "\t"   + "DW " + "?";
				else
					declaracion = "_" + e.getToken().getLexema() + "\t\t" + "DW " + "?";

				//Se agrega
				declaracionesSentencias.add(declaracion);
			}
		}

		//Agregamos las variables de tipo entero_lss
		for (ElementoTS e : tablaSimbolos.getLista()){
			if ( e.getTipo().toString().equals("Entero LSS") && e.getUso().toString().equals("Variable")){
				String declaracion;
				if (e.getToken().getLexema().length() > 6)
					declaracion = "_" + e.getToken().getLexema() + "\t"   + "DD " + "?";
				else
					declaracion = "_" + e.getToken().getLexema() + "\t\t" + "DD " + "?";

				//Se agrega
				declaracionesSentencias.add(declaracion);
			}	
		}

		//Agregamos las variables de tipo vector entero
		for (ElementoTS e : tablaSimbolos.getLista()){
			if ( e.getTipo().toString().equals("Vector de enteros") && e.getUso().toString().equals("Arreglo")){
				Integer cuenta = e.getLim_sup() - e.getLim_inf() + 1;
				String declaracion;
				if (e.getToken().getLexema().length() > 6)
					declaracion = "_" + e.getToken().getLexema() + "\t"   + "DW " + cuenta + " DUP " + "0";
				else
					declaracion = "_" + e.getToken().getLexema() + "\t\t" + "DW " + cuenta + " DUP " + "0";

				//Se agrega
				declaracionesSentencias.add(declaracion);
			}	
		}

		//Agregamos las variables de tipo vector entero_lss
		for (ElementoTS e : tablaSimbolos.getLista()){
			if ( e.getTipo().toString().equals("Vector de enteros lss") && e.getUso().toString().equals("Arreglo")){
				Integer cuenta = e.getLim_sup() - e.getLim_inf() + 1;
				String declaracion;
				if (e.getToken().getLexema().length() > 6)
					declaracion = "_" + e.getToken().getLexema() + "\t"   + "DD " + cuenta + " DUP " + "0";
				else
					declaracion = "_" + e.getToken().getLexema() + "\t\t" + "DD " + cuenta + " DUP " + "0";

				//Se agrega
				declaracionesSentencias.add(declaracion);
			}	
		}


	}


}
