package com.areapal.springmvc.controlador;

import com.areapal.springmvc.modelo.Producto;
import com.areapal.springmvc.repositorio.ProductoRepositorio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productos") // Define la ruta base para todas las rutas de este controlador
public class ProductoControlador {
    private ProductoRepositorio repositorio;

    // Constructor para inyectar el repositorio de productos
    public ProductoControlador(ProductoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    // Maneja las solicitudes GET para listar todos los productos
    @GetMapping
    public String listarTodos(Model model) {
        List<Producto> productos = this.repositorio.findAll(); // Obtiene todos los productos del repositorio
        model.addAttribute("productos", productos); // Agrega los productos al modelo para pasarlos a la vista
        return "lista-productos"; // Devuelve la vista "lista-productos"
    }

    // Maneja las solicitudes GET para mostrar el formulario de creación de un producto
    @GetMapping("/nuevo")
    public String getFormularioCrear(Model model) {
        model.addAttribute("producto", new Producto()); // Agrega un nuevo objeto Producto vacío al modelo
        return "formulario-crear-producto"; // Devuelve la vista "formulario-crear-producto"
    }

    // Maneja las solicitudes POST para crear un nuevo producto
    @PostMapping
    public String crearProducto(@ModelAttribute("producto") Producto producto) {
        try {
            this.repositorio.save(producto); // Guarda el producto en el repositorio
            return "redirect:/productos"; // Redirige a la lista de productos después de guardar
        } catch (Exception e) {
            e.printStackTrace(); // Imprime el error en la consola
            return "error"; // Devuelve la vista de error
        }
    }

    // Maneja las solicitudes GET para mostrar los detalles de un producto específico
    @GetMapping("/{id}/detalles")
    public String verProducto(@PathVariable("id") Long id, Model model) {
        var productoOptional = repositorio.findById(id); // Busca el producto por su ID

        if (productoOptional.isPresent()) { // Si el producto existe
            model.addAttribute("producto", productoOptional.get()); // Agrega el producto al modelo
            return "detalle-producto"; // Devuelve la vista "detalle-producto"
        } else {
            model.addAttribute("mensaje", "Producto no encontrado"); // Agrega un mensaje de error al modelo
            return "error"; // Devuelve la vista de error
        }
    }

    // Maneja las solicitudes GET para mostrar el formulario de edición de un producto
    @GetMapping("/{id}/editar")
    public String getFormularioEditar(@PathVariable("id") Long id, Model model) {
        var productoOptional = repositorio.findById(id); // Busca el producto por su ID
        if (productoOptional.isPresent()) { // Si el producto existe
            model.addAttribute("producto", productoOptional.get()); // Agrega el producto al modelo
            return "formulario-editar-producto"; // Devuelve la vista "formulario-editar-producto"
        } else {
            model.addAttribute("mensaje", "Producto no encontrado"); // Agrega un mensaje de error al modelo
            return "error"; // Devuelve la vista de error
        }
    }

    // Maneja las solicitudes POST para actualizar un producto existente
    @PostMapping("/{id}/editar")
    public String actualizarProducto(@PathVariable("id") Long id, @ModelAttribute("producto") Producto producto) {
        var productoExistente = repositorio.findById(id); // Busca el producto por su ID
        if (productoExistente.isPresent()) { // Si el producto existe
            Producto p = productoExistente.get();
            p.setNombre(producto.getNombre()); // Actualiza el nombre del producto
            p.setDescripcion(producto.getDescripcion()); // Actualiza la descripción del producto
            p.setPrecio(producto.getPrecio()); // Actualiza el precio del producto
            p.setCantidad(producto.getCantidad()); // Actualiza la cantidad disponible
            repositorio.save(p); // Guarda los cambios en el repositorio
            return "redirect:/productos"; // Redirige a la lista de productos
        } else {
            return "error"; // Devuelve la vista de error si el producto no existe
        }
    }

    // Maneja las solicitudes GET para eliminar un producto por su ID
    @GetMapping("/{id}/eliminar")
    public String eliminarProducto(@PathVariable("id") Long id) {
        var productoOptional = repositorio.findById(id); // Busca el producto por su ID
        if (productoOptional.isPresent()) { // Si el producto existe
            repositorio.delete(productoOptional.get()); // Elimina el producto del repositorio
            return "redirect:/productos"; // Redirige a la lista de productos
        } else {
            return "error"; // Devuelve la vista de error si el producto no existe
        }
    }
}
