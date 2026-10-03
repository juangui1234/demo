package com.example.demo.persistencia;


import com.example.demo.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.persistencia.ProductoRepository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    boolean existsByEstanteId(Long estanteId);
}