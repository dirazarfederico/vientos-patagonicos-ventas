package ar.unrn.tp.jpa.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.modelo.Categoria;
import ar.unrn.tp.modelo.Cliente;
import ar.unrn.tp.modelo.Producto;

public class JPAObjectDBProducto implements ProductoService {

	@Override
	public void crearProducto(String codigo, String descripcion, float precio, String marca, Long idCategoria) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("jpa-objectdb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			long id = Long.parseLong(codigo);
			
			TypedQuery<Producto> query = em.createQuery("select p from Producto p where p.codigo = :codigo", Producto.class);
			query.setParameter("codigo", id);
			
			List<Producto> productos = new ArrayList<Producto>();
			
			productos = query.getResultList();
			
			if(productos.size()==0) {
				
				Categoria categoria = em.find(Categoria.class, idCategoria);
				
				Producto producto = new Producto(descripcion, precio, marca, categoria);
				
				em.persist(producto);
			}
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally {
			if (em != null && em.isOpen())
				em.close();
			if (emf != null)
				emf.close();
		}
	}

	@Override
	public void modificarProducto(Long idProducto, String descripcion, float precio, String marca, Long IdCategoria) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("jpa-objectdb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			Producto producto = em.getReference(Producto.class, idProducto);
			
			producto.cambiarDescripcion(descripcion);
			
			producto.cambiarPrecio(precio);
			
			producto.cambiarMarca(marca);
			
			Categoria categoria = em.find(Categoria.class, IdCategoria);
			
			producto.cambiarCategoria(categoria);
			
			em.persist(producto);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally {
			if (em != null && em.isOpen())
				em.close();
			if (emf != null)
				emf.close();
		}
	}

	@Override
	public List<Producto> listarProductos() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("jpa-objectdb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		List<Producto> productos = new ArrayList<Producto>();
		
		try {
			tx.begin();
			
			TypedQuery<Producto> query = em.createQuery("select p from Producto p", Producto.class);
			
			productos = query.getResultList();
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException(e);
		} finally {
			if (em != null && em.isOpen())
				em.close();
			if (emf != null)
				emf.close();
		}
		return productos;
	}

}
