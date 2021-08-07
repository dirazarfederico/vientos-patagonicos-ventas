package ar.unrn.tp.modelo;

import org.apache.commons.validator.routines.EmailValidator;
import ar.unrn.tp.excepciones.EmptyStringException;

public class DireccionEmail {
	private String direccion;
	
	public DireccionEmail(String email) throws EmptyStringException {
		if(email==null||email.isEmpty()) {
			throw new EmptyStringException("Debe ingresar un email");
		}
		direccion = email.trim();
	}
	
	public boolean esValido() {
		EmailValidator validator = EmailValidator.getInstance();
		
		return validator.isValid(this.direccion);
	}
	
	public boolean equals(DireccionEmail email) {
		return this.toString().equals(email.toString());
	}
	
	public String toString() {
		return direccion;
	}
}
