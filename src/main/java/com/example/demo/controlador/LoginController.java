package com.example.demo.controlador;

import com.example.demo.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String iniciarSesion(
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        boolean valido =
                usuarioService.validarLogin(username, password);

        if (valido) {
            return "redirect:/productos";
        }

        model.addAttribute(
                "error",
                "Usuario o contraseña incorrectos"
        );

        return "login";
    }
}