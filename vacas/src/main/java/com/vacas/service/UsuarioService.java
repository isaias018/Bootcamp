package com.vacas.service;

import com.vacas.entity.Usuario;
import com.vacas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Crear usuario
    public Usuario crearUsuario(Usuario usuario) {
        // Validaciones
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new RuntimeException("Ya existe un usuario con este correo electrónico");
        }

        if (usuario.getNroCedula() != null && usuarioRepository.existsByNroCedula(usuario.getNroCedula())) {
            throw new RuntimeException("Ya existe un usuario con este número de cédula");
        }

        // Establecer valores por defecto
        if (usuario.getEstado() == null) {
            usuario.setEstado(true);
        }

        if (usuario.getFechaIngreso() == null) {
            usuario.setFechaIngreso(LocalDate.now());
        }

        // Calcular antigüedad si se proporciona fecha de ingreso
        if (usuario.getFechaIngreso() != null) {
            calcularAntiguedad(usuario);
        }

        // Calcular días de vacaciones basado en antigüedad
        calcularDiasVacaciones(usuario);

        return usuarioRepository.save(usuario);
    }

    // Obtener todos los usuarios
    @Transactional(readOnly = true)
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    // Obtener usuario por ID
    @Transactional(readOnly = true)
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // Obtener usuario por correo
    @Transactional(readOnly = true)
    public Optional<Usuario> obtenerUsuarioPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    // Obtener usuarios activos
    @Transactional(readOnly = true)
    public List<Usuario> obtenerUsuariosActivos() {
        return usuarioRepository.findByEstadoTrue();
    }

    // Buscar usuarios por nombre o apellido
    @Transactional(readOnly = true)
    public List<Usuario> buscarUsuarios(String termino) {
        return usuarioRepository.findByNombreOrApellidoContainingIgnoreCase(termino, termino);
    }

    // Actualizar usuario
    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Validar correo único (si se cambió)
        if (!usuarioExistente.getCorreo().equals(usuarioActualizado.getCorreo()) &&
                usuarioRepository.existsByCorreo(usuarioActualizado.getCorreo())) {
            throw new RuntimeException("Ya existe un usuario con este correo electrónico");
        }

        // Validar cédula única (si se cambió)
        if (usuarioActualizado.getNroCedula() != null &&
                !usuarioActualizado.getNroCedula().equals(usuarioExistente.getNroCedula()) &&
                usuarioRepository.existsByNroCedula(usuarioActualizado.getNroCedula())) {
            throw new RuntimeException("Ya existe un usuario con este número de cédula");
        }

        // Actualizar campos
        usuarioExistente.setNombre(usuarioActualizado.getNombre());
        usuarioExistente.setApellido(usuarioActualizado.getApellido());
        usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
        usuarioExistente.setNroCedula(usuarioActualizado.getNroCedula());
        usuarioExistente.setTelefono(usuarioActualizado.getTelefono());
        usuarioExistente.setFechaNacimiento(usuarioActualizado.getFechaNacimiento());

        if (usuarioActualizado.getFechaIngreso() != null) {
            usuarioExistente.setFechaIngreso(usuarioActualizado.getFechaIngreso());
            calcularAntiguedad(usuarioExistente);
            calcularDiasVacaciones(usuarioExistente);
        }

        if (usuarioActualizado.getEstado() != null) {
            usuarioExistente.setEstado(usuarioActualizado.getEstado());
        }

        return usuarioRepository.save(usuarioExistente);
    }

    // Cambiar estado del usuario
    public Usuario cambiarEstadoUsuario(Long id, Boolean estado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setEstado(estado);
        return usuarioRepository.save(usuario);
    }

    // Eliminar usuario (soft delete)
    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setEstado(false);
        usuarioRepository.save(usuario);
    }

    // Contar usuarios activos
    @Transactional(readOnly = true)
    public long contarUsuariosActivos() {
        return usuarioRepository.countByEstadoTrue();
    }

    // Obtener usuarios por rol
    @Transactional(readOnly = true)
    public List<Usuario> obtenerUsuariosPorRol(String nombreRol) {
        return usuarioRepository.findByRolNombre(nombreRol);
    }

    // Obtener usuarios con vacaciones disponibles
    @Transactional(readOnly = true)
    public List<Usuario> obtenerUsuariosConVacacionesDisponibles() {
        return usuarioRepository.findUsuariosConVacacionesDisponibles();
    }

    // Calcular antigüedad
    private void calcularAntiguedad(Usuario usuario) {
        if (usuario.getFechaIngreso() != null) {
            Period periodo = Period.between(usuario.getFechaIngreso(), LocalDate.now());
            int años = periodo.getYears();
            int meses = periodo.getMonths();

            if (años > 0) {
                usuario.setAntiguedad(años + " años, " + meses + " meses");
            } else {
                usuario.setAntiguedad(meses + " meses");
            }
        }
    }

    // Calcular días de vacaciones según antigüedad
    private void calcularDiasVacaciones(Usuario usuario) {
        if (usuario.getFechaIngreso() != null) {
            Period periodo = Period.between(usuario.getFechaIngreso(), LocalDate.now());
            int años = periodo.getYears();

            int diasVacaciones;
            if (años == 0) {
                diasVacaciones = 12; // Primer año
            } else if (años <= 5) {
                diasVacaciones = 12 + (años * 2); // Incremento gradual
            } else {
                diasVacaciones = 30; // Máximo después de 5 años
            }

            usuario.setDiasVacaciones(diasVacaciones);

            // Si es un usuario nuevo, días restantes = días totales
            if (usuario.getDiasVacacionesRestante() == null) {
                usuario.setDiasVacacionesRestante(diasVacaciones);
            }
        }
    }
}