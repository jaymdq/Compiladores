package generaciónASM;

public class CodigoAssembler {

	String sentencias = new String();
	RegisterManager regManager = new RegisterManager(this);
	
	public void agregarSentencia(String operacion, String op1, String op2){
		String sent = operacion + " " + op1 + ", " + op2;
		System.out.println("Agregar: " + sent);
		sentencias.concat(sent + "\n");
	}

	public RegisterManager getRegManager() {
		return regManager;
	}
	
}
