package ar.unrn.tp.jpa.servicios;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import ar.unrn.tp.api.PromocionService;
import ar.unrn.tp.excepciones.EmptyStringException;
import ar.unrn.tp.modelo.FechaHora;
import ar.unrn.tp.modelo.PromocionMarca;
import ar.unrn.tp.modelo.PromocionTarjeta;
import ar.unrn.tp.modelo.TarjetaCredito;

public class JPAObjectDBPromocion implements PromocionService {

	private String contexto;
	
	public JPAObjectDBPromocion(String nuevoContexto) throws EmptyStringException {
		if(nuevoContexto==null||nuevoContexto.isEmpty())
			throw new EmptyStringException("Debe indicar un contexto de persistencia");
		this.contexto = nuevoContexto;
	}
	
	@Override
	public void crearDescuentoSobreTotal(String marcaTarjeta, LocalDate fechaDesde, LocalDate fechaHasta,
			double porcentaje) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory(contexto);
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			FechaHora inicio, fin;
			
			inicio = new FechaHora(fechaDesde.atTime(LocalTime.MIDNIGHT));
			
			fin = new FechaHora(fechaHasta.atTime(LocalTime.MAX));
			
			PromocionTarjeta promoTarjeta = new PromocionTarjeta(inicio, fin, porcentaje, marcaTarjeta);
			
			em.persist(promoTarjeta);
			
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
	public void crearDescuento(String marcaProducto, LocalDate fechaDesde, LocalDate fechaHasta, double porcentaje) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory(contexto);
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			FechaHora inicio, fin;
			
			inicio = new FechaHora(fechaDesde.atTime(LocalTime.MIDNIGHT));
			
			fin = new FechaHora(fechaHasta.atTime(LocalTime.MAX));
			
			PromocionMarca promoMarca = new PromocionMarca(inicio, fin, porcentaje, marcaProducto);
			
			em.persist(promoMarca);
			
			tx.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			tx.rollback();
			throw new RuntimeException(e);
		} finally {
			if (em != null && em.isOpen())
				em.close();
			if (emf != null)
				emf.close();
		}
	}
	
}
