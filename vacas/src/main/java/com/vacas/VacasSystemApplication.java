package com.vacas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VacasSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(VacasSystemApplication.class, args);
        System.out.println("===========================================");
        System.out.println("🚀 Vacas System API iniciado correctamente!");
        System.out.println("📍 URL:http://localhost:8080/swagger-ui/index.html");
        System.out.println("📊 Base de datos: PostgreSQL");
        System.out.println("🔗 Swagger UI: http://localhost:8080/v3/api-docs\n");
        System.out.println("===========================================");
    }
}