package com.threads.supermarket.reloaded;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainExecutor {

	private static final int numCajeras = 2;
	
	public static void main(String[] args) {
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		clientes.add(new Cliente("Cliente 1", new int[] {2,2,1,5,2} )); // 12 seg
		clientes.add(new Cliente("Cliente 2", new int[] {1,1,5,1,1} )); //  9 seg
		clientes.add(new Cliente("Cliente 3", new int[] {5,3,1,5,2} )); // 16 seg
		clientes.add(new Cliente("Cliente 4", new int[] {2,4,3,2,5} )); // 16 seg
		clientes.add(new Cliente("Cliente 5", new int[] {1,3,2,2,3} )); // 11 seg
		clientes.add(new Cliente("Cliente 6", new int[] {4,2,1,3,1} )); // 11 seg
		clientes.add(new Cliente("Cliente 7", new int[] {3,3,2,4,7} )); // 19 seg
		clientes.add(new Cliente("Cliente 8", new int[] {6,1,3,1,3} )); // 12 seg
		// Tiempo total en procesar todos los pedidos en 108 segundos

		// Tiempo inicial de referencia
		long initialTime = System.currentTimeMillis();

		ExecutorService executor = Executors.newFixedThreadPool(numCajeras);
		for(Cliente cliente: clientes) {
			Runnable cajera = new CajeraRunnable(cliente, initialTime);
			executor.execute(cajera);
		}
		
		executor.shutdown();	// Cierra el Executor
		
		while(!executor.isTerminated()) {
			// Espero a que termimen de ejecutarse todos los procesos
			// para pasar a las siguientes instrucciones
		}
		
		long fin = System.currentTimeMillis();		// Instante final del procesamiento
		System.out.println("Tiempo total de procesamiento: "  + (fin-initialTime)/1000 + " Segundos");
	}
	
}
