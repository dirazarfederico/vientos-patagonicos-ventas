package ar.unrn.tp.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import ar.unrn.tp.excepciones.EmptyStringException;

@Entity
public class Categoria {
	@Id
	@GeneratedValue
	private long id;
	private String nombre;
	
	protected Categoria() {
		
	}
	
	public Categoria(String nombre) throws EmptyStringException {
		if(nombre==null||nombre.isEmpty())
			throw new EmptyStringException("La categoría necesita un nombre");
		this.nombre = nombre;
	}
	
	private long getId() {
		return id;
	}
	private void setId(long id) {
		this.id = id;
	}
	private String getNombre() {
		return nombre;
	}
	private void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
