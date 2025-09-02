package com.vacas.repository;

import com.vacas.entity.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

// Repository para Solicitud
@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

    // Buscar por usuario
    List<Solicitud> findByUsuarioIdUsuario(Long idUsuario);

    // Buscar solicitudes activas
    List<Solicitud> findByEstadoTrue();

    // Buscar solicitudes rechazadas
    List<Solicitud> findByRechazadoTrue();

    // Buscar solicitudes aprobadas (no rechazadas y con aprobaciones)
    @Query("SELECT s FROM Solicitud s WHERE s.rechazado = false AND s.numeroAprobaciones > 0")
    List<Solicitud> findSolicitudesAprobadas();

    // Buscar solicitudes pendientes
    @Query("SELECT s FROM Solicitud s WHERE s.rechazado = false AND (s.numeroAprobaciones = 0 OR s.numeroAprobaciones IS NULL)")
    List<Solicitud> findSolicitudesPendientes();

    // Buscar solicitudes por rango de fechas
    @Query("SELECT s FROM Solicitud s WHERE s.fechaInicio >= :fechaInicio AND s.fechaFin <= :fechaFin")
    List<Solicitud> findByRangoFechas(@Param("fechaInicio") LocalDate fechaInicio,
                                      @Param("fechaFin") LocalDate fechaFin);

    // Buscar solicitudes de un usuario en un rango de fechas
    @Query("SELECT s FROM Solicitud s WHERE s.usuario.idUsuario = :idUsuario AND " +
            "s.fechaInicio >= :fechaInicio AND s.fechaFin <= :fechaFin")
    List<Solicitud> findByUsuarioYRangoFechas(@Param("idUsuario") Long idUsuario,
                                              @Param("fechaInicio") LocalDate fechaInicio,
                                              @Param("fechaFin") LocalDate fechaFin);

    // Contar solicitudes por estado
    long countByEstadoTrue();

    long countByRechazadoTrue();

    // Solicitudes que se solapan con un rango de fechas
    @Query("SELECT s FROM Solicitud s WHERE s.usuario.idUsuario = :idUsuario AND " +
            "((s.fechaInicio <= :fechaFin AND s.fechaFin >= :fechaInicio) OR " +
            "(s.fechaInicio >= :fechaInicio AND s.fechaInicio <= :fechaFin))")
    List<Solicitud> findSolicitudesSolapadas(@Param("idUsuario") Long idUsuario,
                                             @Param("fechaInicio") LocalDate fechaInicio,
                                             @Param("fechaFin") LocalDate fechaFin);
}
