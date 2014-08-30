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
	
	private File f;
	private char[] s;
	private TablaDeSimbolos t;
	private List<Integer> ls;	// Lista de referencias a tokens que aparecen en el analisis lexico
	private int p;
	
	public Proyecto(File f) {
		this.f = f;
		this.t = new TablaDeSimbolos();
		this.ls = new Vector<Integer>();
		this.reset();
		this.loadFile();
	}
	
	public Proyecto() {
		this.f = null;
		this.t = new TablaDeSimbolos();
		this.ls = new Vector<Integer>();
		this.reset();
	}

	public void reset() {
		this.p = 0;
		this.t.clear();
		this.ls.clear();
		setChanged();
		notifyObservers(null);
	}
	
	public void loadFile() {
		this.s = null;
		this.reset();
		if (this.f != null) {
			try {
				byte[] lectura = Files.readAllBytes(Paths.get(this.f.getPath()));
				String texto = new String(lectura,Charset.defaultCharset());
				this.s = texto.toCharArray();
			} catch (IOException e) {};
		}
	}
	
	public void setFile(File f) {
		this.f = f;
		this.loadFile();
	}
	
	public File getFile() {
		return this.f;
	}
	
	public TablaDeSimbolos getTablaDeSimbolos() {
		return this.t;
	}
	
	public void back() {
		if (this.p > 0)
			this.p--;
	}
	
	public char getNext() {
		this.p++;
		return this.s[p-1];
	}
	
	
	public boolean isEOF() {
		return (this.p == this.s.length);
	}
	
	public void compilar() {
		this.reset();
		AnalizadorLexico.prepare(this);
		AnalizadorSintactico.ejecutar(this);
	}

	public void addToken(int pos) { // Este observable no me gusta aca, despes habria que acomodarlo
		this.ls.add(pos);
		setChanged();
		notifyObservers(pos);
	}
}
