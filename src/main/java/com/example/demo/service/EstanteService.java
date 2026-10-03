
package com.example.demo.service;

import com.example.demo.modelo.Estante;
import com.example.demo.persistencia.EstanteRepository;
import com.example.demo.persistencia.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstanteService {

    private final EstanteRepository estanteRepository;
    private final ProductoRepository productoRepository;

    public EstanteService(
            EstanteRepository estanteRepository,
            ProductoRepository productoRepository) {

        this.estanteRepository = estanteRepository;
        this.productoRepository = productoRepository;
    }

    // Listar todos los estantes
    public List<Estante> listarEstantes() {
        return estanteRepository.findAll();
    }

    // Guardar o actualizar estante
    public Estante guardarEstante(Estante estante) {
        return estanteRepository.save(estante);
    }

    // Buscar estante por ID
    public Estante buscarPorId(Long id) {
        return estanteRepository
                .findById(id)
                .orElse(new Estante());
    }

    // Eliminar estante
    public boolean eliminarEstante(Long id) {

        // Verificar si existen productos asignados al estante
        if (productoRepository.existsByEstanteId(id)) {
            return false;
        }

        estanteRepository.deleteById(id);
        return true;
    }
}