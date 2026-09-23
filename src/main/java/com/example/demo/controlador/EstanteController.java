package com.example.demo.controlador;

import com.example.demo.modelo.Estante;
import com.example.demo.persistencia.EstanteRepository;
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

    private final EstanteRepository estanteRepository;

    public EstanteController(EstanteRepository estanteRepository) {
        this.estanteRepository = estanteRepository;
    }

    // Listar estantes
    @GetMapping
    public String listar(Model model) {

        model.addAttribute("estantes", estanteRepository.findAll());
        model.addAttribute("estante", new Estante());

        return "estantes";
    }

    // Crear o actualizar estante
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Estante estante) {

        estanteRepository.save(estante);

        return "redirect:/estantes";
    }

    // Cargar estante para editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Estante estante = estanteRepository
                .findById(id)
                .orElse(new Estante());

        model.addAttribute("estantes", estanteRepository.findAll());
        model.addAttribute("estante", estante);

        return "estantes";
    }

    // Eliminar estante
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {

        estanteRepository.deleteById(id);

        return "redirect:/estantes";
    }
}