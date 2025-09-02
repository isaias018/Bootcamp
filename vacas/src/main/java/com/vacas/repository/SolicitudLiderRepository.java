package com.vacas.repository;

import com.vacas.entity.SolicitudLider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudLiderRepository extends JpaRepository<SolicitudLider, Long> {

    // Buscar por solicitud
    List<SolicitudLider> findBySolicitudIdSolicitud(Long idSolicitud);

    // Buscar por líder
    List<SolicitudLider> findByIdLider(Long idLider);

    // Verificar si un líder está asignado a una solicitud
    boolean existsBySolicitudIdSolicitudAndIdLider(Long idSolicitud, Long idLider);
}
