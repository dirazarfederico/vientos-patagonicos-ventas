package ar.unrn.tp.excepciones;

public class EmptyStringException extends Exception {
	String msj;
	
	public EmptyStringException(String msj) {
		super(msj);
	}
}
