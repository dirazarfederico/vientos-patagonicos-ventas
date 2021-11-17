package ar.unrn.tp.modelo;

import java.util.Map;

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
	
	public String toString() {
		return this.nombre;
	}
	
	public Map<String, Object> toMap() {
		return Map.of("id", id, "name", nombre);
	}
	
	public Long id() {
		return this.id;
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
