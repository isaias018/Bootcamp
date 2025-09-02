package com.vacas.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "solicitudes")
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Long idSolicitud;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha fin es obligatoria")
    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "estado", nullable = false)
    private Boolean estado = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnoreProperties({"solicitudes", "contrasena"})
    private Usuario usuario;

    @Column(name = "cantidad_dias")
    private Integer cantidadDias;

    @Column(name = "numero_aprobaciones")
    private Integer numeroAprobaciones;

    @Column(name = "comentario", columnDefinition = "TEXT")
    private String comentario;

    @Column(name = "rechazado", nullable = false)
    private Boolean rechazado = false;

    // Relación con documentos - IGNORAR en JSON para evitar ciclos
    @OneToMany(mappedBy = "solicitud", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"solicitud"})
    private List<DocumentoPermiso> documentos;

    // Relación con líderes - IGNORAR en JSON para evitar ciclos
    @OneToMany(mappedBy = "solicitud", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"solicitud"})
    private List<SolicitudLider> solicitudLideres;

    // Constructores
    public Solicitud() {}

    public Solicitud(LocalDate fechaInicio, LocalDate fechaFin, Usuario usuario) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.usuario = usuario;
    }

    // Getters y Setters
    public Long getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getCantidadDias() {
        return cantidadDias;
    }

    public void setCantidadDias(Integer cantidadDias) {
        this.cantidadDias = cantidadDias;
    }

    public Integer getNumeroAprobaciones() {
        return numeroAprobaciones;
    }

    public void setNumeroAprobaciones(Integer numeroAprobaciones) {
        this.numeroAprobaciones = numeroAprobaciones;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Boolean getRechazado() {
        return rechazado;
    }

    public void setRechazado(Boolean rechazado) {
        this.rechazado = rechazado;
    }

    public List<DocumentoPermiso> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DocumentoPermiso> documentos) {
        this.documentos = documentos;
    }

    public List<SolicitudLider> getSolicitudLideres() {
        return solicitudLideres;
    }

    public void setSolicitudLideres(List<SolicitudLider> solicitudLideres) {
        this.solicitudLideres = solicitudLideres;
    }

    @Override
    public String toString() {
        return "Solicitud{" +
                "idSolicitud=" + idSolicitud +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", estado=" + estado +
                ", cantidadDias=" + cantidadDias +
                ", rechazado=" + rechazado +
                '}';
    }
}