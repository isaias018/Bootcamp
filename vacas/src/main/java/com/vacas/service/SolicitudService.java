package com.vacas.service;

import com.vacas.entity.Solicitud;
import com.vacas.entity.Usuario;
import com.vacas.repository.SolicitudRepository;
import com.vacas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Crear solicitud
    public Solicitud crearSolicitud(Solicitud solicitud) {
        // Validar usuario
        Usuario usuario = usuarioRepository.findById(solicitud.getUsuario().getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Validar fechas
        validarFechas(solicitud);

        // Calcular días
        long cantidadDias = ChronoUnit.DAYS.between(solicitud.getFechaInicio(), solicitud.getFechaFin()) + 1;
        solicitud.setCantidadDias((int) cantidadDias);

        // Validar días disponibles
        if (usuario.getDiasVacacionesRestante() != null &&
                usuario.getDiasVacacionesRestante() < cantidadDias) {
            throw new RuntimeException("No tienes suficientes días de vacaciones disponibles. " +
                    "Disponibles: " + usuario.getDiasVacacionesRestante() + ", Solicitados: " + cantidadDias);
        }

        // Validar solapamiento
        validarSolapamiento(solicitud);

        // Establecer valores por defecto
        solicitud.setEstado(true);
        solicitud.setRechazado(false);
        solicitud.setNumeroAprobaciones(0);
        solicitud.setUsuario(usuario);

        return solicitudRepository.save(solicitud);
    }

    // Obtener todas las solicitudes
    @Transactional(readOnly = true)
    public List<Solicitud> obtenerTodasLasSolicitudes() {
        return solicitudRepository.findAll();
    }

    // Obtener solicitud por ID
    @Transactional(readOnly = true)
    public Optional<Solicitud> obtenerSolicitudPorId(Long id) {
        return solicitudRepository.findById(id);
    }

    // Obtener solicitudes por usuario
    @Transactional(readOnly = true)
    public List<Solicitud> obtenerSolicitudesPorUsuario(Long idUsuario) {
        return solicitudRepository.findByUsuarioIdUsuario(idUsuario);
    }

    // Obtener solicitudes pendientes
    @Transactional(readOnly = true)
    public List<Solicitud> obtenerSolicitudesPendientes() {
        return solicitudRepository.findSolicitudesPendientes();
    }

    // Obtener solicitudes aprobadas
    @Transactional(readOnly = true)
    public List<Solicitud> obtenerSolicitudesAprobadas() {
        return solicitudRepository.findSolicitudesAprobadas();
    }

    // Obtener solicitudes rechazadas
    @Transactional(readOnly = true)
    public List<Solicitud> obtenerSolicitudesRechazadas() {
        return solicitudRepository.findByRechazadoTrue();
    }

    // Aprobar solicitud
    public Solicitud aprobarSolicitud(Long id, String comentarioAprobacion) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        if (solicitud.getRechazado()) {
            throw new RuntimeException("No se puede aprobar una solicitud rechazada");
        }

        // Incrementar aprobaciones
        int aprobaciones = solicitud.getNumeroAprobaciones() != null ? solicitud.getNumeroAprobaciones() : 0;
        solicitud.setNumeroAprobaciones(aprobaciones + 1);

        // Agregar comentario si se proporciona
        if (comentarioAprobacion != null && !comentarioAprobacion.trim().isEmpty()) {
            String comentarioActual = solicitud.getComentario() != null ? solicitud.getComentario() : "";
            solicitud.setComentario(comentarioActual + "\nAprobación: " + comentarioAprobacion);
        }

        // Si se alcanza el número de aprobaciones necesarias, descontar días de vacaciones
        if (solicitud.getNumeroAprobaciones() >= 1) { // Ajustar según reglas de negocio
            Usuario usuario = solicitud.getUsuario();
            if (usuario.getDiasVacacionesRestante() != null) {
                int diasRestantes = usuario.getDiasVacacionesRestante() - solicitud.getCantidadDias();
                usuario.setDiasVacacionesRestante(Math.max(0, diasRestantes));
                usuarioRepository.save(usuario);
            }
        }

        return solicitudRepository.save(solicitud);
    }

    // Rechazar solicitud
    public Solicitud rechazarSolicitud(Long id, String motivoRechazo) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        solicitud.setRechazado(true);

        // Agregar motivo de rechazo
        if (motivoRechazo != null && !motivoRechazo.trim().isEmpty()) {
            String comentarioActual = solicitud.getComentario() != null ? solicitud.getComentario() : "";
            solicitud.setComentario(comentarioActual + "\nRechazada: " + motivoRechazo);
        }

        return solicitudRepository.save(solicitud);
    }

    // Actualizar solicitud
    public Solicitud actualizarSolicitud(Long id, Solicitud solicitudActualizada) {
        Solicitud solicitudExistente = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        if (solicitudExistente.getRechazado() ||
                (solicitudExistente.getNumeroAprobaciones() != null && solicitudExistente.getNumeroAprobaciones() > 0)) {
            throw new RuntimeException("No se puede actualizar una solicitud que ya fue aprobada o rechazada");
        }

        // Validar fechas
        solicitudActualizada.setIdSolicitud(id);
        validarFechas(solicitudActualizada);

        // Calcular días
        long cantidadDias = ChronoUnit.DAYS.between(
                solicitudActualizada.getFechaInicio(),
                solicitudActualizada.getFechaFin()) + 1;

        // Actualizar campos
        solicitudExistente.setFechaInicio(solicitudActualizada.getFechaInicio());
        solicitudExistente.setFechaFin(solicitudActualizada.getFechaFin());
        solicitudExistente.setCantidadDias((int) cantidadDias);
        solicitudExistente.setComentario(solicitudActualizada.getComentario());

        // Validar solapamiento
        validarSolapamiento(solicitudExistente);

        return solicitudRepository.save(solicitudExistente);
    }

    // Cancelar solicitud
    public void cancelarSolicitud(Long id) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        if (solicitud.getRechazado()) {
            throw new RuntimeException("La solicitud ya está rechazada");
        }

        // Si ya fue aprobada, devolver días de vacaciones
        if (solicitud.getNumeroAprobaciones() != null && solicitud.getNumeroAprobaciones() > 0) {
            Usuario usuario = solicitud.getUsuario();
            if (usuario.getDiasVacacionesRestante() != null) {
                int diasRestantes = usuario.getDiasVacacionesRestante() + solicitud.getCantidadDias();
                usuario.setDiasVacacionesRestante(diasRestantes);
                usuarioRepository.save(usuario);
            }
        }

        solicitudRepository.delete(solicitud);
    }

    // Obtener solicitudes por rango de fechas
    @Transactional(readOnly = true)
    public List<Solicitud> obtenerSolicitudesPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return solicitudRepository.findByRangoFechas(fechaInicio, fechaFin);
    }

    // Validaciones privadas
    private void validarFechas(Solicitud solicitud) {
        if (solicitud.getFechaInicio() == null || solicitud.getFechaFin() == null) {
            throw new RuntimeException("Las fechas de inicio y fin son obligatorias");
        }

        if (solicitud.getFechaInicio().isAfter(solicitud.getFechaFin())) {
            throw new RuntimeException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }

        if (solicitud.getFechaInicio().isBefore(LocalDate.now())) {
            throw new RuntimeException("La fecha de inicio no puede ser en el pasado");
        }

        // Validar que no sea más de un año en el futuro
        if (solicitud.getFechaInicio().isAfter(LocalDate.now().plusYears(1))) {
            throw new RuntimeException("No se pueden solicitar vacaciones con más de un año de anticipación");
        }
    }

    private void validarSolapamiento(Solicitud solicitud) {
        List<Solicitud> solicitudesSolapadas = solicitudRepository.findSolicitudesSolapadas(
                solicitud.getUsuario().getIdUsuario(),
                solicitud.getFechaInicio(),
                solicitud.getFechaFin()
        );

        // Filtrar la misma solicitud si es una actualización
        if (solicitud.getIdSolicitud() != null) {
            solicitudesSolapadas.removeIf(s -> s.getIdSolicitud().equals(solicitud.getIdSolicitud()));
        }

        // Solo considerar solicitudes activas (no rechazadas)
        solicitudesSolapadas.removeIf(s -> s.getRechazado());

        if (!solicitudesSolapadas.isEmpty()) {
            throw new RuntimeException("Ya tienes una solicitud de vacaciones en ese período de fechas");
        }
    }
}