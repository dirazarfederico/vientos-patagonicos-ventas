package ar.unrn.tp.modelo;

import ar.unrn.tp.excepciones.DateOverlapException;
import ar.unrn.tp.excepciones.IllegalNumberException;

public abstract class Promocion {
	private FechaHora fechaInicio;
	private FechaHora fechaFin;
	private double descuento;
	
	public Promocion(FechaHora inicio, FechaHora fin, double descuento) throws DateOverlapException, IllegalNumberException {
		if(inicio.despues(fin))
			throw new DateOverlapException("La fecha de inicio de promoción no puede ser posterior a la fecha de finalización");
		if(inicio.equals(fin))
			throw new DateOverlapException("Las fechas de inicio y fin de una promoción no pueden ser iguales");
		if(descuento<0.01)
			throw new IllegalNumberException("El descuento debe ser mayor a 0");
		
		this.fechaInicio = inicio;
		this.fechaFin = fin;
	}
	
	public boolean vigente() {
		FechaHora hoy = new FechaHora();
		return hoy.despues(fechaInicio) && hoy.antes(fechaFin);
	}
	
	public abstract boolean valido(Object o);
	
	public double calcularDescuento(Object o, double monto) {
		return vigente() && valido(o) ? monto * descuento : monto;
	}

}
