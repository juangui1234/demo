package controlador;

import modelo.Producto;
import persistencia.ProductoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoRepository repository;

    public ProductoController(ProductoRepository repository) {
        this.repository = repository;
    }

    // Leer (Listar)
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", repository.findAll());
        model.addAttribute("producto", new Producto()); // Para el formulario de registro
        return "index";
    }

    // Crear / Actualizar
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto) {
        repository.save(producto);
        return "redirect:/productos";
    }

    // Cargar datos para editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("productos", repository.findAll());
        model.addAttribute("producto", repository.findById(id).orElse(new Producto()));
        return "index";
    }

    // Borrar
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/productos";
    }
}