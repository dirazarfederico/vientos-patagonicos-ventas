package ar.unrn.tp.excepciones;

public class InvalidIDException extends Exception {
	String msj;
	
	public InvalidIDException(String msj) {
		super(msj);
	}
}
