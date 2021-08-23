package ar.unrn.tp.modelo;

import ar.unrn.tp.excepciones.DateOverlapException;
import ar.unrn.tp.excepciones.IllegalNumberException;

public abstract class Promocion {
	private FechaHora fechaInicio;
	private FechaHora fechaFin;
	private double descuento;
	
	public Promocion(FechaHora inicio, FechaHora fin, double descuento) throws DateOverlapException, IllegalNumberException {
		if(inicio.despues(fin))
			throw new DateOverlapException("La fecha de inicio de promocion no puede ser posterior a la fecha de finalizacion");
		if(inicio.equals(fin))
			throw new DateOverlapException("Las fechas de inicio y fin de una promocion no pueden ser iguales");
		if(descuento<0.01)
			throw new IllegalNumberException("El descuento debe ser mayor a 0");
		
		this.fechaInicio = inicio;
		this.fechaFin = fin;
		this.descuento = descuento;
	}
	
	public boolean vigente() {
		FechaHora hoy = new FechaHora();
		return hoy.despues(fechaInicio) && hoy.antes(fechaFin);
	}
	
	public abstract boolean valido(IPromocionable promocionable);
	
	public double calcularDescuento(IPromocionable promocionable, double monto) {
		return vigente() && valido(promocionable) ? monto * (1 - descuento) : monto;
	}

}
