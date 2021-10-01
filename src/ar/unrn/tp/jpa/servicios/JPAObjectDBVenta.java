package ar.unrn.tp.jpa.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import ar.unrn.tp.api.VentaService;
import ar.unrn.tp.excepciones.EmptyStringException;
import ar.unrn.tp.modelo.Carrito;
import ar.unrn.tp.modelo.Cliente;
import ar.unrn.tp.modelo.Producto;
import ar.unrn.tp.modelo.PromocionMarca;
import ar.unrn.tp.modelo.PromocionTarjeta;
import ar.unrn.tp.modelo.TarjetaCredito;
import ar.unrn.tp.modelo.Venta;

public class JPAObjectDBVenta implements VentaService {

	private String contexto;
	
	public JPAObjectDBVenta(String nuevoContexto) throws EmptyStringException {
		if(nuevoContexto==null||nuevoContexto.isEmpty())
			throw new EmptyStringException("Debe indicar un contexto de persistencia");
		this.contexto = nuevoContexto;
	}
	
	public void realizarVenta(Long idCliente, List<Producto> productos, Long idTarjeta) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory(contexto);
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			Cliente cliente = em.find(Cliente.class, idCliente);
			
			TarjetaCredito tarjeta = em.find(TarjetaCredito.class, idTarjeta);
			
			List<PromocionTarjeta> promosTarjeta = new ArrayList<PromocionTarjeta>();
			List<PromocionMarca> promosMarca = new ArrayList<PromocionMarca>();
			
			TypedQuery<PromocionTarjeta> queryPromosTarjeta = em.createQuery("select p from PromocionTarjeta p", PromocionTarjeta.class);
			
			promosTarjeta = queryPromosTarjeta.getResultList();
			
			TypedQuery<PromocionMarca> queryPromosMarca = em.createQuery("select p from PromocionMarca p", PromocionMarca.class);
			
			promosMarca = queryPromosMarca.getResultList();
			
			Carrito carrito = new Carrito(cliente, promosTarjeta, promosMarca);
			
			for(Producto p : productos) {
				carrito.agregarProducto(p, 1);
			}
			
			Venta venta = carrito.efectuarVenta(tarjeta);
			
			em.persist(venta);
			
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

	public double calcularMonto(List<Producto> productos, Long idTarjeta) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory(contexto);
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		double total = 0;
		try {
			tx.begin();
			
			TarjetaCredito tarjeta = em.find(TarjetaCredito.class, idTarjeta);
			
			List<PromocionTarjeta> promosTarjeta = new ArrayList<PromocionTarjeta>();
			List<PromocionMarca> promosMarca = new ArrayList<PromocionMarca>();
			
			TypedQuery<PromocionTarjeta> queryPromosTarjeta = em.createQuery("select p from PromocionTarjeta p", PromocionTarjeta.class);
			
			promosTarjeta = queryPromosTarjeta.getResultList();
			
			TypedQuery<PromocionMarca> queryPromosMarca = em.createQuery("select p from PromocionMarca p", PromocionMarca.class);
			
			promosMarca = queryPromosMarca.getResultList();
			
			Carrito carrito = new Carrito(promosTarjeta, promosMarca);
			
			for(Producto p : productos) {
				carrito.agregarProducto(p, 1);
			}
			
			total = carrito.calcularMonto(tarjeta);
			
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
		return total;
	}

	public List<Venta> ventas() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory(contexto);
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		List<Venta> ventas = new ArrayList<Venta>();
		try {
			tx.begin();
			
			TypedQuery<Venta> query = em.createQuery("select v from Venta v", Venta.class);
			
			ventas = query.getResultList();
			
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
		return ventas;
	}

}
