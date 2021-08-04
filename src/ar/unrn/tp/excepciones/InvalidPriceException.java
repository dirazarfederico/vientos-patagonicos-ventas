package ar.unrn.tp.excepciones;

public class InvalidPriceException extends Exception {
	private String msj;
	
	public InvalidPriceException(String msj) {
		super(msj);
	}
}
