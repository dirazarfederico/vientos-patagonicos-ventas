package ar.unrn.tp.modelo;

import ar.unrn.tp.excepciones.EmptyStringException;
import ar.unrn.tp.excepciones.InvalidPriceException;

public class Producto {
	private static long ultimoCodigo;
	private long codigo;
	private String descripcion;
	private float precio;
	private String marca;
	private String categoria;
	
	public Producto(String descripcion, float precio, String marca, String categoria) throws EmptyStringException, InvalidPriceException {
		codigo = ++ultimoCodigo;
		if(descripcion==null||descripcion.isEmpty())
			throw new EmptyStringException("El producto debe tener una descripcion");
		if(precio<=0.00)
			throw new InvalidPriceException("El precio no puede ser menor o igual a 0");
		if(marca==null||marca.isEmpty())
			throw new EmptyStringException("El producto debe tener una marca");
		if(categoria==null||categoria.isEmpty())
			throw new EmptyStringException("El producto debe tener una categoria");
	}
}
