package com.vacas.repository;

import com.vacas.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Repository para Cargo
@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
    Optional<Cargo> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}
