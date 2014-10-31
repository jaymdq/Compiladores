package generaciónASM;

import arbol.sintactico.ArbolAbs;

public class RegisterManager {

	CodigoAssembler codigo;
	Registro registros[] = new Registro[4];
	
	public RegisterManager(CodigoAssembler codigo){
		registros[0] = new Registro("EAX");
		registros[1] = new Registro("EBX");
		registros[2] = new Registro("ECX");
		registros[3] = new Registro("EDX");
		
		this.codigo = codigo;
	}
	
	public Registro getRegistroLibre(ArbolAbs operando){
		for (Registro r : registros){
			if (r.getOperando() == operando){
				System.out.println("Operando encontrado");
				return r;
			}
		}
		
		for (Registro r : registros){
			if (r.isLibre()){
				System.out.println("Registro libre encontrado: agregar MOV");
				codigo.agregarSentencia("MOV", r.getName(), operando.getName());
				return r;
			}
		}
		
		// TODO
		System.out.println("ERROR: Todos los registros usados");
		return null;
	}
	
}
