package ar.unrn.tp.web;

import java.util.ArrayList;
import java.util.Map;

import com.objectdb.o.IXM.a;

import ar.unrn.tp.api.ClienteService;
import ar.unrn.tp.api.ProductoService;
import ar.unrn.tp.api.PromocionService;
import ar.unrn.tp.api.VentaService;
import ar.unrn.tp.excepciones.IllegalNumberException;
import ar.unrn.tp.modelo.Producto;
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
		app.get("/", traerProductos());
		
//		Excepciones del modelo
//		app.exception(exceptionClass, exceptionHandler)
		
		app.exception(Exception.class, (e, ctx) -> {
			ctx.json(Map.of("result", "error", "message", e.getMessage()));
		});
	}
	
	private Handler traerProductos() {
		return ctx -> {
			var productos = this.productos.listarProductos();
			var list = new ArrayList<Map<String, Object>>();
			for(Producto p : productos) {
				list.add(p.toMap());
			}
		};
	}
	
}
