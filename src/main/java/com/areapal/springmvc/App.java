package com.areapal.springmvc;

import com.areapal.springmvc.controlador.ProductoControlador;
import com.areapal.springmvc.modelo.Producto;
import com.areapal.springmvc.repositorio.ProductoRepositorio;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		ApplicationContext contexto = SpringApplication.run(App.class, args);
		var repositorio = contexto.getBean(ProductoRepositorio.class);

		List<Producto> productos = List.of(
				new Producto(null, "Producto 1", "Este es el producto 1", 5.9, 4),
				new Producto(null, "Producto 2", "Este es el producto 2", 6.99, 5),
				new Producto(null, "Producto 3", "Este es el producto 3", 2.50, 2),
				new Producto(null, "Producto 4", "Este es el producto 4", 6.00, 3),
				new Producto(null, "Producto 5", "Este es el producto 5", 10.00, 8),
				new Producto(null, "Producto 6", "Este es el producto 6", 4.25, 7)
		);
		repositorio.saveAll(productos);
	}
}
