package ar.unrn.tp.excepciones;

public class InvalidEmailException extends Exception {
	String msj;
	
	public InvalidEmailException(String msj) {
		super(msj);
	}
}
