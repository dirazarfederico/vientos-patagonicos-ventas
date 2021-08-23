package ar.unrn.tp.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TarjetaCredito implements IPromocionable {
	@Id
	private long id;
	private long numero;
	private String empresa;

	protected TarjetaCredito() {
		
	}
	
	public TarjetaCredito(long numero, String empresa) {
		this.numero = numero;
		this.empresa = empresa;
	}
	
	public String empresa() {
		return this.empresa;
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

	@Override
	public boolean esValido(String s) {
		return false;
	}

	@Override
	public boolean esValido(TarjetaCredito t) {
		return this.empresa.equals(t.empresa);
	}
	
	private long getId() {
		return id;
	}

	private void setId(long id) {
		this.id = id;
	}

	private long getNumero() {
		return numero;
	}

	private void setNumero(long numero) {
		this.numero = numero;
	}

	private String getEmpresa() {
		return empresa;
	}

	private void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
}
