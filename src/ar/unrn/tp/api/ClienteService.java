package ar.unrn.tp.api;

import java.util.List;

import ar.unrn.tp.modelo.TarjetaCredito;

public interface ClienteService {

	/**
	 * Crea un cliente y lo carga en el sistema
	 * Valida que el DNI no se repita
	 * @param nombre
	 * @param apellido
	 * @param dni
	 * @param email
	 */
	void crearCliente(String nombre, String apellido, String dni, String email);
	/** 
	 * Modifica un cliente del sistema
	 * Verifica que sea un cliente existente
	 * @param idCliente
	 * @param nombre
	 */
	void modificarCliente(Long idCliente, String nombre, String dni, String email);
	
	/**
	 * Agrega una tarjeta de crédito a un cliente del sistema
	 * Valida que sea un cliente existente
	 * @param idCliente
	 * @param nro
	 * @param marca
	 */
	void agregarTarjeta(Long idCliente, String nro, String marca);

	 /**
	  * Devuelve las tarjetas de un cliente específico
	  * 
	  * @param idCliente
	  * @return
	  */
	 List<TarjetaCredito> listarTarjetas(Long idCliente);

	
	
}
