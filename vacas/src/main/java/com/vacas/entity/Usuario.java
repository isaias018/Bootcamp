package com.vacas.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "nro_cedula")
    private Integer nroCedula;

    @Email(message = "El formato del correo es inválido")
    @NotBlank(message = "El correo es obligatorio")
    @Column(name = "correo", nullable = false, unique = true)
    private String correo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol")
    @JsonIgnoreProperties({"usuarios"})
    private Rol rol;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @Column(name = "antiguedad")
    private String antiguedad;

    @Column(name = "dias_vacaciones")
    private Integer diasVacaciones;

    @Column(name = "estado", nullable = false)
    private Boolean estado = true;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "telefono")
    private String telefono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipo")
    @JsonIgnoreProperties({"usuarios"})
    private Equipo equipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cargo")
    @JsonIgnoreProperties({"usuarios"})
    private Cargo cargo;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "dias_vacaciones_restante")
    private Integer diasVacacionesRestante;

    @Column(name = "requiere_cambio_contrasena")
    private Boolean requiereCambioContrasena = false;

    // Relación con solicitudes - IGNORAR en JSON para evitar ciclos
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"usuario"})
    private List<Solicitud> solicitudes;

    // Constructores
    public Usuario() {}

    public Usuario(String nombre, String apellido, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }

    // Getters y Setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getNroCedula() {
        return nroCedula;
    }

    public void setNroCedula(Integer nroCedula) {
        this.nroCedula = nroCedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(String antiguedad) {
        this.antiguedad = antiguedad;
    }

    public Integer getDiasVacaciones() {
        return diasVacaciones;
    }

    public void setDiasVacaciones(Integer diasVacaciones) {
        this.diasVacaciones = diasVacaciones;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getDiasVacacionesRestante() {
        return diasVacacionesRestante;
    }

    public void setDiasVacacionesRestante(Integer diasVacacionesRestante) {
        this.diasVacacionesRestante = diasVacacionesRestante;
    }

    public Boolean getRequiereCambioContrasena() {
        return requiereCambioContrasena;
    }

    public void setRequiereCambioContrasena(Boolean requiereCambioContrasena) {
        this.requiereCambioContrasena = requiereCambioContrasena;
    }

    public List<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", estado=" + estado +
                '}';
    }
}