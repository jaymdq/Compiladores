package proyecto;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Observable;
import java.util.Vector;

import arbol.sintactico.ArbolAbs;
import lexico.AnalizadorLexico;
import sintactico.AnalizadorSintactico;

public class Proyecto extends Observable {
	
	private File archivo;
	private char[] caracteres;
	private TablaDeSimbolos tablaSimbolos;
	private List<Token> listaTokens;	// Lista de referencias a tokens que aparecen en el analisis lexico
	private List<String> listaSentencias;
	private int posicion;
	private int lineaActual;
	private String codigoGenerado = "";
	
	public Proyecto(File archivo) {
		this.archivo = archivo;
		this.tablaSimbolos = new TablaDeSimbolos();
		this.listaTokens = new Vector<Token>();
		this.listaSentencias = new Vector<String>();
		this.reset();
		this.loadFile();
	}
	
	public Proyecto() {
		this.archivo = null;
		this.tablaSimbolos = new TablaDeSimbolos();
		this.listaTokens = new Vector<Token>();
		this.listaSentencias = new Vector<String>();
		this.reset();
	}

	public void reset() {
		this.posicion = 0;
		this.tablaSimbolos.clear();
		this.listaTokens.clear();
		this.lineaActual = 1;
		setChanged();
		notifyObservers(null);
	}
	
	public void loadFile() {
		this.caracteres = null;
		this.reset();
		if (this.archivo != null) {
			try {
				byte[] lectura = Files.readAllBytes(Paths.get(this.archivo.getPath()));
				String texto = new String(lectura,Charset.defaultCharset());
				this.caracteres = texto.toCharArray();
			} catch (IOException e) {};
		}
	}
	
	public void setFile(File archivo) {
		this.archivo = archivo;
		this.loadFile();
	}
	
	public File getFile() {
		return this.archivo;
	}
	
	public TablaDeSimbolos getTablaDeSimbolos() {
		return this.tablaSimbolos;
	}
	
	public void back() {
		if (this.posicion > 0)
			this.posicion--;
	}
	
	public char getNextCaracter() {
		if (!this.isEOF())
			return this.caracteres[this.posicion++];
		this.posicion++;
		return '\0';
	}
	
	
	public boolean isEOF() {
		return (this.posicion >= this.caracteres.length);
	}
	
	public boolean compilar() {
		this.reset();
		AnalizadorLexico.prepare(this);
		boolean salida = AnalizadorSintactico.ejecutar(this);
		return salida;
	}

	public Token addTokenToTable(Token to) {
		this.addTokenToList(to);
		Token salida = this.tablaSimbolos.add(to);
		//AnalizadorLexico.yylval = new ParserVal(this.tablaSimbolos.getPos(salida.getLexema()));  <--- no sabemos porque esta.. la tabla de simbolos ya hace todo
		return salida;
	}
	
	public void addTokenToList(Token to) {
		this.listaTokens.add(to);
		setChanged();
		notifyObservers(to);
	}

	public int getLineaActual() {
		return lineaActual;
	}

	public void setLineaActual(int lineaActual) {
		this.lineaActual = lineaActual;
	}
	
	public void avanzarLinea(){
		this.lineaActual++;
	}

	public void retrocederLinea() {
		this.lineaActual--;
	}

	public void addSentenciaToList(String sentencia){
		this.listaSentencias.add(sentencia);
		setChanged();
		notifyObservers(sentencia);
	}

	public void setArbol(ArbolAbs sentencias) {
		setChanged();
		notifyObservers(sentencias);
	}


}
