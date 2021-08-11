package ar.unrn.tp.modelo;

import java.util.List;

public class Venta {
	private Cliente cliente;
	private FechaHora fechaHora;
	private List<ProductoVendido> detalle;
	private TarjetaCredito tarjeta;
	private double total;
	
	public Venta(List<ProductoVendido> productos, Cliente cliente, TarjetaCredito tarjeta, double total) {
		this.cliente = cliente;
		this.detalle = productos;
		this.tarjeta = tarjeta;
		this.fechaHora = new FechaHora();
		this.total = total;
	}
	
}
