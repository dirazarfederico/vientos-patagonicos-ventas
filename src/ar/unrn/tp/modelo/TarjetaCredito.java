package ar.unrn.tp.modelo;

public class TarjetaCredito {
	private long numero;
	private String empresa;
	
	public TarjetaCredito(long numero, String empresa) {
		this.numero = numero;
		this.empresa = empresa;
	}
	
	public String empresa() {
		return this.empresa;
	}
	
	public boolean mismaEmpresa(TarjetaCredito otraTarjeta) {
		return this.empresa.equals(otraTarjeta.empresa);
	}
	
	@Override
	public boolean equals(Object o) {
		TarjetaCredito otraTarjeta = (TarjetaCredito) o;
		if(this.empresa.equals(otraTarjeta.empresa)) {
			if(this.numero==otraTarjeta.numero)
				return true;
		}
		return false;
	}
}
