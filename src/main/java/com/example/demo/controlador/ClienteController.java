package com.example.demo.controlador;

import com.example.demo.modelo.Cliente;
import com.example.demo.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Listar clientes
    @GetMapping
    public String listar(Model model) {

        model.addAttribute(
                "clientes",
                clienteService.listarClientes()
        );

        model.addAttribute(
                "cliente",
                new Cliente()
        );

        return "clientes";
    }

    // Crear o actualizar cliente
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cliente cliente) {

        clienteService.guardarCliente(cliente);

        return "redirect:/clientes";
    }

    // Cargar cliente para editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Cliente cliente = clienteService.buscarPorId(id);

        model.addAttribute(
                "clientes",
                clienteService.listarClientes()
        );

        model.addAttribute(
                "cliente",
                cliente
        );

        return "clientes";
    }

    // Eliminar cliente
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {

        clienteService.eliminarCliente(id);

        return "redirect:/clientes";
    }
}