package com.threads.supermarket.reloaded;

public class CajeraRunnable implements Runnable {

	private Cliente cliente;
	private long initialTime;
	
	public CajeraRunnable() {
		
	}
	
	public CajeraRunnable(Cliente cliente, long initialTime) {
		super();
		this.cliente = cliente;
		this.initialTime = initialTime;
	}
	
	@Override
	public void run() {
		System.out.println("La cajera " + Thread.currentThread().getName() + 
				" COMIENZA A PROCESAR LA COMPRA DEL CLIENTE " + this.cliente.getNombre() +
				" EN EL TIEMPO " + (System.currentTimeMillis() - this.initialTime) / 1000 + "seg" );
		
		for(int i=0; i < cliente.getCarroCompra().length; i++) {
			
			// se procesa el pedido en X segundos
			this.esperarXsegundos(cliente.getCarroCompra()[i]);
			
			System.out.println("Procesando el producto " + (i+1) +
					" del " + this.cliente.getNombre() + " -> Tiempo: " + 
					(System.currentTimeMillis() - this.initialTime) / 1000 + "seg" );
		}
		
		System.out.println("La cajera " + Thread.currentThread().getName() + " HA TERMINADO DE PROCESAR " + 
				this.cliente.getNombre() + " EN EL TIEMPO " +
				(System.currentTimeMillis() - this.initialTime) / 1000 + "seg" );
	}

	private void esperarXsegundos(int segundos) {
		try {
			Thread.sleep(segundos * 1000);
		}catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	
}
