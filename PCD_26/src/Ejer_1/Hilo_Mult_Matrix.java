package Ejer_1;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

//import java.util.concurrent.locks.ReentrantLock;

public class Hilo_Mult_Matrix implements Runnable{
	
	private final Ej1 matrizConcurrente;
	
	public Hilo_Mult_Matrix(Ej1 matrizConcurrente) {
		this.matrizConcurrente = matrizConcurrente; 
	}
	
	private int [][] matrizA = new int [3][3];

	@Override
	
	public void run() {
		//Repeticones

		for (int i = 0; i < 10; i++) {
			
			int [][] A = generaMatrix();
			setMatrizA(A);
			int [][] matrizResultado = matrixCuadrado(A);
			
			matrizConcurrente.getCerrojo().lock();
		
			// generamos la matriz
			try {
				Ej1.panel.escribir_mensaje("A x A\n");
				imprimeMatrix(A, Ej1.panel);
				Ej1.panel.escribir_mensaje(" x\n");
				imprimeMatrix(A, Ej1.panel);
				Ej1.panel.escribir_mensaje("A^2\n");
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
		
	private int [][] matrixCuadrado(int [][]A) {
		int [][] cuadrado = new int [3][3];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				for (int k = 0; k < A.length; k++) {
					cuadrado[i][j] += A [i][k] * A [k][j];
				}
			}
		}
		
		return cuadrado;
	}
	//imprimimos en la pantalla la matriz y el resultado
	
	public void imprimeMatrix(int [][] A, Panel panel) {
		System.out.println("A x A");
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
