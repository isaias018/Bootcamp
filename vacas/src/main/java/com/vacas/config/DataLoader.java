package com.vacas.config;

import com.vacas.entity.Cargo;
import com.vacas.entity.Equipo;
import com.vacas.entity.Rol;
import com.vacas.entity.Usuario;
import com.vacas.repository.CargoRepository;
import com.vacas.repository.EquipoRepository;
import com.vacas.repository.RolRepository;
import com.vacas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        // Solo cargar datos si la base de datos está vacía
        if (rolRepository.count() == 0) {
            cargarDatosIniciales();
        }
    }

    private void cargarDatosIniciales() {
        System.out.println("🚀 Cargando datos iniciales...");

        // Crear roles
        Rol rolAdmin = new Rol("Administrador");
        Rol rolManager = new Rol("Manager");
        Rol rolEmpleado = new Rol("Empleado");
        Rol rolLider = new Rol("Líder de Equipo");

        rolRepository.save(rolAdmin);
        rolRepository.save(rolManager);
        rolRepository.save(rolEmpleado);
        rolRepository.save(rolLider);

        // Crear cargos
        Cargo cargoDirector = new Cargo("Director");
        Cargo cargoGerente = new Cargo("Gerente");
        Cargo cargoAnalista = new Cargo("Analista");
        Cargo cargoDeveloper = new Cargo("Desarrollador");
        Cargo cargoDesigner = new Cargo("Diseñador");
        Cargo cargoTester = new Cargo("QA Tester");

        cargoRepository.save(cargoDirector);
        cargoRepository.save(cargoGerente);
        cargoRepository.save(cargoAnalista);
        cargoRepository.save(cargoDeveloper);
        cargoRepository.save(cargoDesigner);
        cargoRepository.save(cargoTester);

        // Crear equipos
        Equipo equipoDesarrollo = new Equipo("Desarrollo");
        Equipo equipoMarketing = new Equipo("Marketing");
        Equipo equipoVentas = new Equipo("Ventas");
        Equipo equipoRRHH = new Equipo("Recursos Humanos");
        Equipo equipoFinanzas = new Equipo("Finanzas");
        Equipo equipoQA = new Equipo("Quality Assurance");

        equipoRepository.save(equipoDesarrollo);
        equipoRepository.save(equipoMarketing);
        equipoRepository.save(equipoVentas);
        equipoRepository.save(equipoRRHH);
        equipoRepository.save(equipoFinanzas);
        equipoRepository.save(equipoQA);

        // Crear usuarios de ejemplo
        Usuario admin = new Usuario();
        admin.setNombre("Admin");
        admin.setApellido("Sistema");
        admin.setCorreo("admin@vacas.com");
        admin.setNroCedula(12345678);
        admin.setTelefono("+595981123456");
        admin.setFechaIngreso(LocalDate.of(2020, 1, 15));
        admin.setFechaNacimiento(LocalDate.of(1985, 6, 10));
        admin.setContrasena("admin123"); // En un sistema real, esto debería estar hasheado
        admin.setRol(rolAdmin);
        admin.setCargo(cargoDirector);
        admin.setEquipo(equipoRRHH);
        admin.setEstado(true);
        admin.setRequiereCambioContrasena(false);

        Usuario manager = new Usuario();
        manager.setNombre("María");
        manager.setApellido("González");
        manager.setCorreo("maria.gonzalez@vacas.com");
        manager.setNroCedula(87654321);
        manager.setTelefono("+595981987654");
        manager.setFechaIngreso(LocalDate.of(2021, 3, 10));
        manager.setFechaNacimiento(LocalDate.of(1988, 12, 25));
        manager.setContrasena("manager123");
        manager.setRol(rolManager);
        manager.setCargo(cargoGerente);
        manager.setEquipo(equipoDesarrollo);
        manager.setEstado(true);
        manager.setRequiereCambioContrasena(false);

        Usuario empleado1 = new Usuario();
        empleado1.setNombre("Juan");
        empleado1.setApellido("Pérez");
        empleado1.setCorreo("juan.perez@vacas.com");
        empleado1.setNroCedula(11223344);
        empleado1.setTelefono("+595981112233");
        empleado1.setFechaIngreso(LocalDate.of(2022, 8, 20));
        empleado1.setFechaNacimiento(LocalDate.of(1990, 4, 15));
        empleado1.setContrasena("empleado123");
        empleado1.setRol(rolEmpleado);
        empleado1.setCargo(cargoDeveloper);
        empleado1.setEquipo(equipoDesarrollo);
        empleado1.setEstado(true);
        empleado1.setRequiereCambioContrasena(false);

        Usuario empleado2 = new Usuario();
        empleado2.setNombre("Ana");
        empleado2.setApellido("López");
        empleado2.setCorreo("ana.lopez@vacas.com");
        empleado2.setNroCedula(55667788);
        empleado2.setTelefono("+595981556677");
        empleado2.setFechaIngreso(LocalDate.of(2023, 2, 5));
        empleado2.setFechaNacimiento(LocalDate.of(1992, 9, 30));
        empleado2.setContrasena("empleado456");
        empleado2.setRol(rolEmpleado);
        empleado2.setCargo(cargoDesigner);
        empleado2.setEquipo(equipoMarketing);
        empleado2.setEstado(true);
        empleado2.setRequiereCambioContrasena(false);

        Usuario lider = new Usuario();
        lider.setNombre("Carlos");
        lider.setApellido("Martínez");
        lider.setCorreo("carlos.martinez@vacas.com");
        lider.setNroCedula(99887766);
        lider.setTelefono("+595981998877");
        lider.setFechaIngreso(LocalDate.of(2019, 11, 12));
        lider.setFechaNacimiento(LocalDate.of(1986, 7, 18));
        lider.setContrasena("lider123");
        lider.setRol(rolLider);
        lider.setCargo(cargoAnalista);
        lider.setEquipo(equipoQA);
        lider.setEstado(true);
        lider.setRequiereCambioContrasena(false);

        // Calcular automáticamente días de vacaciones y antigüedad
        calcularDatosUsuario(admin);
        calcularDatosUsuario(manager);
        calcularDatosUsuario(empleado1);
        calcularDatosUsuario(empleado2);
        calcularDatosUsuario(lider);

        usuarioRepository.save(admin);
        usuarioRepository.save(manager);
        usuarioRepository.save(empleado1);
        usuarioRepository.save(empleado2);
        usuarioRepository.save(lider);

        System.out.println("✅ Datos iniciales cargados exitosamente:");
        System.out.println("   📋 Roles: " + rolRepository.count());
        System.out.println("   💼 Cargos: " + cargoRepository.count());
        System.out.println("   👥 Equipos: " + equipoRepository.count());
        System.out.println("   👤 Usuarios: " + usuarioRepository.count());
        System.out.println("");
        System.out.println("👤 Usuarios de prueba creados:");
        System.out.println("   🔑 Admin: admin@vacas.com / admin123");
        System.out.println("   👨‍💼 Manager: maria.gonzalez@vacas.com / manager123");
        System.out.println("   👨‍💻 Empleado 1: juan.perez@vacas.com / empleado123");
        System.out.println("   👩‍🎨 Empleado 2: ana.lopez@vacas.com / empleado456");
        System.out.println("   👨‍🏫 Líder: carlos.martinez@vacas.com / lider123");
    }

    private void calcularDatosUsuario(Usuario usuario) {
        if (usuario.getFechaIngreso() != null) {
            // Calcular antigüedad
            LocalDate ahora = LocalDate.now();
            int años = ahora.getYear() - usuario.getFechaIngreso().getYear();
            int meses = ahora.getMonthValue() - usuario.getFechaIngreso().getMonthValue();

            if (meses < 0) {
                años--;
                meses += 12;
            }

            if (años > 0) {
                usuario.setAntiguedad(años + " años, " + meses + " meses");
            } else {
                usuario.setAntiguedad(meses + " meses");
            }

            // Calcular días de vacaciones según antigüedad
            int diasVacaciones;
            if (años == 0) {
                diasVacaciones = 12; // Primer año
            } else if (años <= 5) {
                diasVacaciones = 12 + (años * 2); // Incremento gradual
            } else {
                diasVacaciones = 30; // Máximo después de 5 años
            }

            usuario.setDiasVacaciones(diasVacaciones);
            usuario.setDiasVacacionesRestante(diasVacaciones);
        }
    }
}
