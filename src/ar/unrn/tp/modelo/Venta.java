package ar.unrn.tp.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Venta {
	@Id @GeneratedValue
	private long id;
	private Cliente cliente;
	private FechaHora fechaHora;
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<ProductoVendido> detalle;
	private TarjetaCredito tarjeta;
	private double total;
	
	protected Venta() {
		
	}
	
	public Venta(List<ProductoVendido> productos, Cliente cliente, TarjetaCredito tarjeta, double total) {
		this.cliente = cliente;
		this.detalle = productos;
		this.tarjeta = tarjeta;
		this.fechaHora = new FechaHora();
		this.total = total;
	}
	
	public double total() {
		return this.total;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.cliente);
		sb.append(" ");
		sb.append(this.fechaHora);
		sb.append(" ");
		sb.append(this.tarjeta.empresa());
		sb.append(" ");
		sb.append(this.total);
		sb.append(" ");
		sb.append(this.detalle);
		return sb.toString();
	}

	private Cliente getCliente() {
		return cliente;
	}

	private void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	private FechaHora getFechaHora() {
		return fechaHora;
	}

	private void setFechaHora(FechaHora fechaHora) {
		this.fechaHora = fechaHora;
	}

	private List<ProductoVendido> getDetalle() {
		return detalle;
	}

	private void setDetalle(List<ProductoVendido> detalle) {
		this.detalle = detalle;
	}

	private TarjetaCredito getTarjeta() {
		return tarjeta;
	}

	private void setTarjeta(TarjetaCredito tarjeta) {
		this.tarjeta = tarjeta;
	}

	private double getTotal() {
		return total;
	}

	private void setTotal(double total) {
		this.total = total;
	}

	private long getId() {
		return id;
	}

	private void setId(long id) {
		this.id = id;
	}
	
	
	
}
