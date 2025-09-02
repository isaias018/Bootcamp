package com.vacas.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "equipos")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipo")
    private Long idEquipo;

    @NotBlank(message = "El nombre del equipo es obligatorio")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    // Relación con usuarios - IGNORAR en JSON para evitar ciclos
    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"rol", "cargo", "equipo", "solicitudes"})
    private List<Usuario> usuarios;

    // Constructores
    public Equipo() {}

    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public Long getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Long idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "idEquipo=" + idEquipo +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}