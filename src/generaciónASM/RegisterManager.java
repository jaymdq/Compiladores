package generaciónASM;

import generaciónASM.CodigoAssembler.Operacion;
import arbol.sintactico.ArbolAbs;

public class RegisterManager {

	CodigoAssembler codigo;
	Registro registros[] = new Registro[4];
	
	public RegisterManager(CodigoAssembler codigo){
		// Se ordenan EBX y ECX primero para postergar el uso de registros específicos
		// (Sirven para mas operaciones)
		registros[0] = new Registro("EBX", "BX");
		registros[1] = new Registro("ECX", "CX");
		registros[2] = new Registro("EAX", "AX");
		registros[3] = new Registro("EDX", "DX");
		
		this.codigo = codigo;
	}
	
	// Mover desde memoria a un registro libre. Devuelve el registro
	public Registro ocuparRegistroLibre(ArbolAbs operando, boolean n16bits){
		for (Registro r : registros){
			if (r.isLibre()){
				System.out.println("Registro libre encontrado: agregar MOV R1, Variable");
				r.setOperando(operando);
				codigo.agregarSentencia(Operacion.MOV, r.getName(n16bits), operando.getName());
				return r;
			}
		}
		
		// TODO
		System.out.println("ERROR: Todos los registros usados");
		return null;
	}
	
	// Mover de un registro a otro
	public Registro ocuparRegistroLibre(Registro registro){
		for (Registro r : registros){
			if (r.isLibre()){
				System.out.println("Registro libre encontrado: agregar MOV R1, R2");
				r.setOperando(registro.getOperando());
				registro.liberar();
				codigo.agregarSentencia(Operacion.MOV, r.getName(false), registro.getName(false));
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

	public Registro ocuparRegistro(Registro registro, ArbolAbs operando, boolean n16bits) {
		for (Registro r : registros){
			if (r.equals(registro)){
				if (!r.isLibre()){
					// Mover valor COMPLETO a otro registro
					ocuparRegistroLibre(r);
				}
				r.setOperando(operando); // TODO Verificar si es null
				if (operando == null){
					codigo.agregarSentencia(Operacion.MOV, r.getName(n16bits), "0"); // TODO Verificar constante
				}else{
					codigo.agregarSentencia(Operacion.MOV, r.getName(n16bits), operando.getName());
				}
				return r;
			}
		}
		
		return null;
	}
	
}
