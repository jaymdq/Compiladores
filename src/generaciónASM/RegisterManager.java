package generaciónASM;

import arbol.sintactico.ArbolAbs;

public class RegisterManager {

	CodigoAssembler codigo;
	Registro registros[] = new Registro[4];
	
	public RegisterManager(CodigoAssembler codigo){
		// Se ordenan EBX y ECX primero para postergar el uso de registros específicos
		// (Sirven para mas operaciones)
		registros[0] = new Registro("EBX");
		registros[1] = new Registro("ECX");
		registros[2] = new Registro("EAX");
		registros[3] = new Registro("EDX");
		
		this.codigo = codigo;
	}
	
	public Registro getRegistroLibre(ArbolAbs operando){
		for (Registro r : registros){
			if (r.isLibre()){
				System.out.println("Registro libre encontrado: agregar MOV");
				r.setOperando(operando);
				codigo.agregarSentencia("MOV", r.getName(), operando.getName());
				return r;
			}
		}
		
		// TODO
		System.out.println("ERROR: Todos los registros usados");
		return null;
	}
	
	public Registro findRegistro(ArbolAbs operando){
		for (Registro r : registros){
			if (!r.isLibre() && r.getOperando().equals(operando)){
				return r;
			}
		}
		
		return null;
	}

	/*public void setRegistroOcupado(Registro registro, ArbolAbs arbol) {
		for (Registro r : registros){
			if (r.equals(registro)){
				System.out.println("Registro " + r.getName() + " actualizado");
				r.setOperando(arbol);
			}else if (r.getOperando().equals(arbol)){
				System.out.println("Liberar");
				r.liberar();
			}
		}
	}*/
	
}
