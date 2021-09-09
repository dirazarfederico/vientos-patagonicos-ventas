package ar.unrn.tp.modelo;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import ar.unrn.tp.excepciones.DateOverlapException;
import ar.unrn.tp.excepciones.EmptyStringException;
import ar.unrn.tp.excepciones.IllegalNumberException;

@Entity
public class PromocionTarjeta extends Promocion {
	private String empresa;
	
	protected PromocionTarjeta() {
	}
	
	public PromocionTarjeta(FechaHora inicio, FechaHora fin, double descuento, String empresa) throws DateOverlapException, IllegalNumberException, EmptyStringException {
		super(inicio, fin, descuento);
		if(empresa==null||empresa.isEmpty())
			throw new EmptyStringException("Se requiere una empresa para dar de alta una promoción de tarjeta");
		this.empresa = empresa;
	}

	@Override
	public boolean valido(IPromocionable promocionable) {
		return promocionable.esValido(this.empresa);
	}
	
	public boolean mismaEmpresa(String otraEmpresa) {
		return this.empresa.equals(otraEmpresa);
	}

	private String getEmpresa() {
		return empresa;
	}

	private void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	
	
	
}
