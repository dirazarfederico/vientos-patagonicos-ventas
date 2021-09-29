package ar.unrn.tp.jpa.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.excepciones.EmptyStringException;
import ar.unrn.tp.modelo.Cliente;
import ar.unrn.tp.modelo.TarjetaCredito;

public class JPAObjectDBCliente implements ClienteService {

	private String contexto;
	
	public JPAObjectDBCliente(String nuevoContexto) throws EmptyStringException {
		if(nuevoContexto==null||nuevoContexto.isEmpty())
			throw new EmptyStringException("Debe indicar un contexto de persistencia");
		this.contexto = nuevoContexto;
	}
	
	@Override
	public void crearCliente(String nombre, String apellido, String dni, String email) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory(contexto);
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			int dniNumero = Integer.parseInt(dni);
			
			TypedQuery<Cliente> query = em.createQuery("select c from Cliente c where c.dni = :dni", Cliente.class);
			query.setParameter("dni", dniNumero);
			
			List<Cliente> clientes = new ArrayList<Cliente>();
			
			clientes = query.getResultList();
			
			if(clientes.size()!=0) {
				throw new RuntimeException("Usted ya está registrado. Llame a atención al cliente.");
			}
			
			Cliente cliente = new Cliente(nombre, apellido, dniNumero, email);
			
			em.persist(cliente);
			
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
	public void modificarCliente(Long idCliente, String nombre, String apellido, String dni, String email) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory(contexto);
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			Cliente cliente = em.getReference(Cliente.class, idCliente);
			
			cliente.cambiarNombre(nombre);
			
			cliente.cambiarApellido(apellido);
			
			cliente.cambiarDni(Integer.parseInt(dni));
			
			cliente.cambiarMail(email);
			
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
	public void agregarTarjeta(Long idCliente, String nro, String marca) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory(contexto);
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			Cliente cliente = em.getReference(Cliente.class, idCliente);
			
			TarjetaCredito tarjeta = new TarjetaCredito(Long.parseLong(nro), marca);
			
			cliente.agregarTarjeta(tarjeta);
			
			em.persist(cliente);
			
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
	public List<TarjetaCredito> listarTarjetas(Long idCliente) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory(contexto);
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		List<TarjetaCredito> tarjetas = new ArrayList<TarjetaCredito>();
		
		try {
			tx.begin();
			
			//Valida que el cliente exista
			Cliente cliente = em.getReference(Cliente.class, idCliente);

			TypedQuery<TarjetaCredito> query = em.createQuery("select t from Cliente c join c.tarjetas t " + 
					"where c.id = :id", TarjetaCredito.class);
			query.setParameter("id", idCliente);
			
			tarjetas = query.getResultList();
			
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
		return tarjetas;
	}
	
}
