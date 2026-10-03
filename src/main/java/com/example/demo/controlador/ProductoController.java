package com.example.demo.controlador;

import com.example.demo.modelo.Producto;
import com.example.demo.service.ProductoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Listar productos
    @GetMapping
    public String listar(Model model) {

        model.addAttribute(
                "productos",
                productoService.listarProductos()
        );

        model.addAttribute(
                "producto",
                new Producto()
        );

        model.addAttribute(
                "estantes",
                productoService.listarEstantes()
        );

        return "index";
    }

    // Crear o actualizar producto
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto) {

        productoService.guardarProducto(producto);

        return "redirect:/productos";
    }

    // Cargar producto para editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Producto producto = productoService.buscarPorId(id);

        model.addAttribute(
                "productos",
                productoService.listarProductos()
        );

        model.addAttribute(
                "producto",
                producto
        );

        model.addAttribute(
                "estantes",
                productoService.listarEstantes()
        );

        return "index";
    }

    // Eliminar producto
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {

        productoService.eliminarProducto(id);

        return "redirect:/productos";
    }
}