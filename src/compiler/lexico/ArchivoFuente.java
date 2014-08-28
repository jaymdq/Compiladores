package compiler.lexico;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ArchivoFuente {
	private File file;
	//private String HARDCODED_SOURCE = "si 56 <= valor entonces imprimir(hola);";
	private char [] source;
	private int pointer;
	
	public ArchivoFuente (File file){
		loadFile(file);
	}
	
	public void loadFile(File file){
		this.file = file;
		try {
			byte[] lectura = Files.readAllBytes(Paths.get(file.getPath()));
			String texto = new String(lectura,Charset.defaultCharset());
			source = texto.toCharArray();
		} catch (IOException e) { e.printStackTrace();}
		pointer = 0;
	}
	
	public char getChar(){
		if (pointer >= source.length){
			pointer++;
			return 0;
		}
		return source[pointer++];
	}
	
	public void ungetChar(){
		if (pointer > 0)
			pointer--;
	}
	
	@Override
	public String toString() {
		return file.getName();
	}
}
