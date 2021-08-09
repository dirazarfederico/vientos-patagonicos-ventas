package ar.unrn.tp.modelo;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
	private List<ProductoVendido> productos;
	
	public Carrito() {
		productos=new ArrayList<ProductoVendido>();
	}
	
	public boolean añadirProducto(Producto prod, int cantidad) {
		ProductoVendido prodVendido = new ProductoVendido(prod, cantidad);
		return productos.add(prodVendido);
	}
	
	public List<ProductoVendido> productos() {
		return this.productos;
	}
}
