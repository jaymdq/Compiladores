package generaciónASM;

import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import proyecto.ElementoTS;
import proyecto.TablaDeSimbolos;
import arbol.sintactico.ArbolAbs;

public class GeneradorASM {

	private ArbolAbs sentencias;
	private TablaDeSimbolos tablaSimbolos;
	private String codigoGenerado = "";
	private Vector<String> declaracionesSentencias;
	private static HashMap<String,String> mapStringsASM ;

	public GeneradorASM(ArbolAbs sentencias,TablaDeSimbolos tablaSimbolos){
		this.sentencias = sentencias;
		this.tablaSimbolos = tablaSimbolos;
		this.declaracionesSentencias = new Vector<String>();
		GeneradorASM.setMapStringsASM(new HashMap<String,String>());
	}

	private String getLibrerias() {
		String path = "";
		try {
			path = new java.io.File(".").getCanonicalPath();
		} catch (IOException e) {}
		String salida = "";
		salida += "include "+ path +"\\masm32\\include\\windows.inc\n";
		salida += "include "+ path +"\\masm32\\include\\kernel32.inc\n";
		salida += "include "+ path +"\\masm32\\include\\user32.inc\n";
		salida += "includelib "+ path +"\\masm32\\lib\\kernel32.lib\n";
		salida += "includelib "+ path +"\\masm32\\lib\\user32.lib\n";
		return salida;
	}

	//Hay que hacer todo lo que genere ASM
	public void generarCodigo(){
		String codigo = "";

		//Sección de inicialización
		codigo += ".386\n";

		codigo += ".model flat, stdcall\n";
		codigo += "option casemap :none\n";


		codigo += getLibrerias();

		//Sección de datos
		codigo += ".data\n";

		tratarDeclaraciones();
		for (String s : declaracionesSentencias)
			codigo += s + "\n";

		tratarCadenas();
		for (String clave : getMapStringsASM().keySet()){
			codigo +=  getMapStringsASM().get(clave) + "\t\t" + "db " + clave + ", 0\n";
		}
		
		//Se insertan los errores!
		codigo += "_@E1 \t\tdb \"Índice del arreglo es menor al límite inferior!!!\", 0\n";
		codigo += "_@E2 \t\tdb \"Índice del arreglo es mayor al límite superior!!!\", 0\n";
		codigo += "_@E3 \t\tdb \"Overflow en producto (Fuera de Rango)!!!\", 0\n";
		
		
		
		//----------------------- CÓDIGO ------------------------------------------------------

		System.out.println("Generar Assembler");
		CodigoAssembler capturadorASM = new CodigoAssembler();
		sentencias.generarAssembler(capturadorASM);

		//Caso particular de si hay sentencias auxiliares
		codigo += capturadorASM.getDeclaracionesExtras();
		
		codigo += "\n.code\n";
		codigo += "start:\n\n";
		
		codigo += "PUSH EAX ; Se guarda el contexto \n";
		codigo += "PUSH EBX ; Se guarda el contexto \n";
		codigo += "PUSH ECX ; Se guarda el contexto \n";
		codigo += "PUSH EDX ; Se guarda el contexto \n";
			
		codigo += "MOV EAX, 0 ; Inicialización \n";
		codigo += "MOV EBX, 0 ; Inicialización \n";
		codigo += "MOV ECX, 0 ; Inicialización \n";
		codigo += "MOV EDX, 0 ; Inicialización \n\n";
		
		codigo += capturadorASM.getSentencias();

		codigo += "POP EDX ; Se restaura el contexto \n";
		codigo += "POP ECX ; Se restaura el contexto \n";
		codigo += "POP EBX ; Se restaura el contexto \n";
		codigo += "POP EAX ; Se restaura el contexto \n";
		
		//Se finaliza el código
		codigo += "INVOKE ExitProcess, 0\n";
		codigo += "end start";

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
					declaracion = "_" + e.getToken().getLexema() + "\t"   + "DW " + cuenta + " DUP ( 0 )";
				else
					declaracion = "_" + e.getToken().getLexema() + "\t\t" + "DW " + cuenta + " DUP ( 0 )";

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
					declaracion = "_" + e.getToken().getLexema() + "\t"   + "DD " + cuenta + " DUP ( 0 )";
				else
					declaracion = "_" + e.getToken().getLexema() + "\t\t" + "DD " + cuenta + " DUP ( 0 )";

				//Se agrega
				declaracionesSentencias.add(declaracion);
			}	
		}

	}


	private void tratarCadenas(){
		Integer strC = 0;
		//Agregamos las cadenas multilineas
		for (ElementoTS e : tablaSimbolos.getLista()){
			if ( e.getTipo().toString().equals("Cadena Multilinea") && e.getUso().toString().equals("Constante")){
				String declaracion;
				declaracion = "_@" + strC;
				getMapStringsASM().put(e.getToken().getLexema(), declaracion);							
				strC++;
			}
		}
	}

	public static HashMap<String,String> getMapStringsASM() {
		return mapStringsASM;
	}

	public static void setMapStringsASM(HashMap<String,String> mapStringsASM) {
		GeneradorASM.mapStringsASM = mapStringsASM;
	}
}
