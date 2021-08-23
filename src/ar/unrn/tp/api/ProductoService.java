package ar.unrn.tp.api;

import java.util.List;

import ar.unrn.tp.modelo.Producto;

public interface ProductoService {

	/** 
	 * Agrega un producto nuevo al sistema
	 * Validar que sea una categor�a existente y que codigo no se repita
	 * @param codigo (id)
	 * @param descripcion
	 * @param precio
	 * @param IdCategor�a
	 */
	void crearProducto(String codigo, String descripcion, float precio, Long IdCategoria);
	
	/**
	 * Modifica un producto existente
	 * Validar que sea un producto existente
	 * @param idProducto
	 * @param descripcion
	 * @param precio
	 * @param IdCategor�a
	 */
	void modificarProducto(Long idProducto, String descripcion, float precio, Long IdCategoria);
	
	 /**
	  * Devuelve todos los productos
	  * @return Una lista con todos los productos
	  */
	 List<Producto> listarProductos();
	
}
