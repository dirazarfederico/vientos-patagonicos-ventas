package ar.unrn.tp.modelo;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import ar.unrn.tp.excepciones.DateOverlapException;
import ar.unrn.tp.excepciones.EmptyStringException;

public class PromocionMarcaTest {

	@Test
	public void promocionFechasSolapadas() throws ParseException, EmptyStringException, DateOverlapException {
		
		//set up
		FechaHora inicio, fin;
		Marca marca = new Marca("Nike");
		
		inicio = new FechaHora("09/08/2021 12:00");
		fin = new FechaHora("08/08/2021 13:58");
		
		//exercise
		//promoMarca = new PromocionMarca(inicio, fin, marca);
		
		//verify
		Assert.assertThrows(DateOverlapException.class, () -> {
			PromocionMarca promoMarca = new PromocionMarca(inicio, fin, marca);
		});
	}
	
}
