package ar.unrn.tp.modelo;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import ar.unrn.tp.excepciones.InvalidEmailException;

public class ClienteTest {

	@Test
	public void emailDosArrobaTest() {
		Assert.assertThrows(InvalidEmailException.class, () -> {
			Cliente cliente = new Cliente("Rodolfo", "Rodríguez", 42153684, "rrodri@guez@gmail.com");
		});
		
	}
	
	@Test
	public void emailSinLocalTest() {
		Assert.assertThrows(InvalidEmailException.class, () -> {
			Cliente cliente = new Cliente("Rodolfo", "Rodríguez", 42153684, "@gmail.com");
		});
	}
	
}
