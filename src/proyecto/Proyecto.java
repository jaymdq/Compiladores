package proyecto;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Observable;
import java.util.Vector;

import lexico.AnalizadorLexico;
import sintactico.AnalizadorSintactico;

public class Proyecto extends Observable {
	
	private File archivo;
	private char[] caracteres;
	private TablaDeSimbolos tablaSimbolos;
	private List<Integer> listaReferencias;	// Lista de referencias a tokens que aparecen en el analisis lexico
	private int posicion;
	
	public Proyecto(File archivo) {
		this.archivo = archivo;
		this.tablaSimbolos = new TablaDeSimbolos();
		this.listaReferencias = new Vector<Integer>();
		this.reset();
		this.loadFile();
	}
	
	public Proyecto() {
		this.archivo = null;
		this.tablaSimbolos = new TablaDeSimbolos();
		this.listaReferencias = new Vector<Integer>();
		this.reset();
	}

	public void reset() {
		this.posicion = 0;
		this.tablaSimbolos.clear();
		this.listaReferencias.clear();
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
	
	public char getNext() {
		return this.caracteres[this.posicion++];
	}
	
	
	public boolean isEOF() {
		return (this.posicion == this.caracteres.length);
	}
	
	public void compilar() {
		this.reset();
		AnalizadorLexico.prepare(this);
		AnalizadorSintactico.ejecutar(this);
	}

	public int addToken(Token to) {
		int pos = this.tablaSimbolos.add(to);
		this.listaReferencias.add(pos);
		setChanged();
		notifyObservers(pos);
		return pos;
	}
}
