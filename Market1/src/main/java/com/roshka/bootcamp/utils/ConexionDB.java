package com.roshka.bootcamp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:postgresql://localhost:5432/bootcamp_market";
    private static final String USER = "postgres";
    private static final String PASSWORD = "isaias018";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontró el Driver de PostgreSQL", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
