package ar.unrn.tp.modelo;

import java.util.Map;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductoVendido {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long codigoProducto;
	private double precioProducto;
	private String descripcionProducto;
	private int cantidad;
	
	protected ProductoVendido() {
		
	}
	
	public ProductoVendido(Producto prod, int cant, double precio) {
		this.precioProducto = precio;
		this.codigoProducto = prod.codigo();
		this.descripcionProducto = prod.descripcion();
		this.cantidad = cant;
	}
	
	public Map<String, Object> toMap() {
		return Map.of("id", id, "descripcion", descripcionProducto, "precio", precioProducto);
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

	private long getCodigoProducto() {
		return codigoProducto;
	}

	private void setCodigoProducto(long codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	private double getPrecioProducto() {
		return precioProducto;
	}

	private void setPrecioProducto(double precioProducto) {
		this.precioProducto = precioProducto;
	}

	private String getDescripcionProducto() {
		return descripcionProducto;
	}

	private void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	private int getCantidad() {
		return cantidad;
	}

	private void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
