package ar.unrn.tp.main;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.api.PromocionService;
import ar.unrn.tp.api.VentaService;
import ar.unrn.tp.jpa.servicios.JPAObjectDBCliente;
import ar.unrn.tp.jpa.servicios.JPAObjectDBProducto;
import ar.unrn.tp.jpa.servicios.JPAObjectDBPromocion;
import ar.unrn.tp.jpa.servicios.JPAObjectDBVenta;
import ar.unrn.tp.modelo.Categoria;
import ar.unrn.tp.modelo.Cliente;
import ar.unrn.tp.modelo.FechaHora;
import ar.unrn.tp.modelo.Producto;
import ar.unrn.tp.modelo.PromocionTarjeta;
import ar.unrn.tp.modelo.TarjetaCredito;
import ar.unrn.tp.modelo.Venta;
import ar.unrn.tp.web.WebAPI;

public class Main {
	
	public static void main(String[] args) throws ParseException {
		
		try {
			
			String objectdb = "jpa-objectdb", jdbc = "jpa-jdbc";
			
			ClienteService clientes = new JPAObjectDBCliente(jdbc);
			
			ProductoService productos = new JPAObjectDBProducto(jdbc);
//			
			PromocionService promos = new JPAObjectDBPromocion(jdbc);
//			
//			LocalDate fechaDesde = LocalDate.now(), fechaHasta = LocalDate.of(2021, 10, 28);
//			
			VentaService ventas = new JPAObjectDBVenta(jdbc);
			
			WebAPI api = new WebAPI(clientes, productos, ventas, promos, 7000);
			
			api.start();
			
//			List<Venta> listaVentas= new ArrayList();
//			listaVentas = ventas.ventas();
//			
//			for (Venta v: listaVentas) {
//				
//			}
			
//			promos.crearDescuento("Logitech", fechaDesde, fechaHasta, 0.05);
			
//			promos.crearDescuentoSobreTotal("MemeCard", fechaDesde, fechaHasta, 0.08);

//			productos.crearProducto("1", "Auriculares sin micrófono extendibles USB", 1200, "Logitech", 1L);
//			productos.crearProducto("2", "Jeans azules", 1700, "Levi", 2L);
//			productos.crearProducto("3", "Escritorio con cajones madera", 8900, "IKEA", 3L);
//			productos.crearProducto("4", "Pinza ", 800, "Black&Decker", 4L);
//			productos.crearProducto("5", "Pelota inflable tipo pulpo", 650, "Pulpo", 5L);
//			productos.crearProducto("6", "Mouse gamer", 1500, "Logitech", 1L);
			
//			List<Producto> listaProductos = new ArrayList<Producto>();
////			
//			listaProductos = productos.listarProductos();
//			
//			for (Producto producto : listaProductos) {
//				System.out.println(producto.descripcion());
//				System.out.println(producto.categoria());
//				System.out.println();
//			}
//			
//			listaProductos = listaProductos.stream().filter((prod)->prod.codigo()==6).collect(Collectors.toList());
			
//			ventas.realizarVenta(7L, listaProductos, 8L);
//			
//			List<TarjetaCredito> tarjetas = new ArrayList<TarjetaCredito>();
//			tarjetas = clientes.listarTarjetas(7L);
//			
//			EntityManagerFactory emf = Persistence
//					.createEntityManagerFactory(jdbc);
//			EntityManager em = emf.createEntityManager();
//			EntityTransaction tx = em.getTransaction();
//			
//			tx.begin();
//			
//			PromocionTarjeta promoTarjeta = em.find(PromocionTarjeta.class, 14L);
//			
//			for (TarjetaCredito tarjetaCredito : tarjetas) {
//				System.out.println(promoTarjeta.mismaEmpresa(tarjetaCredito.empresa()));
//			}
////			
////			Categoria cat1 = new Categoria("Indumentaria");
////			Categoria cat2 = new Categoria("Muebles");
////			Categoria cat3 = new Categoria("Herramientas");
////			Categoria cat4 = new Categoria("Deporte");
////			Categoria cat5 = new Categoria("Electrónica");
////			
////			em.persist(cat1);
////			em.persist(cat2);
////			em.persist(cat3);
////			em.persist(cat4);
////			em.persist(cat5);
////			
//			tx.commit();
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
