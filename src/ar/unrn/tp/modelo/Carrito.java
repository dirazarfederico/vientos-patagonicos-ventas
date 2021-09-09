package ar.unrn.tp.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import ar.unrn.tp.excepciones.IllegalNumberException;

public class Carrito {
	private HashMap<Producto, Integer> productos;
	private Cliente cliente;
	private List<PromocionTarjeta> promosTarjeta;
	private List<PromocionMarca> promosMarca;
	
	public Carrito(List<PromocionTarjeta> promosTarjeta, List<PromocionMarca> promosMarca) {
		if(promosTarjeta==null)
			throw new IllegalArgumentException("Debe ingresar las promociones de tarjeta vigentes");
		if(promosMarca==null)
			throw new IllegalArgumentException("Debe ingresar las promociones de marca vigentes");
		this.productos = new HashMap<Producto, Integer>();
		this.promosTarjeta = promosTarjeta;
		this.promosMarca = promosMarca;
	}
	
	public Carrito(Cliente cliente, List<PromocionTarjeta> promosTarjeta, List<PromocionMarca> promosMarca) throws IllegalArgumentException {
		if(cliente==null)
			throw new IllegalArgumentException("Debe ingresar un cliente para este carrito");
		if(promosTarjeta==null)
			throw new IllegalArgumentException("Debe ingresar las promociones de tarjeta vigentes");
		if(promosMarca==null)
			throw new IllegalArgumentException("Debe ingresar las promociones de marca vigentes");
		productos = new HashMap<Producto, Integer>();
		this.promosTarjeta = promosTarjeta;
		this.promosMarca = promosMarca;
		this.cliente = cliente;
	}
	
	public void agregarProducto(Producto prod, int cantidad) throws IllegalArgumentException, IllegalNumberException {
		if(prod==null)
			throw new IllegalArgumentException("No puede agregar un producto vacio al carrito");
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
			double monto = prod.precio();
			List<PromocionMarca> promocionesMarca = this.promosMarca.stream().filter((p) -> p.valido(prod)).collect(Collectors.toList());
			if(!promocionesMarca.isEmpty()) {
				monto = promocionesMarca.get(0).calcularDescuento(prod, monto);
			}
			ProductoVendido productoVendido;
			productoVendido = new ProductoVendido(prod, cant, monto);
			productosResultado.add(productoVendido);
		});
		
		total = productosResultado.stream().collect(Collectors.summingDouble((ProductoVendido p) -> p.subtotal()));
		
		List<PromocionTarjeta> promocionesTarjeta = this.promosTarjeta.stream().filter((t) -> t.valido(tarjeta)).collect(Collectors.toList());
		if(!promocionesTarjeta.isEmpty()) {
			total = promocionesTarjeta.get(0).calcularDescuento(tarjeta, total);
		}
		
		return new Venta(productosResultado, this.cliente, tarjeta, total);
	}
	
	public double calcularMonto(TarjetaCredito tarjeta) {
		if(tarjeta==null)
			throw new IllegalArgumentException("Debe pagar con una tarjeta");
		
		List<ProductoVendido> productosResultado = new ArrayList<ProductoVendido>();
		double total = 0;
		this.productos.forEach((prod, cant) -> {
			double monto = prod.precio();
			List<PromocionMarca> promocionesMarca = this.promosMarca.stream().filter((p) -> p.valido(prod)).collect(Collectors.toList());
			if(!promocionesMarca.isEmpty()) {
				monto = promocionesMarca.get(0).calcularDescuento(prod, monto);
			}
			ProductoVendido productoVendido;
			productoVendido = new ProductoVendido(prod, cant, monto);
			productosResultado.add(productoVendido);
		});
		
		total = productosResultado.stream().collect(Collectors.summingDouble((ProductoVendido p) -> p.subtotal()));
		
		List<PromocionTarjeta> promocionesTarjeta = this.promosTarjeta.stream().filter((t) -> t.valido(tarjeta)).collect(Collectors.toList());
		if(!promocionesTarjeta.isEmpty()) {
			total = promocionesTarjeta.get(0).calcularDescuento(tarjeta, total);
		}
		return total;
	}
}
