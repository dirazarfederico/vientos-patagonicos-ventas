package ar.unrn.tp.excepciones;

public class IllegalNumberException extends Exception {
	private String msj;
	
	public IllegalNumberException(String msj) {
		super(msj);
	}
}
