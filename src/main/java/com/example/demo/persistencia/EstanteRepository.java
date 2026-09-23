package com.example.demo.persistencia;

import com.example.demo.modelo.Estante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstanteRepository extends JpaRepository<Estante, Long> {
}