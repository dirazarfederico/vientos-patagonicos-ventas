package ar.unrn.tp.excepciones;

public class EmptyProductListException extends Exception {
	private String msj;
	
	public EmptyProductListException(String msj) {
		super(msj);
	}
}
