package ar.unrn.tp.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ar.unrn.tp.excepciones.IllegalNumberException;

public class Carrito {
	private HashMap<Producto, Integer> productos;
	private Cliente cliente;
	private List<Promocion> promociones;
	
	public Carrito(Cliente cliente, List<Promocion> promos) throws IllegalArgumentException {
		if(cliente==null)
			throw new IllegalArgumentException("Debe ingresar un cliente para este carrito");
		if(promos==null)
			throw new IllegalArgumentException("Debe ingresar las promociones vigentes");
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
			// Obtener una promoción de la marca de este producto o ninguna promoción.
			// ¿Cómo filtrar las PromocionMarca de PromocionTarjeta?
			double monto = 0;
			ProductoVendido productoVendido = new ProductoVendido(prod, cant, monto);
			productosResultado.add(productoVendido);
		});
		
		//Al guardar en producto vendido, ya tiene el dcto
		
		return new Venta(productosResultado, this.cliente, tarjeta, total);
	}
}
