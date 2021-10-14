package ar.unrn.tp.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.api.PromocionService;
import ar.unrn.tp.api.VentaService;
import ar.unrn.tp.excepciones.DateOverlapException;
import ar.unrn.tp.excepciones.EmptyProductListException;
import ar.unrn.tp.excepciones.EmptyStringException;
import ar.unrn.tp.excepciones.IllegalNumberException;
import ar.unrn.tp.excepciones.InvalidEmailException;
import ar.unrn.tp.modelo.Producto;
import ar.unrn.tp.modelo.Promocion;
import ar.unrn.tp.modelo.TarjetaCredito;
import ar.unrn.tp.modelo.Venta;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class WebAPI {

	private ClienteService clientes = null;
	private ProductoService productos = null;
	private VentaService ventas = null;
	private PromocionService promos = null;
	private int puerto;
	
	public WebAPI(ClienteService clientes, ProductoService productos, VentaService ventas, PromocionService promos, int puerto) throws IllegalArgumentException, IllegalNumberException {
		if(clientes==null) {
			throw new IllegalArgumentException("Error al iniciar servicio de clientes");
		}
		if(productos==null) {
			throw new IllegalArgumentException("Error al iniciar servicio de productos");
		}
		if(ventas==null) {
			throw new IllegalArgumentException("Error al iniciar servicio de ventas");
		}
		if(promos==null) {
			throw new IllegalArgumentException("Error al iniciar servicio de promociones");
		}
		if(puerto<999) {
			throw new IllegalNumberException("El puerto debe tener 4 cifras");
		}
		
		this.clientes = clientes;
		this.productos = productos;
		this.ventas = ventas;
		this.promos = promos;
		this.puerto = puerto;
	}
	
	public void start() {
		Javalin app = Javalin.create(config -> {
			config.enableCorsForAllOrigins();
		}).start(this.puerto);
		app.get("/productos", traerProductos());
		app.get("/descuentos", traerDescuentos());
		app.get("/tarjetas", traerTarjetas());
		app.get("/ventas", traerVentas());
		app.post("/monto", calcularMonto());
		app.post("/compra", confirmarCompra());
		
//		Excepciones del modelo
//		app.exception(exceptionClass, exceptionHandler)

		app.exception(DateOverlapException.class, (e, ctx) -> {
			ctx.json(Map.of("result", "error", "message", e.getMessage()));
		});
		
		app.exception(EmptyProductListException.class, (e, ctx) -> {
			ctx.json(Map.of("result", "error", "message", e.getMessage()));
		});
		
		app.exception(EmptyStringException.class, (e, ctx) -> {
			ctx.json(Map.of("result", "error", "message", e.getMessage()));
		});
		
		app.exception(IllegalNumberException.class, (e, ctx) -> {
			ctx.json(Map.of("result", "error", "message", e.getMessage()));
		});
		
		app.exception(InvalidEmailException.class, (e, ctx) -> {
			ctx.json(Map.of("result", "error", "message", e.getMessage()));
		});
		
		app.exception(Exception.class, (e, ctx) -> {
			ctx.json(Map.of("result", "error", "message", "Algo salió mal..." + e.getMessage()));
		});
	}
	
	private Handler traerProductos() {
		return ctx -> {
			var productos = this.productos.listarProductos();
			var list = new ArrayList<Map<String, Object>>();
			for(Producto p : productos) {
				list.add(p.toMap());
			}
			ctx.json(Map.of("result", "success", "products", list));
		};
	}
	private Handler traerDescuentos() {
		return ctx -> {
			var vigentes = this.promos.vigentes();
			var list = new ArrayList<Map<String, Object>>();
			for(Promocion p : vigentes) {
				list.add(p.toMap());
			}
			ctx.json(Map.of("result", "success", "discounts", list));
		};
	}
	
	private Handler traerTarjetas() {
		return ctx -> {
			var tarjetas = this.clientes.listarTarjetas(7L);
			var list = new ArrayList<Map<String, Object>>();
			for(TarjetaCredito t : tarjetas) {
				list.add(t.toMap());
			}
			ctx.json(Map.of("result", "success", "creditCards", list));
		};
	}
	
	private Handler calcularMonto() {
		return ctx -> {
			VentaDTO dto = ctx.bodyAsClass(VentaDTO.class);
			
			List<Producto> productosCarrito = new ArrayList<Producto>();
			
			List<Long> idProductos = new ArrayList<Long>();
			
			for (String s : dto.getDetalle()) {
				Long idProducto = Long.parseLong(s);
				idProductos.add(idProducto);
			}
			
			for (Producto prod : this.productos.listarProductos()) {
				if(idProductos.contains(prod.codigo())) {
					productosCarrito.add(prod);
				}
			}
			
			Long idTarjeta = Long.parseLong(dto.getTarjeta());
			
			double monto = this.ventas.calcularMonto(productosCarrito, idTarjeta);
			
			ctx.json(Map.of("result", "success", "message", "El monto a abonar es: " , "monto", monto));
		};
	}
	
	private Handler confirmarCompra() {
		return ctx -> {
			VentaDTO dto = ctx.bodyAsClass(VentaDTO.class);
			
			List<Producto> productosCarrito = new ArrayList<Producto>();
			
			List<Long> idProductos = new ArrayList<Long>();
			
			for (String s : dto.getDetalle()) {
				Long idProducto = Long.parseLong(s);
				idProductos.add(idProducto);
			}
			
			for (Producto prod : this.productos.listarProductos()) {
				if(idProductos.contains(prod.codigo())) {
					productosCarrito.add(prod);
				}
			}
			
			Long idTarjeta = Long.parseLong(dto.getTarjeta());
			
			Long idCliente = Long.parseLong(dto.getCliente());
			
			this.ventas.realizarVenta(idCliente, productosCarrito, idTarjeta);
			
			ctx.json(Map.of("result", "success", "message", "Su compra se ha realizado de manera exitosa"));
		};
	}
	
	private Handler traerVentas() {
		return ctx -> {
			var ventas = this.ventas.ventas();
			var list = new ArrayList<Map<String, Object>>();
			for(Venta v: ventas) {
				list.add(v.toMap());
			}
			ctx.json(Map.of("result", "success", "sales", list));
		};
	}
	
}
