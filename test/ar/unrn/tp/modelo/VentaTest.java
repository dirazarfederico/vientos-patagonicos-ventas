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

public class VentaTest {

	@Test
	public void creacionVenta() throws EmptyStringException, InvalidEmailException, IllegalNumberException, ParseException, IllegalArgumentException, DateOverlapException {
		
		Cliente cliente = new Cliente("Alfonso", "Ramirez", 39864572, "a.ramirez@gmail.com");
		
		String marca = "Samsung", empresa = "MemeCard";
		
		Categoria categoria = new Categoria("Electrónica");
		
		Producto producto = new Producto("Auriculares Samsung", 300.5, marca, categoria);
		
		TarjetaCredito tarjeta = new TarjetaCredito(2483186648464L, empresa);
		
		PromocionTarjeta promo1;
		PromocionMarca promo2;
		
		FechaHora inicio, fin;
		
		inicio = new FechaHora("12/07/2021 00:00:00");
		
		fin = new FechaHora("31/12/2021 00:00:00");
		
		FechaHora hoy = new FechaHora();
		
		promo1 = new PromocionTarjeta(inicio, fin, 0.08, empresa);
		
		promo2 = new PromocionMarca(inicio, fin, 0.05, marca);
		
		List<PromocionTarjeta> promosTarjeta = new ArrayList<PromocionTarjeta>();
		
		promosTarjeta.add(promo1);
		
		List<PromocionMarca> promosMarca = new ArrayList<PromocionMarca>();
		
		promosMarca.add(promo2);
		
		Carrito carrito = new Carrito(cliente, promosTarjeta, promosMarca);
		
		carrito.agregarProducto(producto, 2);
		
		Venta venta = carrito.efectuarVenta(tarjeta);
		
		Assert.assertNotNull("Hola venta", venta);
	}
	
}
