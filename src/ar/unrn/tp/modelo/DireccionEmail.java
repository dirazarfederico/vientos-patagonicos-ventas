package ar.unrn.tp.modelo;

import javax.persistence.Embeddable;

import ar.unrn.tp.excepciones.EmptyStringException;

@Embeddable
public class DireccionEmail {
	private String direccion;
	private static final String[] dominios = {"@gmail.com", "@hotmail.com", "@outlook.com", "@yahoo.com.ar"};
	
	protected DireccionEmail() {
		
	}
	
	public DireccionEmail(String email) throws EmptyStringException {
		if(email==null||email.isEmpty()) {
			throw new EmptyStringException("Debe ingresar un email");
		}
		direccion = email.trim();
	}
	
	public boolean esValido() {
		
		boolean localValido = false, localNoVacio = false, localUnArroba = false, dominioValido = false;
		
		for(String dominio : DireccionEmail.dominios) {
			if(this.direccion.endsWith(dominio)) {
				dominioValido = true;
				break;
			}
		}
		
		if(dominioValido) {
			String local = this.direccion.substring(0, this.direccion.lastIndexOf('@'));
			
			if(!local.isEmpty())
				localNoVacio = true;
			
			// Verifica que la secci�n local no tenga dos o m�s @
			if(this.direccion.substring(0, this.direccion.indexOf('@')).equalsIgnoreCase(local))
				localUnArroba = true;
		}
		
		localValido = localNoVacio && localUnArroba;
		
		return localValido && dominioValido;
	}
	
	public boolean equals(DireccionEmail email) {
		return this.toString().equals(email.toString());
	}
	
	public String toString() {
		return direccion;
	}

	private String getDireccion() {
		return direccion;
	}

	private void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	private static String[] getDominios() {
		return dominios;
	}
	
	
}
