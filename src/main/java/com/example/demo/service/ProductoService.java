package com.example.demo.service;

import com.example.demo.modelo.Estante;
import com.example.demo.modelo.Producto;
import com.example.demo.persistencia.EstanteRepository;
import com.example.demo.persistencia.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final EstanteRepository estanteRepository;

    public ProductoService(
            ProductoRepository productoRepository,
            EstanteRepository estanteRepository) {

        this.productoRepository = productoRepository;
        this.estanteRepository = estanteRepository;
    }

    // Listar todos los productos
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    // Guardar o actualizar producto
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // Buscar producto por ID
    public Producto buscarPorId(Long id) {
        return productoRepository
                .findById(id)
                .orElse(new Producto());
    }

    // Eliminar producto
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    // Listar estantes disponibles
    public Object listarEstantes() {
        return estanteRepository.findAll();
    }
}
