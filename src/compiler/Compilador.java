package compiler;

import util.MatrizTransicion;

public class Compilador {
	private ArchivoFuente fuente;
	private AnalizadorLexico lexico;
	private AnalizadorSintatico sintactico;
	
	private MatrizTransicion transiciones;
	private TablaDeSimbolos tabla;
	
	public Compilador(){
		lexico = new AnalizadorLexico(this);
		sintactico = new AnalizadorSintatico(this);
		
		tabla = new TablaDeSimbolos();
		transiciones = new MatrizTransicion(this);
	}
	
	public void compilar(ArchivoFuente fuente){
		this.fuente = fuente;
		
		tabla.reset();
		sintactico.ejecutar();
	}

	public ArchivoFuente getArchivoFuente() {
		return fuente;
	}

	public AnalizadorLexico getAnalizadorLexico() {
		return lexico;		
	}
	
	public MatrizTransicion getMatrizTransicion(){
		return transiciones;
	}

	public TablaDeSimbolos getTablaDeSimbolos() {
		return tabla;
	}
}
