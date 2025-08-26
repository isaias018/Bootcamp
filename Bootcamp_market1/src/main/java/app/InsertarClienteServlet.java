package com.roshka.bootcamp.servlets;

import com.roshka.bootcamp.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/insertarCliente")
public class InsertarClienteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO clientes (nombre, email) VALUES (?, ?)")) {

            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.executeUpdate();

            response.sendRedirect("consultas/clientes");
        } catch (Exception e) {
            throw new ServletException("Error al insertar cliente", e);
        }
    }
}
