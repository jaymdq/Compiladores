package generaciónASM;

import java.util.HashMap;
import java.util.Vector;

import generaciónASM.CodigoAssembler.Operacion;
import arbol.sintactico.ArbolAbs;

public class RegisterManager {

	CodigoAssembler codigo;
	Registro registros[] = new Registro[4];
	
	Vector<Registro> cola = new Vector<Registro>();
	HashMap<ArbolAbs, String> hash = new HashMap<ArbolAbs, String>();

	public RegisterManager(CodigoAssembler codigo){
		// Se ordenan EBX y ECX primero para postergar el uso de registros específicos
		// (Sirven para mas operaciones)
		registros[0] = new Registro("EBX", "BX", this);
		registros[1] = new Registro("ECX", "CX", this);
		registros[2] = new Registro("EAX", "AX", this);
		registros[3] = new Registro("EDX", "DX", this);

		this.codigo = codigo;
	}

	public Registro getRegistro(Registro registro, ArbolAbs operando){
		for (Registro r : registros){
			// Encontrar registro
			if (r.equals(registro)){
				// Si no esta vacio, mover valor COMPLETO a otro registro
				if (!r.isLibre() && !r.getOperando().equals(operando)){
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
				codigo.agregarSentencia(Operacion.MOV, r.getName(n16bits, true), operando.getName());
				return r;
			}
		}

		// TODO
		System.out.println("ERROR: Todos los registros usados");
		Registro masViejo = cola.firstElement();
		String variableTemporal = codigo.declararTemporal();
		
		codigo.agregarSentencia(Operacion.MOV, variableTemporal, masViejo.getName(false));
		hash.put(masViejo.getOperando(), variableTemporal);
		masViejo.liberar();
		
		masViejo.setOperando(operando);
		codigo.agregarSentencia(Operacion.MOV, masViejo.getName(n16bits), operando.getName());
		
		return masViejo;
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
		Registro masViejo = cola.firstElement();
		String variableTemporal = codigo.declararTemporal();
		codigo.agregarSentencia(Operacion.MOV, variableTemporal, masViejo.getName(false));
		hash.put(masViejo.getOperando(), variableTemporal);
		masViejo.liberar();
		
		masViejo.setOperando(registro.getOperando());
		registro.liberar();
		codigo.agregarSentencia(Operacion.MOV, masViejo.getName(false), registro.getName(false));
		
		return masViejo;
	}

	public Registro findRegistro(ArbolAbs operando){
		for (Registro r : registros){
			if (!r.isLibre() && r.getOperando().equals(operando)){
				return r;
			}
		}
		// TODO Verificar que no este almacenado
		if (hash.containsKey(operando)){
			String variableTemporal = hash.get(operando);
			Registro victima = null;
			int i = 0;
			while (victima == null && i < 4) {
				if (registros[i].isLibre())
					victima = registros[i];
				else
					i++;
			}
			if (victima == null)
				victima = cola.firstElement(); // Por teoria de usos se elige la ultima
			codigo.agregarSentencia(Operacion.MOV, victima.getName(false), variableTemporal);
			victima.liberar();
				
			victima.setOperando(operando);
			hash.remove(operando);
			return victima;
		}
		
		return null;
	}

	// Almacenar en un registro determinado una variable
	public Registro ocuparRegistro(Registro registro, ArbolAbs operando, boolean n16bits) {
		Registro r = getRegistro(registro, operando);

		if (r == null)
			return null;

		if (r.getOperando() != null && r.getOperando().equals(operando))
			return r;
		
		codigo.agregarSentencia(Operacion.MOV, r.getName(n16bits), operando.getName());
		r.setOperando(operando); // TODO Verificar si es null

		return r;
	}

	// Almacenar en un registro determinado un valor inmediato
	public Registro ocuparRegistro(Registro registro, int inmediato, boolean n16bits){
		Registro r = getRegistro(registro, null);

		if (r == null)
			return null;

		codigo.agregarSentencia(Operacion.MOV, r.getName(n16bits), "" + inmediato);
		r.setInmediato();

		return r;
	}

	// Mover un valor de un registro a otro
	public Registro ocuparRegistro(Registro registro, Registro reg, boolean n16bits) {
		// Ya esta en el registro solicitado
		if (registro.equals(reg))
			return reg;
		
		Registro r = getRegistro(registro, null);

		if (r == null)
			return null;

		codigo.agregarSentencia(Operacion.MOV, r.getName(n16bits), reg.getName(n16bits));
		reg.liberar();

		return r;
	}
	
	public void liberarTodos(){
		for (Registro r : registros){
			r.liberar();
		}
	}

	public void desencolar(Registro registro) {
		cola.remove(registro);		
	}

	public void encolar(Registro registro) {
		cola.remove(registro);
		cola.add(registro); // Agregar al final
	}

}
