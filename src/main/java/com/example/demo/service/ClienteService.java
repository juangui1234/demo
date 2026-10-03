package com.example.demo.service;

import com.example.demo.modelo.Cliente;
import com.example.demo.persistencia.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Listar todos los clientes
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    // Guardar o actualizar cliente
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Buscar cliente por ID
    public Cliente buscarPorId(Long id) {
        return clienteRepository
                .findById(id)
                .orElse(new Cliente());
    }

    // Eliminar cliente
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}