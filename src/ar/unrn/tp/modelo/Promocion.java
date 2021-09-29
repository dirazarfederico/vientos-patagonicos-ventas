package ar.unrn.tp.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ar.unrn.tp.excepciones.DateOverlapException;
import ar.unrn.tp.excepciones.IllegalNumberException;

@Entity
public abstract class Promocion {
	@Id @GeneratedValue
	private long id;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private FechaHora fechaInicio;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private FechaHora fechaFin;
	private double descuento;
	
	protected Promocion() {
		
	}
	
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

	private long getId() {
		return id;
	}

	private void setId(long id) {
		this.id = id;
	}

	private FechaHora getFechaInicio() {
		return fechaInicio;
	}

	private void setFechaInicio(FechaHora fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	private FechaHora getFechaFin() {
		return fechaFin;
	}

	private void setFechaFin(FechaHora fechaFin) {
		this.fechaFin = fechaFin;
	}

	private double getDescuento() {
		return descuento;
	}

	private void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	
	
}
