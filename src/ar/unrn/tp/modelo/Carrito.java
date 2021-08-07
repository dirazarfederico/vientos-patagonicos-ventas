package ar.unrn.tp.modelo;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
	private Cliente cliente;
	private List<ProductoVendido> productos;
	
	public Carrito(Cliente cliente) {
		this.cliente=cliente;
		productos=new ArrayList<ProductoVendido>();
	}
	
	public boolean añadirProducto(Producto prod, int cantidad) {
		ProductoVendido prodVendido = new ProductoVendido(prod, cantidad);
		return productos.add(prodVendido);
	}
	
	public List<ProductoVendido> productos() {
		return this.productos;
	}
	
	public Cliente cliente() {
		return this.cliente;
	}
}
