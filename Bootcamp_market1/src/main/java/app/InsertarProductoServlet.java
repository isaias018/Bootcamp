package com.roshka.bootcamp.servlets;

import com.roshka.bootcamp.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/insertarProducto")
public class InsertarProductoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String precio = request.getParameter("precio");

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO productos (nombre, precio) VALUES (?, ?)")) {

            ps.setString(1, nombre);
            ps.setDouble(2, Double.parseDouble(precio));
            ps.executeUpdate();

            response.sendRedirect("consultas/productos");
        } catch (Exception e) {
            throw new ServletException("Error al insertar producto", e);
        }
    }
}
