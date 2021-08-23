package ar.unrn.tp.modelo;

import java.util.ArrayList;
import java.util.List;

import ar.unrn.tp.excepciones.EmptyStringException;
import ar.unrn.tp.excepciones.IllegalNumberException;
import ar.unrn.tp.excepciones.InvalidEmailException;

public class Cliente {
	private String nombre;
	private String apellido;
	private int dni;
	private DireccionEmail email;
	private List<TarjetaCredito> tarjetas;
	
	public Cliente(String nombre, String apellido, int dni, String email) throws EmptyStringException, IllegalNumberException, InvalidEmailException {
		if(nombre==null||nombre.isEmpty())
			throw new EmptyStringException("Debe ingresar un nombre");
		if(apellido==null||apellido.isEmpty())
			throw new EmptyStringException("Debe ingresar un apellido");
		if(dni<10000000||dni>99999999)
			throw new IllegalNumberException("Debe ingresar un dni valido");
		
		DireccionEmail direccionEmail = new DireccionEmail(email);
		
		if(!direccionEmail.esValido())
			throw new InvalidEmailException("Debe ingresar un email valido");
		
		this.nombre = nombre.trim();
		this.apellido = apellido.trim();
		this.dni = dni;
		this.email = direccionEmail;
		this.tarjetas = new ArrayList<TarjetaCredito>();
	}
	
	public boolean agregarTarjeta(TarjetaCredito tarjeta) {
		return this.tarjetas.add(tarjeta);
	}
	
	public boolean eliminarTarjeta(TarjetaCredito tarjeta) {
		return this.tarjetas.remove(tarjeta);
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.nombre);
		sb.append(" ");
		sb.append(this.apellido);
		sb.append(" ");
		sb.append(this.dni);
		sb.append(" ");
		sb.append(this.email.toString());
		return sb.toString();
	}
	
}
