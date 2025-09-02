package com.vacas.repository;

import com.vacas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar por correo electrónico
    Optional<Usuario> findByCorreo(String correo);

    // Buscar por número de cédula
    Optional<Usuario> findByNroCedula(Integer nroCedula);

    // Buscar usuarios activos
    List<Usuario> findByEstadoTrue();

    // Buscar usuarios inactivos
    List<Usuario> findByEstadoFalse();

    // Buscar por nombre y apellido (ignorando mayúsculas/minúsculas)
    @Query("SELECT u FROM Usuario u WHERE LOWER(u.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')) " +
            "OR LOWER(u.apellido) LIKE LOWER(CONCAT('%', :apellido, '%'))")
    List<Usuario> findByNombreOrApellidoContainingIgnoreCase(@Param("nombre") String nombre,
                                                             @Param("apellido") String apellido);

    // Buscar por rol
    @Query("SELECT u FROM Usuario u WHERE u.rol.nombre = :nombreRol")
    List<Usuario> findByRolNombre(@Param("nombreRol") String nombreRol);

    // Buscar por equipo
    @Query("SELECT u FROM Usuario u WHERE u.equipo.nombre = :nombreEquipo")
    List<Usuario> findByEquipoNombre(@Param("nombreEquipo") String nombreEquipo);

    // Buscar por cargo
    @Query("SELECT u FROM Usuario u WHERE u.cargo.nombre = :nombreCargo")
    List<Usuario> findByCargoNombre(@Param("nombreCargo") String nombreCargo);

    // Verificar si existe por correo
    boolean existsByCorreo(String correo);

    // Verificar si existe por cédula
    boolean existsByNroCedula(Integer nroCedula);

    // Contar usuarios activos
    long countByEstadoTrue();

    // Usuarios que requieren cambio de contraseña
    List<Usuario> findByRequiereCambioContrasenaTrue();

    // Buscar usuarios con días de vacaciones disponibles
    @Query("SELECT u FROM Usuario u WHERE u.diasVacacionesRestante > 0")
    List<Usuario> findUsuariosConVacacionesDisponibles();
}