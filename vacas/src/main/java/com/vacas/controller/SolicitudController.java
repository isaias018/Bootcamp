package com.vacas.controller;

import com.vacas.entity.Solicitud;
import com.vacas.entity.Usuario;
import com.vacas.service.SolicitudService;
import com.vacas.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/solicitudes")
@CrossOrigin(origins = "*")
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    @Autowired
    private UsuarioService usuarioService;

    // DTO para crear solicitud
    public static class CrearSolicitudDTO {
        private Long usuarioId;
        private LocalDate fechaInicio;
        private LocalDate fechaFin;
        private String comentario;
        private Integer cantidadDias;

        // Getters y Setters
        public Long getUsuarioId() { return usuarioId; }
        public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

        public LocalDate getFechaInicio() { return fechaInicio; }
        public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

        public LocalDate getFechaFin() { return fechaFin; }
        public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

        public String getComentario() { return comentario; }
        public void setComentario(String comentario) { this.comentario = comentario; }

        public Integer getCantidadDias() { return cantidadDias; }
        public void setCantidadDias(Integer cantidadDias) { this.cantidadDias = cantidadDias; }
    }

    // Crear solicitud
    @PostMapping
    public ResponseEntity<?> crearSolicitud(@Valid @RequestBody CrearSolicitudDTO solicitudDTO) {
        try {
            // Buscar usuario
            Optional<Usuario> usuarioOpt = usuarioService.obtenerUsuarioPorId(solicitudDTO.getUsuarioId());
            if (!usuarioOpt.isPresent()) {
                return new ResponseEntity<>("Usuario no encontrado", HttpStatus.BAD_REQUEST);
            }

            // Crear solicitud
            Solicitud solicitud = new Solicitud();
            solicitud.setUsuario(usuarioOpt.get());
            solicitud.setFechaInicio(solicitudDTO.getFechaInicio());
            solicitud.setFechaFin(solicitudDTO.getFechaFin());
            solicitud.setComentario(solicitudDTO.getComentario());

            Solicitud solicitudCreada = solicitudService.crearSolicitud(solicitud);
            return new ResponseEntity<>(solicitudCreada, HttpStatus.CREATED);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todas las solicitudes
    @GetMapping
    public ResponseEntity<List<Solicitud>> obtenerTodasLasSolicitudes() {
        try {
            List<Solicitud> solicitudes = solicitudService.obtenerTodasLasSolicitudes();
            return new ResponseEntity<>(solicitudes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener solicitud por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerSolicitudPorId(@PathVariable Long id) {
        try {
            Optional<Solicitud> solicitud = solicitudService.obtenerSolicitudPorId(id);
            if (solicitud.isPresent()) {
                return new ResponseEntity<>(solicitud.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Solicitud no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener solicitudes por usuario
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Solicitud>> obtenerSolicitudesPorUsuario(@PathVariable Long idUsuario) {
        try {
            List<Solicitud> solicitudes = solicitudService.obtenerSolicitudesPorUsuario(idUsuario);
            return new ResponseEntity<>(solicitudes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener solicitudes pendientes
    @GetMapping("/pendientes")
    public ResponseEntity<List<Solicitud>> obtenerSolicitudesPendientes() {
        try {
            List<Solicitud> solicitudes = solicitudService.obtenerSolicitudesPendientes();
            return new ResponseEntity<>(solicitudes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener solicitudes aprobadas
    @GetMapping("/aprobadas")
    public ResponseEntity<List<Solicitud>> obtenerSolicitudesAprobadas() {
        try {
            List<Solicitud> solicitudes = solicitudService.obtenerSolicitudesAprobadas();
            return new ResponseEntity<>(solicitudes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener solicitudes rechazadas
    @GetMapping("/rechazadas")
    public ResponseEntity<List<Solicitud>> obtenerSolicitudesRechazadas() {
        try {
            List<Solicitud> solicitudes = solicitudService.obtenerSolicitudesRechazadas();
            return new ResponseEntity<>(solicitudes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener solicitudes por rango de fechas
    @GetMapping("/fechas")
    public ResponseEntity<List<Solicitud>> obtenerSolicitudesPorRangoFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        try {
            List<Solicitud> solicitudes = solicitudService.obtenerSolicitudesPorRangoFechas(fechaInicio, fechaFin);
            return new ResponseEntity<>(solicitudes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Aprobar solicitud
    @PatchMapping("/{id}/aprobar")
    public ResponseEntity<?> aprobarSolicitud(@PathVariable Long id,
                                              @RequestParam(required = false) String comentario) {
        try {
            Solicitud solicitud = solicitudService.aprobarSolicitud(id, comentario);
            return new ResponseEntity<>(solicitud, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Rechazar solicitud
    @PatchMapping("/{id}/rechazar")
    public ResponseEntity<?> rechazarSolicitud(@PathVariable Long id,
                                               @RequestParam String motivo) {
        try {
            Solicitud solicitud = solicitudService.rechazarSolicitud(id, motivo);
            return new ResponseEntity<>(solicitud, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar solicitud
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarSolicitud(@PathVariable Long id,
                                                 @Valid @RequestBody CrearSolicitudDTO solicitudDTO) {
        try {
            // Crear solicitud con los nuevos datos
            Solicitud solicitudActualizada = new Solicitud();
            solicitudActualizada.setFechaInicio(solicitudDTO.getFechaInicio());
            solicitudActualizada.setFechaFin(solicitudDTO.getFechaFin());
            solicitudActualizada.setComentario(solicitudDTO.getComentario());

            Solicitud solicitud = solicitudService.actualizarSolicitud(id, solicitudActualizada);
            return new ResponseEntity<>(solicitud, HttpStatus.OK);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Cancelar solicitud
    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelarSolicitud(@PathVariable Long id) {
        try {
            solicitudService.cancelarSolicitud(id);
            return new ResponseEntity<>("Solicitud cancelada correctamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Estadísticas de solicitudes
    @GetMapping("/estadisticas")
    public ResponseEntity<?> obtenerEstadisticas() {
        try {
            // Crear un objeto con estadísticas básicas
            var estadisticas = new Object() {
                public final long totalSolicitudes = solicitudService.obtenerTodasLasSolicitudes().size();
                public final long solicitudesPendientes = solicitudService.obtenerSolicitudesPendientes().size();
                public final long solicitudesAprobadas = solicitudService.obtenerSolicitudesAprobadas().size();
                public final long solicitudesRechazadas = solicitudService.obtenerSolicitudesRechazadas().size();
            };

            return new ResponseEntity<>(estadisticas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}