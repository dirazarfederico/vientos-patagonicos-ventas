package ar.unrn.tp.modelo;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import ar.unrn.tp.excepciones.EmptyStringException;
import ar.unrn.tp.excepciones.IllegalNumberException;
import ar.unrn.tp.excepciones.InvalidEmailException;

public class ClienteTest {
	
	@Test
	public void clienteSinDni( ) {
		
		Assert.assertThrows(IllegalNumberException.class, () -> {
			Cliente cliente = new Cliente("Alfonso", "Ramirez", 0, "a.ramirez@gmail.com");
		});
	}
	
	@Test
	public void clienteDniNegativo( ) {
		
		Assert.assertThrows(IllegalNumberException.class, () -> {
			Cliente cliente = new Cliente("Alfonso", "Ramirez", -10, "a.ramirez@gmail.com");
		});
	}
	
	@Test
	public void clienteSinNombre( ) {
		
		Assert.assertThrows(EmptyStringException.class, () -> {
			Cliente cliente = new Cliente(null, "Ramirez", 42345421, "a.ramirez@gmail.com");
		});
	}
	
	@Test
	public void clienteNombreVacio( ) {
		
		Assert.assertThrows(EmptyStringException.class, () -> {
			Cliente cliente = new Cliente("", "Ramirez", 42345421, "a.ramirez@gmail.com");
		});
	}
	
	@Test
	public void clienteSinApellido( ) {
		
		Assert.assertThrows(EmptyStringException.class, () -> {
			Cliente cliente = new Cliente("Alfonso", null, 42345421, "a.ramirez@gmail.com");
		});
	}
	
	@Test
	public void clienteApellidoVacio( ) {
		
		Assert.assertThrows(EmptyStringException.class, () -> {
			Cliente cliente = new Cliente("Alfonso", "", 42345421, "a.ramirez@gmail.com");
		});
	}
	
	@Test
	public void clienteSinEmail( ) {
		
		Assert.assertThrows(InvalidEmailException.class, () -> {
			Cliente cliente = new Cliente("Alfonso", "Ramirez", 42345421, null);
		});
	}
	
	@Test
	public void clienteEmailVacio( ) {
		
		Assert.assertThrows(EmptyStringException.class, () -> {
			Cliente cliente = new Cliente("Alfonso", "Ramirez", 42345421, "");
		});
	}
	
}
