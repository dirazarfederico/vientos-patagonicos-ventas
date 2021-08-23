package ar.unrn.tp.modelo;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import ar.unrn.tp.excepciones.EmptyStringException;
import ar.unrn.tp.excepciones.IllegalNumberException;

public class ProductoTest {

	@Test
	public void productoSinMarca( ) {
		
		Assert.assertThrows(EmptyStringException.class, () -> {
			Producto producto = new Producto("Auriculares Samsung", 300.5, null, "Electronica");
		});
		
	}
	
	@Test
	public void productoMarcaVacia( ) {
		
		Assert.assertThrows(EmptyStringException.class, () -> {
			Producto producto = new Producto("Auriculares Samsung", 300.5, "", "Electronica");
		});
		
	}
	
	@Test
	public void productoSinCategoria( ) {
		
		Assert.assertThrows(EmptyStringException.class, () -> {
			Producto producto = new Producto("Auriculares Samsung", 300.5, "Samsung", null);
		});
		
	}
	
	@Test
	public void productoCategoriaVacia( ) {
		
		Assert.assertThrows(EmptyStringException.class, () -> {
			Producto producto = new Producto("Auriculares Samsung", 300.5, "Samsung", "");
		});
		
	}
	
	@Test
	public void productoSinPrecio( ) {
		
		Assert.assertThrows(IllegalNumberException.class, () -> {
			Producto producto = new Producto("Auriculares Samsung", 0, "Samsung", "Electronica");
		});
		
	}
	
	@Test
	public void productoPrecioNegativo( ) {
		
		Assert.assertThrows(IllegalNumberException.class, () -> {
			Producto producto = new Producto("Auriculares Samsung", -1, "Samsung", "Electronica");
		});
		
	}
}
