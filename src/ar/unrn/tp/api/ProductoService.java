package ar.unrn.tp.api;

import java.util.List;

import ar.unrn.tp.excepciones.UpdateException;
import ar.unrn.tp.modelo.Categoria;
import ar.unrn.tp.modelo.Producto;

public interface ProductoService {

	/** 
	 * Agrega un producto nuevo al sistema
	 * Validar que sea una categoría existente y que codigo no se repita
	 * @param codigo (id)
	 * @param descripcion
	 * @param precio
	 * @param IdCategor�a
	 */
	void crearProducto(String codigo, String descripcion, double precio, String marca, Long IdCategoria);
	
	/**
	 * Modifica un producto existente
	 * Valida que sea un producto existente
	 * @param idProducto
	 * @param descripcion
	 * @param precio
	 * @param marca
	 * @param IdCategoria
	 * @throws UpdateException 
	 */
	void modificarProducto(Long idProducto, String descripcion, double precio, String marca, Long IdCategoria, Long version) throws UpdateException;
	
	 /**
	  * Devuelve todos los productos
	  * @return Una lista con todos los productos
	  */
	 List<Producto> listarProductos();
	 
	 /**
	  * Devuelve las categorías
	  * @return Una lista con todas las categorías
	  */
	 List<Categoria> listarCategorias();
	
}
