package ar.unrn.tp.api;

import java.time.LocalDate;

public interface PromocionService {

	/**
	 * Carga una promoción de tarjeta
	 * Validar que las fechas no se superpongan
	 * @param marcaTarjeta
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param porcentaje
	 */
	void crearDescuentoSobreTotal(String marcaTarjeta, LocalDate fechaDesde,
	LocalDate fechaHasta, float porcentaje);

	/**
	 * Carga una promoción de marca
	 * Validar que las fechas no se superpongan
	 * @param marcaProducto
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param porcentaje
	 */
	void crearDescuento(String marcaProducto, LocalDate fechaDesde, LocalDate
	fechaHasta, float porcentaje);

	
}
