package ar.unrn.tp.web;

public class VentaDTO {
	private String cliente;
	private String[] detalle;
	private String tarjeta;
	
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String[] getDetalle() {
		return detalle;
	}
	public void setDetalle(String[] detalle) {
		this.detalle = detalle;
	}
	public String getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
	
	
}
