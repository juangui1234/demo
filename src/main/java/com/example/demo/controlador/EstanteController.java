package com.example.demo.controlador;

import com.example.demo.modelo.Estante;
import com.example.demo.service.EstanteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/estantes")
public class EstanteController {

    private final EstanteService estanteService;

    public EstanteController(EstanteService estanteService) {
        this.estanteService = estanteService;
    }

    // Listar estantes
    @GetMapping
    public String listar(Model model) {

        model.addAttribute(
                "estantes",
                estanteService.listarEstantes()
        );

        model.addAttribute(
                "estante",
                new Estante()
        );

        return "estantes";
    }

    // Crear o actualizar estante
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Estante estante) {

        estanteService.guardarEstante(estante);

        return "redirect:/estantes";
    }

    // Cargar estante para editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Estante estante = estanteService.buscarPorId(id);

        model.addAttribute(
                "estantes",
                estanteService.listarEstantes()
        );

        model.addAttribute(
                "estante",
                estante
        );

        return "estantes";
    }

    // Eliminar estante
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {

        boolean eliminado = estanteService.eliminarEstante(id);

        if (!eliminado) {
            return "redirect:/estantes?error=productos";
        }

        return "redirect:/estantes?eliminado=true";
    }

}