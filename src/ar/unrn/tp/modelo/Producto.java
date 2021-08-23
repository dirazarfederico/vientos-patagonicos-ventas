package ar.unrn.tp.modelo;

import ar.unrn.tp.excepciones.EmptyStringException;
import ar.unrn.tp.excepciones.IllegalNumberException;

public class Producto implements IPromocionable {
	private long codigo;
	private String descripcion;
	private double precio;
	private String marca;
	private String categoria;
	
	public Producto(String descripcion, double precio, String marca, String categoria) throws EmptyStringException, IllegalNumberException {
		if(descripcion==null||descripcion.isEmpty())
			throw new EmptyStringException("El producto debe tener una descripcion");
		if(precio<=0.00)
			throw new IllegalNumberException("El precio no puede ser menor o igual a 0");
		if(marca==null||marca.isEmpty())
			throw new EmptyStringException("El producto debe tener una marca");
		if(categoria==null||categoria.isEmpty())
			throw new EmptyStringException("El producto debe tener una categoria");

		this.descripcion = descripcion;
		this.precio = precio;
		this.marca = marca;
		this.categoria = categoria;
	}


	public long codigo() {
		return this.codigo;
	}
	
	public double precio() {
		return this.precio;
	}
	
	public String descripcion() {
		return this.descripcion;
	}
	
	public String marca() {
		return this.marca;
	}
	
	public String categoria() {
		return this.categoria;
	}


	@Override
	public boolean esValido(String s) {
		return this.marca.equals(s);
	}


	@Override
	public boolean esValido(TarjetaCredito t) {
		return false;
	}
}
