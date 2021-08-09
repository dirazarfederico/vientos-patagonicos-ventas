package ar.unrn.tp.modelo;

import ar.unrn.tp.excepciones.EmptyStringException;
import ar.unrn.tp.excepciones.IllegalNumberException;

public class Producto {
	private static long ultimoCodigo;
	private long codigo;
	private String descripcion;
	private double precio;
	private Marca marca;
	private String categoria;
	
	public Producto(String descripcion, double precio, Marca marca, String categoria) throws EmptyStringException, IllegalNumberException {
		if(descripcion==null||descripcion.isEmpty())
			throw new EmptyStringException("El producto debe tener una descripcion");
		if(precio<=0.00)
			throw new IllegalNumberException("El precio no puede ser menor o igual a 0");
		if(marca==null)
			throw new EmptyStringException("El producto debe tener una marca");
		if(categoria==null||categoria.isEmpty())
			throw new EmptyStringException("El producto debe tener una categoria");
		
		this.codigo = ++ultimoCodigo;
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
}
