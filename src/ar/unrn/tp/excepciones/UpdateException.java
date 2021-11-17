package ar.unrn.tp.excepciones;

public class UpdateException extends Exception {
	private String msj;
	
	public UpdateException(String msj) {
		super(msj);
	}
}
