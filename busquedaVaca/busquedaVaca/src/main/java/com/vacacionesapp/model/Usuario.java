package com.vacacionesapp.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nombre;
    private String apellido;
    private Integer nroCedula;
    private String correo;

    private LocalDate fechaIngreso;
    private LocalDate fechaNacimiento;

    private Integer diasVacaciones;
    private Integer diasVacacionesRestante;
    private Boolean estado;
    private Boolean requiereCambioContrasena;

    private String contrasena;
    private String telefono;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "id_cargo")
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;

    // Getters y Setters
    // ...
}

