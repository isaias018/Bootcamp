package com.vacas.repository;

import com.vacas.entity.DocumentoPermiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repository para DocumentoPermiso
@Repository
public interface DocumentoPermisoRepository extends JpaRepository<DocumentoPermiso, Long> {

    // Buscar por solicitud
    List<DocumentoPermiso> findBySolicitudIdSolicitud(Long idSolicitud);

    // Contar documentos por solicitud
    long countBySolicitudIdSolicitud(Long idSolicitud);
}
