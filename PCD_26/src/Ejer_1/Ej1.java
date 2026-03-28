package Ejer_1;

import java.util.concurrent.locks.ReentrantLock;

public class Ej1 {
	
	
	public static Panel panel = new Panel ("Ejercicio_1", 100, 100);
	private static final ReentrantLock cerrojo = new ReentrantLock();

	public static void main (String [] args) {
		Ej1 matrizConcurrente = new Ej1();

		Hilo_Mult_Matrix a = new Hilo_Mult_Matrix(matrizConcurrente);
		Hilo_Suma_Matrix b = new Hilo_Suma_Matrix(matrizConcurrente);

		Thread t1 = new Thread (a);
		
		Thread t2 = new Thread (b);
		t1.start();

		t2.start();
		
	}

	

	public ReentrantLock getCerrojo() {
		return cerrojo;
	}
	
}
