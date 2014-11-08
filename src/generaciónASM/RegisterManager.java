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

	public Registro getRegistro(Registro registro){
		for (Registro r : registros){
			// Encontrar registro
			if (r.equals(registro)){
				// Si no esta vacio, mover valor COMPLETO a otro registro
				if (!r.isLibre()){
					ocuparRegistroLibre(r);
				}
				return r;
			}
		}

		return null;
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

	// Mover de un registro a otro. Devuelve el registro
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

	// Almacenar en un registro determinado una variable
	public Registro ocuparRegistro(Registro registro, ArbolAbs operando, boolean n16bits) {
		Registro r = getRegistro(registro);

		if (r == null)
			return null;

		codigo.agregarSentencia(Operacion.MOV, r.getName(n16bits), operando.getName());
		r.setOperando(operando); // TODO Verificar si es null

		return r;
	}

	// Almacenar en un registro determinado un valor inmediato
	public Registro ocuparRegistro(Registro registro, int inmediato, boolean n16bits){
		Registro r = getRegistro(registro);

		if (r == null)
			return null;

		codigo.agregarSentencia(Operacion.MOV, r.getName(n16bits), "" + inmediato);
		r.setInmediato();

		return r;
	}

	// Mover un valor de un registro a otro
	public Registro ocuparRegistro(Registro registro, Registro reg, boolean n16bits) {
		Registro r = getRegistro(registro);

		if (r == null)
			return null;

		codigo.agregarSentencia(Operacion.MOV, r.getName(n16bits), reg.getName(n16bits));

		return r;
	}

	// Mover un valor de un registro a otro
	public Registro ocuparRegistroBien(Registro registro,ArbolAbs operando, boolean n16bits) {
		Registro r = getRegistro(registro);

		if (r == null)
			return null;

		r.setOperando(operando);

		return r;
	}
	
	public void liberarTodos(){
		for (Registro r : registros){
			r.liberar();
		}
	}

}
