
public class Test {

	public static void main(String[] args) {
		int indice;
		int[] arr = new int[10];
		int[] fibo = new int[11]; 	// Modificacion: Arreglo desde 0
		long[] suc = new long[11];	// Modificacion: Arreglo desde 0
		long maximo;
		
		indice = 0;

		do {
			if (indice - indice / 2 * 2 == 0) // Si es par
				arr[indice] = 1;
			else { 					// Si es impar
				arr[indice] = 0;
				System.out.println("Es impar");
			}
			indice = indice + 1;
		} while( !( indice == 10));

		indice = 3;

		
		fibo[1] = 1;
		fibo[2] = 1;
		suc[1] = 1;
		suc[2] = 2;

		do {
			suc[indice] = suc[indice-2] + suc[indice-1] * suc[indice-2] * -1;
			fibo[indice] = fibo[indice-2] + fibo[indice-1];
			
			// Modificacion: Debug
			//System.out.println(suc[indice]);
			//System.out.println(fibo[indice]);
			
			indice = indice + 1;
			
		} while ( !( indice == 11) );
		
		maximo = (long) (Math.pow(2, 32) - 1); // Modificacion

		// Modificacion: Debug
		//System.out.println(maximo);
		//System.out.println(suc[10]);
		//System.out.println(fibo[10]);
		
		if(maximo == 4294967295l)
			if(suc[10] == 55)
				System.out.println("Sucesion incorrecta");
			else
				if (fibo[10] == 55)
					System.out.println("Sucesion correcta");
		
	}

}
