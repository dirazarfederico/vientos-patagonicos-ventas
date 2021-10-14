package ar.unrn.tp.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ar.unrn.tp.excepciones.EmptyProductListException;

@Entity
public class Venta {
	@Id @GeneratedValue
	private long id;
	@ManyToOne
	private Cliente cliente;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private FechaHora fechaHora;
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "venta_id")
	private List<ProductoVendido> detalle;
	@ManyToOne
	private TarjetaCredito tarjeta;
	private double total;
	
	protected Venta() {
		
	}
	
	public Venta(List<ProductoVendido> productos, Cliente cliente, TarjetaCredito tarjeta, double total) throws EmptyProductListException {
		this.cliente = cliente;
		if(productos.isEmpty())
			throw new EmptyProductListException("Debe agregar productos al carrito antes de poder comprar.");
		this.detalle = productos;
		this.tarjeta = tarjeta;
		this.fechaHora = new FechaHora();
		this.total = total;
	}
	
	public double total() {
		return this.total;
	}
	
	public Map<String, Object> toMap() {
		List<Map<String, Object>> listaMapas = new ArrayList<Map<String, Object>>();
		
		detalle.forEach((prodVendido)-> {
			listaMapas.add(prodVendido.toMap());
		});
		
		return Map.of("id", id, "fechaHora", fechaHora.toString(), "detalle", Map.of("productosVendidos", listaMapas), "tarjeta", tarjeta.empresa(), "total", total);
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
