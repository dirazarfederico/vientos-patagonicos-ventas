package ar.unrn.tp.modelo;

import java.util.List;

public class Venta {
	private Cliente cliente;
	private FechaHora fechaHora;
	private List<ProductoVendido> detalle;
	private TarjetaCredito tarjeta;
	private double total;
	
	public Venta(Cliente cliente, TarjetaCredito tarjeta) {
		this.cliente = cliente;
		this.detalle = cliente.carrito().productos();
		this.tarjeta = tarjeta;
		this.fechaHora = new FechaHora();
	}
	
}
