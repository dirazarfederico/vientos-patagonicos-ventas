package ar.unrn.tp.modelo;

import org.junit.jupiter.api.Test;

public class TestVenta {
	
	@Test
	public void creacionVenta() {
		
		//set up
		Cliente cliente = null;
		TarjetaCredito tarjeta = null;
		Carrito carrito = null;
		Venta venta = null;
		
		try {
		cliente = new Cliente("Gordo", "Ramírez", 50124863, "gramia@gmail.com");
		
		tarjeta = new TarjetaCredito(12345678123L, "MemeCard");
		
		carrito = new Carrito(cliente);
		
		venta = new Venta(carrito, tarjeta);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		//exercise
		
		System.out.println(venta.fechaHora());
		
		//verify
		
	}
	
}
