package Ejer_1;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

//import java.util.concurrent.locks.ReentrantLock;

public class Hilo_Suma_Matrix implements Runnable{
	
	private final Ej1 matrizConcurrente;
	
	public Hilo_Suma_Matrix(Ej1 matrizConcurrente) {
		this.matrizConcurrente = matrizConcurrente; 
	}
	
	private int [][] matrizA = new int [3][3];

	@Override
	
	public void run() {
		//Repeticones

		for (int i = 0; i < 10; i++) {
			
			int [][] A = generaMatrix();
			setMatrizA(A);
			int [][] matrizResultado = matrixDoble(A);
			
			matrizConcurrente.getCerrojo().lock();
		
			// generamos la matriz
			try {
				Ej1.panel.escribir_mensaje("A + A\n");
				imprimeMatrix(A, Ej1.panel);
				Ej1.panel.escribir_mensaje(" +\n");
				imprimeMatrix(A, Ej1.panel);
				Ej1.panel.escribir_mensaje("2A\n");
				imprimeMatrix(matrizResultado, Ej1.panel);
				Ej1.panel.escribir_mensaje("\n\n");

			} finally {
				matrizConcurrente.getCerrojo().unlock();
			}	
		}
		
	}
	
	
	//creamos una matriz con numeros aleatorios
	public static int [][] generaMatrix() {
		Random random = new Random();
		int [][] matrix = new int[3][3];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix [i][j] = random.nextInt(10);
			}
		}
		return matrix;
			
	}
		
	private int [][] matrixDoble(int [][]A) {
		int [][] doble = new int [3][3];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				for (int k = 0; k < A.length; k++) {
					doble[i][j] += A [i][k] * A [k][j];
				}
			}
		}
		
		return doble;
	}
	//imprimimos en la pantalla la matriz y el resultado
	
	public void imprimeMatrix(int [][] A, Panel panel) {
		System.out.println("A + A");
		for (int i = 0; i < A.length; i++) {
			StringBuilder fila = new StringBuilder();
			for (int j = 0; j < A[i].length; j++) {
				fila.append(String.format("%3d ", A[i][j]));
				//System.out.print(A [i][j] + " ");
			}
			//System.out.println();
			panel.escribir_mensaje(fila.toString() + "\n");
		}
	}
	
	public void setMatrizA(int [][] matrizA) {
		this.matrizA = matrizA;
	}
	
}