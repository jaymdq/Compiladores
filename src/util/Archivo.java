package util;

import java.io.File;

public class Archivo {
	private File file;
	private String HARDCODED_SOURCE = "si 56 <= valor entonces imprimir(hola);";
	private char [] source;
	private int pointer;
	
	public Archivo (File file){
		this.file = file;
	}
	
	public void reset(){
		source = HARDCODED_SOURCE.toCharArray();
		pointer = 0;
	}
	
	public char getChar(){
		if (pointer >= source.length)
			return 0;
		return source[pointer++];
	}
	
	public void ungetChar(){
		if (pointer > 0)
			pointer--;
	}
}
