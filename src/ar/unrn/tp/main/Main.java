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
import ar.unrn.tp.modelo.TarjetaCredito;

public class Main {
	
	public static void main(String[] args) throws ParseException {
		
		try {
//			ClienteService clientes = new JPAObjectDBCliente();
			
			ProductoService productos = new JPAObjectDBProducto();
			
			PromocionService promos = new JPAObjectDBPromocion();
			
			LocalDate fechaDesde = LocalDate.now(), fechaHasta = LocalDate.of(2021, 10, 28);
			
			VentaService ventas = new JPAObjectDBVenta();
			
			List<Producto> listaProductos = new ArrayList<Producto>();
			
			listaProductos = productos.listarProductos();
			
			listaProductos = listaProductos.stream().filter((prod)->prod.codigo()==11).collect(Collectors.toList());
			
			ventas.realizarVenta(26L, listaProductos, 27L);
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
