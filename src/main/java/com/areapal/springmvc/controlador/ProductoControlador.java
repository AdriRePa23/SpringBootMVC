package com.areapal.springmvc.controlador;

import com.areapal.springmvc.modelo.Producto;
import com.areapal.springmvc.repositorio.ProductoRepositorio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productos") // Aquí tienes "/productos" como base
public class ProductoControlador {
    private ProductoRepositorio repositorio;

    public ProductoControlador(ProductoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping
    public String listarTodos(Model model) {
        List<Producto> productos = this.repositorio.findAll();
        model.addAttribute("productos", productos);
        return "lista-productos";
    }

    // Cambiar la ruta para que coincida con el enlace en la vista
    @GetMapping("/nuevo") // Asegúrate de que la ruta sea "/productos/nuevo"
    public String getFormulario(Model model) {
        model.addAttribute("producto", new Producto());
        return "formulario-producto";
    }

    @PostMapping
    public String crearProducto(@ModelAttribute("producto") Producto producto) {
        try {
            this.repositorio.save(producto);
            return "redirect:/productos"; // Redirige a la lista después de guardar
        } catch (Exception e) {
            e.printStackTrace();
            return "error";  // Puedes redirigir a una página de error o mostrar un mensaje de error
        }
    }

    @GetMapping("/{id}/detalles")
    public String verProducto(@PathVariable("id") Long id, Model model) {
        var productoOptional = repositorio.findById(id);

        if (productoOptional.isPresent()) {
            model.addAttribute("producto", productoOptional.get());
            return "detalle-producto";
        } else {
            model.addAttribute("mensaje", "Producto no encontrado");
            return "error";
        }
    }
}
