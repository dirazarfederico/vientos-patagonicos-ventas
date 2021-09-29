package ar.unrn.tp.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import ar.unrn.tp.excepciones.EmptyStringException;
import ar.unrn.tp.excepciones.IllegalNumberException;
import ar.unrn.tp.excepciones.InvalidEmailException;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nombre;
	private String apellido;
	private int dni;
//	@OneToOne(cascade = CascadeType.PERSIST)
	@Embedded
	private DireccionEmail email;
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_cliente")
	private List<TarjetaCredito> tarjetas;
	
	protected Cliente() {
		
	}
	
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
	
	public void cambiarNombre(String nombre) throws EmptyStringException {
		if(nombre==null||nombre.isEmpty())
			throw new EmptyStringException("Debe ingresar un nombre");
		this.nombre = nombre.trim();
	}
	
	public void cambiarApellido(String apellido) throws EmptyStringException {
		if(apellido==null||apellido.isEmpty())
			throw new EmptyStringException("Debe ingresar un apellido");
		this.apellido = apellido.trim();
	}
	
	public void cambiarDni(int dni) throws IllegalNumberException {
		if(dni<10000000||dni>99999999)
			throw new IllegalNumberException("Debe ingresar un dni valido");
		this.dni = dni;
	}
	
	public void cambiarMail(String email) throws InvalidEmailException, EmptyStringException {
		DireccionEmail direccionEmail = new DireccionEmail(email);
		
		if(!direccionEmail.esValido())
			throw new InvalidEmailException("Debe ingresar un email valido");
		this.email = direccionEmail;
	}
	
	public List<TarjetaCredito> tarjetas() {
		return this.tarjetas;
	}
	
	public String toString() {
		return nombre + " " + apellido + "" + dni + " " + tarjetas.size();
	}

	private String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private String getApellido() {
		return apellido;
	}

	private void setApellido(String apellido) {
		this.apellido = apellido;
	}

	private int getDni() {
		return dni;
	}

	private void setDni(int dni) {
		this.dni = dni;
	}

	private DireccionEmail getEmail() {
		return email;
	}

	private void setEmail(DireccionEmail email) {
		this.email = email;
	}

	private List<TarjetaCredito> getTarjetas() {
		return tarjetas;
	}

	private void setTarjetas(List<TarjetaCredito> tarjetas) {
		this.tarjetas = tarjetas;
	}
	
	
	
}
