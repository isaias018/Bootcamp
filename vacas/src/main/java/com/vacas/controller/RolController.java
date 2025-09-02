package com.vacas.controller;

import com.vacas.entity.*;
import com.vacas.repository.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Controller para Roles
@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
class RolController {

    @Autowired
    private RolRepository rolRepository;

    @PostMapping
    public ResponseEntity<?> crearRol(@Valid @RequestBody Rol rol) {
        try {
            if (rolRepository.existsByNombre(rol.getNombre())) {
                return new ResponseEntity<>("Ya existe un rol con este nombre", HttpStatus.BAD_REQUEST);
            }
            Rol rolCreado = rolRepository.save(rol);
            return new ResponseEntity<>(rolCreado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Rol>> obtenerTodosLosRoles() {
        try {
            List<Rol> roles = rolRepository.findAll();
            return new ResponseEntity<>(roles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerRolPorId(@PathVariable Long id) {
        try {
            Optional<Rol> rol = rolRepository.findById(id);
            if (rol.isPresent()) {
                return new ResponseEntity<>(rol.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Rol no encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarRol(@PathVariable Long id, @Valid @RequestBody Rol rol) {
        try {
            Optional<Rol> rolExistente = rolRepository.findById(id);
            if (!rolExistente.isPresent()) {
                return new ResponseEntity<>("Rol no encontrado", HttpStatus.NOT_FOUND);
            }

            Rol rolActualizar = rolExistente.get();
            rolActualizar.setNombre(rol.getNombre());

            Rol rolActualizado = rolRepository.save(rolActualizar);
            return new ResponseEntity<>(rolActualizado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRol(@PathVariable Long id) {
        try {
            if (!rolRepository.existsById(id)) {
                return new ResponseEntity<>("Rol no encontrado", HttpStatus.NOT_FOUND);
            }
            rolRepository.deleteById(id);
            return new ResponseEntity<>("Rol eliminado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

// Controller para Cargos
@RestController
@RequestMapping("/api/cargos")
@CrossOrigin(origins = "*")
class CargoController {

    @Autowired
    private CargoRepository cargoRepository;

    @PostMapping
    public ResponseEntity<?> crearCargo(@Valid @RequestBody Cargo cargo) {
        try {
            if (cargoRepository.existsByNombre(cargo.getNombre())) {
                return new ResponseEntity<>("Ya existe un cargo con este nombre", HttpStatus.BAD_REQUEST);
            }
            Cargo cargoCreado = cargoRepository.save(cargo);
            return new ResponseEntity<>(cargoCreado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Cargo>> obtenerTodosLosCargos() {
        try {
            List<Cargo> cargos = cargoRepository.findAll();
            return new ResponseEntity<>(cargos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCargoPorId(@PathVariable Long id) {
        try {
            Optional<Cargo> cargo = cargoRepository.findById(id);
            if (cargo.isPresent()) {
                return new ResponseEntity<>(cargo.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Cargo no encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCargo(@PathVariable Long id, @Valid @RequestBody Cargo cargo) {
        try {
            Optional<Cargo> cargoExistente = cargoRepository.findById(id);
            if (!cargoExistente.isPresent()) {
                return new ResponseEntity<>("Cargo no encontrado", HttpStatus.NOT_FOUND);
            }

            Cargo cargoActualizar = cargoExistente.get();
            cargoActualizar.setNombre(cargo.getNombre());

            Cargo cargoActualizado = cargoRepository.save(cargoActualizar);
            return new ResponseEntity<>(cargoActualizado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCargo(@PathVariable Long id) {
        try {
            if (!cargoRepository.existsById(id)) {
                return new ResponseEntity<>("Cargo no encontrado", HttpStatus.NOT_FOUND);
            }
            cargoRepository.deleteById(id);
            return new ResponseEntity<>("Cargo eliminado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

// Controller para Equipos
@RestController
@RequestMapping("/api/equipos")
@CrossOrigin(origins = "*")
class EquipoController {

    @Autowired
    private EquipoRepository equipoRepository;

    @PostMapping
    public ResponseEntity<?> crearEquipo(@Valid @RequestBody Equipo equipo) {
        try {
            if (equipoRepository.existsByNombre(equipo.getNombre())) {
                return new ResponseEntity<>("Ya existe un equipo con este nombre", HttpStatus.BAD_REQUEST);
            }
            Equipo equipoCreado = equipoRepository.save(equipo);
            return new ResponseEntity<>(equipoCreado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Equipo>> obtenerTodosLosEquipos() {
        try {
            List<Equipo> equipos = equipoRepository.findAll();
            return new ResponseEntity<>(equipos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerEquipoPorId(@PathVariable Long id) {
        try {
            Optional<Equipo> equipo = equipoRepository.findById(id);
            if (equipo.isPresent()) {
                return new ResponseEntity<>(equipo.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Equipo no encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEquipo(@PathVariable Long id, @Valid @RequestBody Equipo equipo) {
        try {
            Optional<Equipo> equipoExistente = equipoRepository.findById(id);
            if (!equipoExistente.isPresent()) {
                return new ResponseEntity<>("Equipo no encontrado", HttpStatus.NOT_FOUND);
            }

            Equipo equipoActualizar = equipoExistente.get();
            equipoActualizar.setNombre(equipo.getNombre());

            Equipo equipoActualizado = equipoRepository.save(equipoActualizar);
            return new ResponseEntity<>(equipoActualizado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEquipo(@PathVariable Long id) {
        try {
            if (!equipoRepository.existsById(id)) {
                return new ResponseEntity<>("Equipo no encontrado", HttpStatus.NOT_FOUND);
            }
            equipoRepository.deleteById(id);
            return new ResponseEntity<>("Equipo eliminado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}