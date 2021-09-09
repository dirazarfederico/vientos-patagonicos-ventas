package ar.unrn.tp.modelo;

import javax.persistence.Entity;

import ar.unrn.tp.excepciones.DateOverlapException;
import ar.unrn.tp.excepciones.IllegalNumberException;

@Entity
public class PromocionMarca extends Promocion {
	private String marca;
	
	protected PromocionMarca() {
		
	}
	
	public PromocionMarca(FechaHora inicio, FechaHora fin, double descuento, String marca) throws DateOverlapException, IllegalArgumentException, IllegalNumberException {
		super(inicio, fin, descuento);
		if(marca==null)
			throw new IllegalArgumentException("Se requiere de una marca para dar de alta una promoci�n de marca");
		this.marca = marca;
	}

	@Override
	public boolean valido(IPromocionable promocionable) {
		return promocionable.esValido(this.marca);
	}

	private String getMarca() {
		return marca;
	}

	private void setMarca(String marca) {
		this.marca = marca;
	}
	
	
}
