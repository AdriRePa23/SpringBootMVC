package com.areapal.springmvc.controlador;

import com.areapal.springmvc.modelo.Producto;
import com.areapal.springmvc.repositorio.ProductoRepositorio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoControlador {
    private ProductoRepositorio repositorio;
    public ProductoControlador(ProductoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    /*
    GET http://localhost:8080/productos
    */
    @GetMapping
    public String listarTodos(Model model) {
        List<Producto> productos = this.repositorio.findAll();
        model.addAttribute("productos", productos);
        return "lista-productos";
    }

    /*
    GET http://localhost:8080/productos/nuevo
    */
    @GetMapping("/nuevo")
    public String getFormulario(Model model) {
        model.addAttribute("productos", new Producto());
        return "formulario-producto";
    }

    /*
    GET http://localhost:8080/productos
    */
    @PostMapping
    public String crearProducto(@ModelAttribute("producto") Producto producto) {
        this.repositorio.save(producto);
        return "redirect:/productos";
    }

    /*
    GET http://localhost:8080/productos/{id}/detalles
    */
    @GetMapping("/{id}/detalles")
    public String verProducto(@PathVariable("id") Long id, Model model) {
        var productoOptional = repositorio.findById(id);

        if (productoOptional.isPresent()) {
            model.addAttribute("producto", productoOptional.get());
            return "detalle-producto";  // Vista para los detalles del producto
        } else {
            model.addAttribute("mensaje", "Producto no encontrado");
            return "error";  // Vista de error si no se encuentra el producto
        }
    }



}
