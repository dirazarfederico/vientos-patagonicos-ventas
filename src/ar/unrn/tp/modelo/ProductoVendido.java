package ar.unrn.tp.modelo;

public class ProductoVendido {
	private long codigoProducto;
	private double precioProducto;
	private String descripcionProducto;
	private int cantidad;
	
	public ProductoVendido(Producto prod, int cant, double precio) {
		this.precioProducto = precio;
		this.codigoProducto = prod.codigo();
		this.descripcionProducto = prod.descripcion();
		this.cantidad = cant;
	}
	
	public double subtotal() {
		return precioProducto * cantidad;
	}
	
	public String descripcion() {
		return this.descripcionProducto;
	}
	
	public long codigo() {
		return this.codigoProducto;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.codigoProducto);
		sb.append(" ");
		sb.append(this.precioProducto);
		sb.append(" ");
		sb.append(this.descripcionProducto);
		sb.append(" ");
		sb.append(this.cantidad);
		return sb.toString();
	}
}
