package compiler;

import java.io.File;

public class ArchivoFuente {
	private File file;
	private String HARDCODED_SOURCE = "si 56 <= valor entonces imprimir(hola);";
	private char [] source;
	private int pointer;
	
	public ArchivoFuente (File file){
		this.file = file;
		
		reset();
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
