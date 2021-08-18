package ar.unrn.tp.api;

import java.util.List;

import ar.unrn.tp.modelo.Producto;

public interface VentaService {

	/**
	 * Crea una venta. El monto se calcula aplicando los descuentos a la fecha
	 * validaciones:
	 * debe ser un cliente existente
	 * la lista de productos no debe estar vacía
	 * la tarjeta debe pertenecer al cliente
	 * @param idCliente
	 * @param productos
	 * @param idTarjeta
	 */
	 // 
	void realizarVenta(Long idCliente, List<Producto> productos, Long idTarjeta);
	
	/**
	 * Devuelve el monto total aplicando los descuentos al día de la fecha
	 * validar que no llegue una lista vacía y la tarjeta exista
	 * @param productos
	 * @param idTarjeta
	 * @return
	 */
	float calcularMonto(List<Producto> productos, Long idTarjeta);
	 /**
	  * Devuelve todas las ventas realizadas
	  * @return
	  */
	 List<Producto> ventas(); 
}
