package ar.unrn.tp.modelo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import ar.unrn.tp.excepciones.DateOverlapException;
import ar.unrn.tp.excepciones.EmptyProductListException;
import ar.unrn.tp.excepciones.EmptyStringException;
import ar.unrn.tp.excepciones.IllegalNumberException;
import ar.unrn.tp.excepciones.InvalidEmailException;

public class CarritoTest {
	
	@Test
	public void montoTotalDescuentosVencidos() throws EmptyStringException, InvalidEmailException, IllegalNumberException, ParseException, IllegalArgumentException, DateOverlapException, EmptyProductListException {
		
		Cliente cliente = new Cliente("Alfonso", "Ramirez", 39864572, "a.ramirez@gmail.com");
		
		Categoria categoria = new Categoria("Electrónica");
		
		Producto producto = new Producto("Auriculares Samsung", 200, "Samsung", categoria);
		
		String empresa = "MemeCard";
		
		TarjetaCredito tarjeta = new TarjetaCredito(2483186648464L, empresa);
		
		PromocionTarjeta promo1;
		PromocionMarca promo2;
		
		FechaHora inicio, fin;
		
		inicio = new FechaHora("12/07/2021 00:00:00");
		
		fin = new FechaHora("31/07/2021 00:00:00");
		
		FechaHora hoy = new FechaHora();
		
		promo1 = new PromocionTarjeta(inicio, fin, 0.08, empresa);
		
		promo2 = new PromocionMarca(inicio, fin, 0.05, "Samsung");
		
		List<PromocionTarjeta> promosTarjeta = new ArrayList<PromocionTarjeta>();
		
		promosTarjeta.add(promo1);
		
		List<PromocionMarca> promosMarca = new ArrayList<PromocionMarca>();
		
		promosMarca.add(promo2);
		
		Carrito carrito = new Carrito(cliente, promosTarjeta, promosMarca);
		
		carrito.agregarProducto(producto, 2);
		
		Venta venta = carrito.efectuarVenta(tarjeta);
		
		double[] precio = {venta.total()}, esperado = {400};
		
		Assert.assertArrayEquals(esperado, precio, 0.01);
		
	}
	
	@Test
	public void montoTotalDescuentoMarca() throws EmptyStringException, InvalidEmailException, IllegalNumberException, ParseException, IllegalArgumentException, DateOverlapException, EmptyProductListException {
		
		Cliente cliente = new Cliente("Alfonso", "Ramirez", 39864572, "a.ramirez@gmail.com");
		
		String marca = "Acme";
		
		Categoria categoria = new Categoria("Electrónica");
		
		Producto producto = new Producto("Auriculares Acme", 300, "Acme", categoria);
		
		TarjetaCredito tarjeta = new TarjetaCredito(2483186648464L, "MemeCard");

		PromocionMarca promo2;
		
		FechaHora inicio, fin;
		
		inicio = new FechaHora("12/07/2021 00:00:00");
		
		fin = new FechaHora("31/12/2021 00:00:00");
		
		FechaHora hoy = new FechaHora();
		
		promo2 = new PromocionMarca(inicio, fin, 0.05, marca);
		
		List<PromocionTarjeta> promosTarjeta = new ArrayList<PromocionTarjeta>();
		
		List<PromocionMarca> promosMarca = new ArrayList<PromocionMarca>();
		
		promosMarca.add(promo2);
		
		Carrito carrito = new Carrito(cliente, promosTarjeta, promosMarca);
		
		carrito.agregarProducto(producto, 2);
		
		Venta venta = carrito.efectuarVenta(tarjeta);
		
		double[] precio = {venta.total()}, esperado = {570};
		
		Assert.assertArrayEquals(esperado, precio, 0.01);;
		
	}
	
	@Test
	public void montoTotalDescuentosTarjeta() throws EmptyStringException, InvalidEmailException, IllegalNumberException, ParseException, IllegalArgumentException, DateOverlapException, EmptyProductListException {
		
		Cliente cliente = new Cliente("Alfonso", "Ramirez", 39864572, "a.ramirez@gmail.com");
		
		Categoria categoria = new Categoria("Electrónica");
		
		Producto producto = new Producto("Auriculares Samsung", 300, "Samsung", categoria);
		
		String empresa = "MemeCard";
		
		TarjetaCredito tarjeta = new TarjetaCredito(2483186648464L, empresa);
		
		PromocionTarjeta promo1;
		
		FechaHora inicio, fin;
		
		inicio = new FechaHora("12/07/2021 00:00:00");
		
		fin = new FechaHora("31/12/2021 00:00:00");
		
		FechaHora hoy = new FechaHora();
		
		promo1 = new PromocionTarjeta(inicio, fin, 0.08, empresa);
		
		List<PromocionTarjeta> promosTarjeta = new ArrayList<PromocionTarjeta>();
		
		promosTarjeta.add(promo1);
		
		List<PromocionMarca> promosMarca = new ArrayList<PromocionMarca>();
		
		Carrito carrito = new Carrito(cliente, promosTarjeta, promosMarca);
		
		carrito.agregarProducto(producto, 2);
		
		Venta venta = carrito.efectuarVenta(tarjeta);
		
		double[] precio = {venta.total()}, esperado = {552};
		
		Assert.assertArrayEquals(esperado, precio, 0.01);
		
	}
	
	@Test
	public void montoTotalAmbosDescuentos() throws EmptyStringException, InvalidEmailException, IllegalNumberException, ParseException, IllegalArgumentException, DateOverlapException, EmptyProductListException {
		
		Cliente cliente = new Cliente("Alfonso", "Ramirez", 39864572, "a.ramirez@gmail.com");
		
		String marca = "Samsung", empresa = "MemeCard";
		
		Categoria categoria = new Categoria("Electrónica");
		
		Producto producto = new Producto("Auriculares Samsung", 300, "Samsung", categoria);
		
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
		
		double[] precio = {venta.total()}, esperado = {524.4};
		
		Assert.assertArrayEquals(esperado, precio, 0.01);
		
	}

}
