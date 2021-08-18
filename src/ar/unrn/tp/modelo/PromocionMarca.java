package ar.unrn.tp.modelo;

import ar.unrn.tp.excepciones.DateOverlapException;
import ar.unrn.tp.excepciones.IllegalNumberException;

public class PromocionMarca extends Promocion {
	private String marca;
	
	public PromocionMarca(FechaHora inicio, FechaHora fin, double descuento, String marca) throws DateOverlapException, IllegalArgumentException, IllegalNumberException {
		super(inicio, fin, descuento);
		if(marca==null)
			throw new IllegalArgumentException("Se requiere de una marca para dar de alta una promoción de marca");
		this.marca = marca;
	}

	@Override
	public boolean valido(Object o) {
		Producto prod = (Producto) o;
		return prod.marca().equals(this.marca);
	}
}
