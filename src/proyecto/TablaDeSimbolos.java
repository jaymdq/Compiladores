package proyecto;

import java.util.Observable;
import java.util.Vector;

import sintactico.ParserVal;
import lexico.AnalizadorLexico;

public class TablaDeSimbolos extends Observable {

	//private Vector<Token> tabla;
	
	private Vector<ElementoTS> tabla;
	
	public TablaDeSimbolos(){
		this.tabla = new Vector<ElementoTS>();
	}

	public Token getToken(String l) {
		for (ElementoTS t : this.tabla) {
			if (t.getToken().getLexema().equals(l))
				return t.getToken();
		}
		return null;
	}
	
	public Token getToken(int p) {
		if (p < this.tabla.size() )
			return this.tabla.elementAt(p).getToken();
		return null;
	}
	
	public int getPos(String l) {
		for (int i = 0; i < this.tabla.size(); i++) {
			if (this.tabla.elementAt(i).getToken().getLexema().equals(l))
				return i;
		}
		return -1;
	}
	
	public ElementoTS getElemento(int pos){
		return this.tabla.elementAt(pos);
	}
	
	
	public boolean containsToken(String l) {
		return getToken(l) != null;
	}
	
	public Token add(Token to) {
		if (this.containsToken(to.getLexema())){
			AnalizadorLexico.yylval = new ParserVal(this.getPos(to.getLexema()));
			this.tabla.elementAt(this.getPos(to.getLexema())).getToken().aumentarContador();
			return this.tabla.elementAt(this.getPos(to.getLexema())).getToken();
		}
			
		ElementoTS nuevoElemento = new ElementoTS(to);
		//this.tabla.add(to);
		this.tabla.add(nuevoElemento);
		
		this.setChanged();
		//this.notifyObservers(to);
		this.notifyObservers(nuevoElemento);
		AnalizadorLexico.yylval = new ParserVal(tabla.size() - 1);
		return to;
	}
	
	public void remove(String lexema){
		for (ElementoTS t : tabla){
			if (t.getToken().getLexema().equals(lexema)){
				tabla.remove(t);
				return;
			}
		}
	}
	
	public void clear() {
		this.tabla.clear();
		this.setChanged();
		this.notifyObservers(null);
	}

	public Vector<ElementoTS> getList() {
		return tabla;
	}

	public void setearCambios() {
		this.setChanged();
	}

}
