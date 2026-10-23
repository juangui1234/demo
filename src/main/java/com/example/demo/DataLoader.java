package com.example.demo;

import com.example.demo.modelo.Usuario;
import com.example.demo.persistencia.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;

    public DataLoader(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) {

        Usuario admin =
                usuarioRepository.findByUsername("admin");

        if (admin == null) {

            Usuario usuario = new Usuario();

            usuario.setUsername("admin");
            usuario.setPassword("1234");
            usuario.setRol("ADMIN");

            usuarioRepository.save(usuario);

            System.out.println("✅ Usuario administrador creado");
        }
    }
}