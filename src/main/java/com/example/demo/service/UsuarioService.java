package com.example.demo.service;

import com.example.demo.modelo.Usuario;
import com.example.demo.persistencia.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public boolean validarLogin(String username, String password) {

        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario == null) {
            return false;
        }

        return usuario.getPassword().equals(password);
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}