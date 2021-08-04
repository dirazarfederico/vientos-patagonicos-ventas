package ar.unrn.tp.modelo;

import ar.unrn.tp.excepciones.EmptyStringException;
import ar.unrn.tp.excepciones.InvalidEmailException;
import ar.unrn.tp.excepciones.InvalidIDException;
import ar.unrn.tp.helper.EmailValidator;

public class Cliente {
	private String nombre;
	private String apellido;
	private int dni;
	private String email;
	
	public Cliente(String nombre, String apellido, int dni, String email) throws EmptyStringException, InvalidIDException, InvalidEmailException {
		if(nombre==null||nombre.isEmpty())
			throw new EmptyStringException("Debe ingresar un nombre");
		if(apellido==null||apellido.isEmpty())
			throw new EmptyStringException("Debe ingresar un apellido");
		if(dni<0||dni>99999999)
			throw new InvalidIDException("Debe ingresar un dni válido");
		if(!EmailValidator.validarMail(email))
			throw new InvalidEmailException("Debe ingresar un email válido");
		
		this.nombre=nombre.trim();
		this.apellido=apellido.trim();
		this.dni=dni;
		this.email=email.trim();
	}
	
}
