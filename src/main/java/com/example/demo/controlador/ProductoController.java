package com.example.demo.controlador;

import com.example.demo.modelo.Producto;
import com.example.demo.persistencia.EstanteRepository;
import com.example.demo.persistencia.ProductoRepository;
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

    private final ProductoRepository productoRepository;
    private final EstanteRepository estanteRepository;

    public ProductoController(
            ProductoRepository productoRepository,
            EstanteRepository estanteRepository) {

        this.productoRepository = productoRepository;
        this.estanteRepository = estanteRepository;
    }

    // Listar productos
    @GetMapping
    public String listar(Model model) {

        model.addAttribute("productos", productoRepository.findAll());
        model.addAttribute("producto", new Producto());
        model.addAttribute("estantes", estanteRepository.findAll());

        return "index";
    }

    // Crear o actualizar producto
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto) {

        productoRepository.save(producto);

        return "redirect:/productos";
    }

    // Cargar producto para editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Producto producto = productoRepository
                .findById(id)
                .orElse(new Producto());

        model.addAttribute("productos", productoRepository.findAll());
        model.addAttribute("producto", producto);
        model.addAttribute("estantes", estanteRepository.findAll());

        return "index";
    }

    // Eliminar producto
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {

        productoRepository.deleteById(id);

        return "redirect:/productos";
    }
}