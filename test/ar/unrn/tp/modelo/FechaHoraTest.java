package ar.unrn.tp.modelo;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class FechaHoraTest {
	
//	@Test
//	void fechaHoraActualTest() {
//		FechaHora actual = new FechaHora();
//		
//		Assert.assertNotNull(actual);
//	}
	
	@Test
	void fechaHoraLocalDateTime() {
		LocalDateTime fechaHora = LocalDateTime.of(2021, 7, 24, 11, 54);
		FechaHora deLocalDateTime = new FechaHora(fechaHora);
		
		System.out.println(deLocalDateTime);
		
		Assert.assertNotNull(deLocalDateTime);
	}
	
}
