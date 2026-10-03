package com.example.demo.controlador;

import com.example.demo.service.ClienteExternoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClienteExternoController {

    private final ClienteExternoService clienteExternoService;

    public ClienteExternoController(ClienteExternoService clienteExternoService) {
        this.clienteExternoService = clienteExternoService;
    }

    // Mostrar clientes obtenidos desde JSONPlaceholder
    @GetMapping("/clientes-externos")
    public String listarClientesExternos(Model model) {

        model.addAttribute(
                "clientesExternos",
                clienteExternoService.listarClientesExternos()
        );

        return "clientes-api";
    }
}