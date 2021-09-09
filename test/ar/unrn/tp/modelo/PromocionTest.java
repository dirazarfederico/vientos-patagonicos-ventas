package ar.unrn.tp.modelo;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import ar.unrn.tp.excepciones.DateOverlapException;
import ar.unrn.tp.excepciones.EmptyStringException;
import ar.unrn.tp.excepciones.IllegalNumberException;

public class PromocionTest {

	@Test
	public void promocionFechasSolapadas() throws ParseException, EmptyStringException, DateOverlapException, IllegalArgumentException, IllegalNumberException {
		
		//set up
		FechaHora inicio, fin;
		String marca = "Nike";
		
		inicio = new FechaHora("09/08/2021 12:00");
		fin = new FechaHora("08/08/2021 13:58");
		
		//exercise
		//promoMarca = new PromocionMarca(inicio, fin, marca);
		
		//verify
		Assert.assertThrows(DateOverlapException.class, () -> {
			PromocionMarca promoMarca = new PromocionMarca(inicio, fin, 0.05, marca);
		});
	}
	
	@Test
	public void promocionVigenteMismaMarca() throws ParseException, EmptyStringException, IllegalNumberException, IllegalArgumentException, DateOverlapException {
		FechaHora inicio, fin;
		String marca = "Samsung";
		
		inicio = new FechaHora("09/08/2021 12:00");
		fin = new FechaHora("08/12/2021 13:58");
		
		PromocionMarca promoMarca = new PromocionMarca(inicio, fin, 0.05, marca);
		
		Categoria categoria = new Categoria("Electrónica");
		
		Producto prod = new Producto("Auriculares Samsung", 300, marca, categoria);
		
		double descuento = promoMarca.calcularDescuento(prod, prod.precio());
		
		//verify
		double[] precio = {descuento}, esperado = {285};
		
		Assert.assertArrayEquals(esperado, precio, 0.01);
		
	}
	
	@Test
	public void promocionVigenteMismaTarjeta() throws ParseException, EmptyStringException, IllegalNumberException, IllegalArgumentException, DateOverlapException {
		FechaHora inicio, fin;
		String empresa = "Naranja";
		
		inicio = new FechaHora("09/08/2021 12:00");
		fin = new FechaHora("08/12/2021 13:58");
		
		TarjetaCredito tarjeta = new TarjetaCredito(126762L, empresa);
		
		PromocionTarjeta promoTarjeta = new PromocionTarjeta(inicio, fin, 0.08, empresa);
		
		Categoria categoria = new Categoria("Electrónica");
		
		Producto prod = new Producto("Auriculares Samsung", 300, "Samsung", categoria);
		
		double descuento = promoTarjeta.calcularDescuento(tarjeta, prod.precio());
		
		//verify
		double[] precio = {descuento}, esperado = {276};
		
		Assert.assertArrayEquals(esperado, precio, 0.01);
		
	}
	
//	@Test
//	public void promocionTarjetaLocalDate() throws IllegalNumberException, EmptyStringException, IllegalArgumentException, DateOverlapException {
//		FechaHora inicio, fin;
//		
//		LocalDate fechaDesde = LocalDate.of(2021, 8, 30) , fechaHasta = LocalDate.of(2021, 9, 30);
//		
//		String marcaTarjeta = "Memecard";
//		
//		inicio = new FechaHora(fechaDesde.atTime(LocalTime.MIDNIGHT));
//		
//		fin = new FechaHora(fechaHasta.atTime(LocalTime.MAX));
//
//		TarjetaCredito tarjeta = new TarjetaCredito(1, marcaTarjeta);
//		
//		PromocionTarjeta promoTarjeta = new PromocionTarjeta(inicio, fin, 0.08, tarjeta);
//		
//		Assert.assertNotNull(promoTarjeta);
//	}
}
