package proyecto;

import java.util.Observable;
import java.util.Vector;

public class TablaDeSimbolos extends Observable {

	private Vector<Token> tabla;
	
	public TablaDeSimbolos(){
		this.tabla = new Vector<Token>();
	}

	public Token getToken(String l) {
		for (Token t : this.tabla) {
			if (t.getLexema().equals(l))
				return t;
		}
		return null;
	}
	
	public Token getToken(int p) {
		if (p < this.tabla.size() )
			return this.tabla.elementAt(p);
		return null;
	}
	
	public int getPos(String l) {
		for (int i = 0; i < this.tabla.size(); i++) {
			if (this.tabla.elementAt(i).getLexema().equals(l))
				return i;
		}
		return -1;
	}
	
	public boolean containsToken(String l) {
		for (Token token : this.tabla) {
			if (token.getLexema().equals(l))
				return true;
		}
		return false;
	}
	
	public Token add(Token to) {
		if (this.containsToken(to.getLexema()))
			return this.tabla.elementAt(this.getPos(to.getLexema()));
		this.tabla.add(to);
		this.setChanged();
		this.notifyObservers(to);
		
		
		return to;
	}
	
	public void clear() {
		this.tabla.clear();
		this.setChanged();
		this.notifyObservers(null);
	}

	public Vector<Token> getList() {
		return tabla;
	}

}
