package proyecto;

import java.util.Observable;
import java.util.Vector;

public class TablaDeSimbolos extends Observable {

	private Vector<Token> t;
	
	public TablaDeSimbolos(){
		this.t = new Vector<Token>();
	}

	public Token getToken(String l) {
		for (Token t : this.t) {
			if (t.getLexema().equals(l))
				return t;
		}
		return null;
	}
	
	public Token getToken(int p) {
		if (p < this.t.size() )
			return this.t.elementAt(p);
		return null;
	}
	
	public int getPos(String l) {
		for (int i = 0; i < this.t.size(); i++) {
			if (this.t.elementAt(i).getLexema().equals(l))
				return i;
		}
		return -1;
	}
	
	public boolean containsToken(String l) {
		for (Token token : this.t) {
			if (token.getLexema().equals(l))
				return true;
		}
		return false;
	}
	
	public Token add(Token to) {
		if (this.containsToken(to.getLexema()))
			return this.t.elementAt(this.getPos(to.getLexema()));
		this.t.add(to);
		this.setChanged();
		this.notifyObservers(to);
		return to;
	}
	
	public void clear() {
		this.t.clear();
		this.setChanged();
		this.notifyObservers(null);
	}

	public Vector<Token> getList() {
		return t;
	}

}
