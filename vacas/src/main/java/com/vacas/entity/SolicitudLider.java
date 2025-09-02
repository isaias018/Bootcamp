package com.vacas.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "solicitud_lideres")
public class SolicitudLider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // o el nombre real de tu PK
    private Long idSolicitudLider;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud") // ✅ Esta queda igual (FK a solicitud)
    private Solicitud solicitud;

    @Column(name = "id_lider")
    private Long idLider;

    // Constructores
    public SolicitudLider() {}

    public SolicitudLider(Solicitud solicitud, Long idLider) {
        this.solicitud = solicitud;
        this.idLider = idLider;
    }

    // Getters y Setters
    public Long getIdSolicitudLider() {
        return idSolicitudLider;
    }

    public void setIdSolicitudLider(Long idSolicitudLider) {
        this.idSolicitudLider = idSolicitudLider;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public Long getIdLider() {
        return idLider;
    }

    public void setIdLider(Long idLider) {
        this.idLider = idLider;
    }

    @Override
    public String toString() {
        return "SolicitudLider{" +
                "idSolicitudLider=" + idSolicitudLider +
                ", idLider=" + idLider +
                '}';
    }
}