package com.vacas.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "cargos")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cargo")
    private Long idCargo;

    @NotBlank(message = "El nombre del cargo es obligatorio")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    // Relación con usuarios - IGNORAR en JSON para evitar ciclos
    @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"rol", "cargo", "equipo", "solicitudes"})
    private List<Usuario> usuarios;

    // Constructores
    public Cargo() {}

    public Cargo(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public Long getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Long idCargo) {
        this.idCargo = idCargo;
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
        return "Cargo{" +
                "idCargo=" + idCargo +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}