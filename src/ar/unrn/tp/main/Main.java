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
import ar.unrn.tp.modelo.Number;
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
			
			PromocionService promos = new JPAObjectDBPromocion(jdbc);
			
			VentaService ventas = new JPAObjectDBVenta(jdbc);
			
			WebAPI api = new WebAPI(clientes, productos, ventas, promos, 7000);
			
			api.start();
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
