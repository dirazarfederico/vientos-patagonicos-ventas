package ar.unrn.tp.modelo;

import ar.unrn.tp.excepciones.EmptyStringException;

public class Marca {
	private String nombre;
	
	public Marca(String nombre) throws EmptyStringException {
		if(nombre==null || nombre.isEmpty())
			throw new EmptyStringException("La marca debe tener un nombre");
		this.nombre = nombre;
	}
	
	@Override
	public boolean equals(Object o) {
		Marca otraMarca = (Marca) o;
		return this.nombre.equals(otraMarca.nombre);
	}
}
