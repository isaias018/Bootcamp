package com.vacas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "documentos_permisos")
public class DocumentoPermiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento")
    private Long idDocumento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud", nullable = false)
    private Solicitud solicitud;

    @NotBlank(message = "La URL del documento es obligatoria")
    @Column(name = "url_documento", nullable = false)
    private String urlDocumento;

    @Column(name = "fecha_subida")
    private LocalDateTime fechaSubida;

    // Constructores
    public DocumentoPermiso() {}

    public DocumentoPermiso(Solicitud solicitud, String urlDocumento) {
        this.solicitud = solicitud;
        this.urlDocumento = urlDocumento;
        this.fechaSubida = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        fechaSubida = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public String getUrlDocumento() {
        return urlDocumento;
    }

    public void setUrlDocumento(String urlDocumento) {
        this.urlDocumento = urlDocumento;
    }

    public LocalDateTime getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(LocalDateTime fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    @Override
    public String toString() {
        return "DocumentoPermiso{" +
                "idDocumento=" + idDocumento +
                ", urlDocumento='" + urlDocumento + '\'' +
                ", fechaSubida=" + fechaSubida +
                '}';
    }
}