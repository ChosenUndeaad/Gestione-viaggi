package com.example.viaggi.repository;

import com.example.viaggi.model.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {

    Optional<Dipendente> findByUsername(String username);

    boolean existsByUsername(String username);
}
