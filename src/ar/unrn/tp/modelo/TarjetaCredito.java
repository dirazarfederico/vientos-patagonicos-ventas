package ar.unrn.tp.modelo;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Cascade;

import ar.unrn.tp.excepciones.EmptyStringException;
import ar.unrn.tp.excepciones.IllegalNumberException;

@Entity
public class TarjetaCredito implements IPromocionable {
	@Id @GeneratedValue
	private long id;
	private long numero;
	private String empresa;

	protected TarjetaCredito() {
		
	}
	
	public TarjetaCredito(long numero, String empresa) throws IllegalNumberException, EmptyStringException {
		if(numero<=0)
			throw new IllegalNumberException("Se debe ingresar un número para la tarjeta");
		if(empresa==null||empresa.isEmpty())
			throw new EmptyStringException("Se debe ingresar la empresa de la tarjeta de crédito");
		this.numero = numero;
		this.empresa = empresa;
	}
	
	public String empresa() {
		return this.empresa;
	}
	
	public Map<String, Object> toMap() {
		return Map.of("id", id, "numero", numero, "empresa", empresa);
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
		return this.empresa.equals(s);
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

	private long getId() {
		return id;
	}

	private void setId(long id) {
		this.id = id;
	}
	
	
}
