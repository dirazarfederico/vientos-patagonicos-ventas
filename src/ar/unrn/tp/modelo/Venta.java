package ar.unrn.tp.modelo;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Venta {
	private Cliente cliente;
	private LocalDateTime fechaHora;
	private List<ProductoVendido> detalle;
	private TarjetaCredito tarjeta;
	
	public Venta(Carrito carrito, TarjetaCredito tarjeta) {
		this.detalle = carrito.productos();
		this.cliente = carrito.cliente();
		this.tarjeta = tarjeta;
		this.fechaHora = Instant.ofEpochMilli(new Date().getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	
	public LocalDateTime fechaHora() {
		return this.fechaHora;
	}
	
}
