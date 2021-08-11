package ar.unrn.tp.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ar.unrn.tp.excepciones.IllegalNumberException;

public class Carrito {
	private HashMap<Producto, Integer> productos;
	private Cliente cliente;
	
	public Carrito() {
		productos = new HashMap<Producto, Integer>();
	}
	
	public void añadirProducto(Producto prod, int cantidad) throws IllegalArgumentException, IllegalNumberException {
		if(prod==null)
			throw new IllegalArgumentException("No puede agregar un producto vacío al carrito");
		if(cantidad<=0)
			throw new IllegalNumberException("La cantidad debe ser mayor a 0");
		productos.put(prod, cantidad);
	}
	
	public Cliente cliente() {
		return this.cliente;
	}
	
	
	public Venta efectuarVenta(TarjetaCredito tarjeta) {
		if(tarjeta==null)
			throw new IllegalArgumentException("Debe pagar con una tarjeta");
		
		List<ProductoVendido> productosResultado = new ArrayList<ProductoVendido>();
		double total = 0;
		this.productos.forEach((prod, cant) -> {
			ProductoVendido productoVendido = new ProductoVendido(prod, cant);
			productosResultado.add(productoVendido);
		});
		
		//Calcular descuento con promoción, si es nulo no hay descuento. Por donde llegan las promo??
		
		return new Venta(productosResultado, this.cliente, tarjeta, total);
	}
}
