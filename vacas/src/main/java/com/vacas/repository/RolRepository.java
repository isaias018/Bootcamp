package com.vacas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vacas.entity.Rol; // ✅ CORRECTO - Importar la entidad

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    // Métodos personalizados si los necesitas
    boolean existsByNombre(String nombre);
}