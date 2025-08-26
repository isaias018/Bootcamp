package com.roshka.bootcamp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String DB_URL  = "jdbc:postgresql://localhost:5432/bootcamp_market";
    private static final String DB_USER = "postgres";
    private static final String DB_PASS = "postgres";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}
