package com.vacas.controller;

import com.vacas.entity.Usuario;
import com.vacas.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*") // Para permitir conexiones desde el frontend
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Crear usuario
    @PostMapping
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody Usuario usuario) {
        try {
            Usuario usuarioCreado = usuarioService.crearUsuario(usuario);
            return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios() {
        try {
            List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Long id) {
        try {
            Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorId(id);
            if (usuario.isPresent()) {
                return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Buscar usuario por correo
    @GetMapping("/correo/{correo}")
    public ResponseEntity<?> obtenerUsuarioPorCorreo(@PathVariable String correo) {
        try {
            Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorCorreo(correo);
            if (usuario.isPresent()) {
                return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener usuarios activos
    @GetMapping("/activos")
    public ResponseEntity<List<Usuario>> obtenerUsuariosActivos() {
        try {
            List<Usuario> usuarios = usuarioService.obtenerUsuariosActivos();
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Buscar usuarios
    @GetMapping("/buscar")
    public ResponseEntity<List<Usuario>> buscarUsuarios(@RequestParam String termino) {
        try {
            List<Usuario> usuarios = usuarioService.buscarUsuarios(termino);
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener usuarios por rol
    @GetMapping("/rol/{nombreRol}")
    public ResponseEntity<List<Usuario>> obtenerUsuariosPorRol(@PathVariable String nombreRol) {
        try {
            List<Usuario> usuarios = usuarioService.obtenerUsuariosPorRol(nombreRol);
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Contar usuarios activos
    @GetMapping("/count")
    public ResponseEntity<Long> contarUsuariosActivos() {
        try {
            long count = usuarioService.contarUsuariosActivos();
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener usuarios con vacaciones disponibles
    @GetMapping("/vacaciones-disponibles")
    public ResponseEntity<List<Usuario>> obtenerUsuariosConVacacionesDisponibles() {
        try {
            List<Usuario> usuarios = usuarioService.obtenerUsuariosConVacacionesDisponibles();
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
        try {
            Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, usuario);
            return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Cambiar estado del usuario
    @PatchMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstadoUsuario(@PathVariable Long id, @RequestParam Boolean estado) {
        try {
            Usuario usuario = usuarioService.cambiarEstadoUsuario(id, estado);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar usuario (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioService.eliminarUsuario(id);
            return new ResponseEntity<>("Usuario eliminado correctamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}