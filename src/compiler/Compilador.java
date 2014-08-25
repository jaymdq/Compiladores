package compiler;

public class Compilador {
	private ArchivoFuente fuente;
	private AnalizadorLexico lexico;
	private AnalizadorSintatico sintactico;
	
	private TablaDeSimbolos tabla;
	
	public Compilador(){
		lexico = new AnalizadorLexico(this);
		sintactico = new AnalizadorSintatico(this);
		
		tabla = new TablaDeSimbolos();
	}
	
	public void compilar(ArchivoFuente fuente){
		this.fuente = fuente;
		sintactico.ejecutar();
	}

	public ArchivoFuente getArchivoFuente() {
		return fuente;
	}

	public AnalizadorLexico getAnalizadorLexico() {
		return lexico;		
	}
}
