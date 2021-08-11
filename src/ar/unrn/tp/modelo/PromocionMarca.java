package ar.unrn.tp.modelo;

import ar.unrn.tp.excepciones.DateOverlapException;
import ar.unrn.tp.excepciones.IllegalNumberException;

public class PromocionMarca extends Promocion {
	private Marca marca;
	
	public PromocionMarca(FechaHora inicio, FechaHora fin, double descuento, Marca marca) throws DateOverlapException, IllegalArgumentException, IllegalNumberException {
		super(inicio, fin, descuento);
		if(marca==null)
			throw new IllegalArgumentException("Se requiere de una tarjeta para dar de alta una promoción de tarjeta");
		this.marca = marca;
	}
}
