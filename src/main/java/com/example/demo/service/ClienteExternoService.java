package com.example.demo.service;

import com.example.demo.modelo.ClienteExterno;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

@Service
public class ClienteExternoService {

    private final RestClient restClient;

    public ClienteExternoService() {

        this.restClient = RestClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }

    // Obtener usuarios desde JSONPlaceholder
    public List<ClienteExterno> listarClientesExternos() {

        ClienteExterno[] clientes = restClient
                .get()
                .uri("/users")
                .retrieve()
                .body(ClienteExterno[].class);

        if (clientes == null) {
            return List.of();
        }

        return Arrays.asList(clientes);
    }
}