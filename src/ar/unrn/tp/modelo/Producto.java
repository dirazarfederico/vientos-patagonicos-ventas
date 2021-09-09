package ar.unrn.tp.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import ar.unrn.tp.excepciones.EmptyStringException;
import ar.unrn.tp.excepciones.IllegalNumberException;

@Entity
public class Producto implements IPromocionable {
	@Id
	@GeneratedValue
	private long codigo;
	private String descripcion;
	private double precio;
	private String marca;
	private Categoria categoria;
	
	protected Producto() {
		
	}
	
	public Producto(String descripcion, double precio, String marca, Categoria categoria) throws EmptyStringException, IllegalNumberException {
		if(descripcion==null||descripcion.isEmpty())
			throw new EmptyStringException("El producto debe tener una descripcion");
		if(precio<=0.00)
			throw new IllegalNumberException("El precio no puede ser menor o igual a 0");
		if(marca==null||marca.isEmpty())
			throw new EmptyStringException("El producto debe tener una marca");
		if(categoria==null)
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
	
	public Categoria categoria() {
		return this.categoria;
	}

	public void cambiarDescripcion(String desc) throws EmptyStringException {
		if(descripcion==null||descripcion.isEmpty())
			throw new EmptyStringException("El producto debe tener una descripcion");
		this.descripcion = desc;
	}
	
	public void cambiarPrecio(double precio) throws IllegalNumberException {
		if(precio<=0.00)
			throw new IllegalNumberException("El precio no puede ser menor o igual a 0");
		this.precio = precio;
	}
	
	public void cambiarMarca(String marca) throws EmptyStringException {
		if(marca==null||marca.isEmpty())
			throw new EmptyStringException("El producto debe tener una marca");
		this.marca = marca;
	}
	
	public void cambiarCategoria(Categoria categoria) throws EmptyStringException {
		if(categoria==null)
			throw new EmptyStringException("El producto debe tener una categoria");
		this.categoria = categoria;
	}
	
	@Override
	public boolean esValido(String s) {
		return this.marca.equals(s);
	}

	private long getCodigo() {
		return codigo;
	}


	private void setCodigo(long codigo) {
		this.codigo = codigo;
	}


	private String getDescripcion() {
		return descripcion;
	}


	private void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	private double getPrecio() {
		return precio;
	}


	private void setPrecio(double precio) {
		this.precio = precio;
	}


	private String getMarca() {
		return marca;
	}


	private void setMarca(String marca) {
		this.marca = marca;
	}


	private Categoria getCategoria() {
		return categoria;
	}


	private void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
}
