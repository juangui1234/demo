package com.example.demo.controlador;

import com.example.demo.modelo.Cliente;
import com.example.demo.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteApiController {

    private final ClienteService clienteService;

    public ClienteApiController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Obtener todos los clientes
    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    // Obtener cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable Long id) {

        Cliente cliente = clienteService.buscarPorId(id);

        if (cliente.getId() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cliente);
    }

    // Crear cliente
    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteService.guardarCliente(cliente);
    }

    // Actualizar cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(
            @PathVariable Long id,
            @RequestBody Cliente cliente) {

        Cliente existente = clienteService.buscarPorId(id);

        if (existente.getId() == null) {
            return ResponseEntity.notFound().build();
        }

        cliente.setId(id);

        return ResponseEntity.ok(
                clienteService.guardarCliente(cliente)
        );
    }

    // Eliminar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {

        Cliente existente = clienteService.buscarPorId(id);

        if (existente.getId() == null) {
            return ResponseEntity.notFound().build();
        }

        clienteService.eliminarCliente(id);

        return ResponseEntity.noContent().build();
    }
}