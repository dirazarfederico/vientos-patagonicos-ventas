package ar.unrn.tp.modelo;

import ar.unrn.tp.excepciones.DateOverlapException;
import ar.unrn.tp.excepciones.IllegalNumberException;

public class PromocionTarjeta extends Promocion {
	private TarjetaCredito tarjeta;
	
	public PromocionTarjeta(FechaHora inicio, FechaHora fin, double descuento, TarjetaCredito tarjeta) throws DateOverlapException, IllegalNumberException, IllegalArgumentException {
		super(inicio, fin, descuento);
		if(tarjeta==null)
			throw new IllegalArgumentException("Se requiere de una tarjeta para dar de alta una promocion de tarjeta");
		this.tarjeta = tarjeta;
	}

	@Override
	public boolean valido(IPromocionable promocionable) {
		return promocionable.esValido(this.tarjeta);
	}
}
