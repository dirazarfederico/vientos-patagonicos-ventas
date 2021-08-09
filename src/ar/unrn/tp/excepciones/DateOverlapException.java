package ar.unrn.tp.excepciones;

public class DateOverlapException extends Exception {
	private String msj;
	
	public DateOverlapException(String msj) {
		super(msj);
	}
}
