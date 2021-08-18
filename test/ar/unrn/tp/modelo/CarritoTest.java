package ar.unrn.tp.modelo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import ar.unrn.tp.excepciones.DateOverlapException;
import ar.unrn.tp.excepciones.EmptyStringException;
import ar.unrn.tp.excepciones.IllegalNumberException;
import ar.unrn.tp.excepciones.InvalidEmailException;
import ar.unrn.tp.excepciones.InvalidIDException;

public class CarritoTest {
	
	@Test
	public void montoTotalDescuentosVencidos() throws EmptyStringException, InvalidIDException, InvalidEmailException, IllegalNumberException, ParseException, IllegalArgumentException, DateOverlapException {
		
		Cliente cliente = new Cliente("Alfonso", "Ramírez", 39864572, "a.ramirez@gmail.com");
		
		Producto producto = new Producto("Auriculares Samsung", 300.50, "Samsung", "Electronica");
		
		TarjetaCredito tarjeta = new TarjetaCredito(2483186648464L, "MemeCard");
		
		Promocion promo1, promo2;
		
		FechaHora inicio, fin;
		
		inicio = new FechaHora("12/07/2021");
		
		fin = new FechaHora("11/08/2021");
		
		promo1 = new PromocionTarjeta(inicio, fin, 0.08, tarjeta);
		
		promo2 = new PromocionMarca(inicio, fin, 0.05, "Samsung");
		
		List<Promocion> promos = new ArrayList<Promocion>();
		
		promos.add(promo1);
		promos.add(promo2);
		
		Carrito carrito = new Carrito(cliente, promos);
		
		carrito.añadirProducto(producto, 2);
		
		Venta venta = carrito.efectuarVenta(tarjeta);
		
		boolean bienPrecio = venta.total() == 601.00;
		
		Assert.assertTrue(bienPrecio);
		
	}
	
	

}
