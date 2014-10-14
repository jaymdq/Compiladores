package arbolsintactico;

public class NodoHoja extends Nodo{

	private String token;

	public NodoHoja(String token) {
		super();
		this.token = token;
	}

	@Override
	public String toString() {
		return token;
	}
	
}
