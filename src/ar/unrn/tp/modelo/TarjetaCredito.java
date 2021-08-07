package ar.unrn.tp.modelo;

public class TarjetaCredito {
	private long numero;
	private String empresa;
	
	public TarjetaCredito(long numero, String empresa) {
		this.numero=numero;
		this.empresa=empresa;
	}
	
	public String empresa() {
		return this.empresa;
	}
}
