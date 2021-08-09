package ar.unrn.tp.modelo;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class TestVenta {
	
	@Test
	public void creacionVenta() {
		 /*
		//set up
		Cliente cliente = null;
		TarjetaCredito tarjeta = null;
		Venta venta = null;
		
		try {
		cliente = new Cliente("Gordo", "Ramírez", 50124863, "fede@hotmail.com");
		
		tarjeta = new TarjetaCredito(12345678123L, "MemeCard");
		
		venta = new Venta(cliente, tarjeta);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		//exercise
		
		System.out.println(venta.fechaHora());
		
		//verify
		*/
		
		Date fecha, fecha2;
		fecha = new Date();
		fecha2 = fecha;
		
		System.out.println(fecha.after(fecha2));
		System.out.println(fecha.before(fecha2));
	}
	
}
