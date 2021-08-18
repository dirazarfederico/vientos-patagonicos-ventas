package ar.unrn.tp.modelo;

public class ProductoVendido {
	private long codigoProducto;
	private double precioProducto;
	private String descripcionProducto;
	private int cantidad;
	
	public ProductoVendido(Producto prod, int cant, double precio) {
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
}
