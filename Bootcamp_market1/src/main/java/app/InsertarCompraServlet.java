package com.roshka.bootcamp.servlets;

import com.roshka.bootcamp.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/insertarCompra")
public class InsertarCompraServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int clienteId = Integer.parseInt(request.getParameter("cliente_id"));
        int productoId = Integer.parseInt(request.getParameter("producto_id"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO compras (cliente_id, producto_id, cantidad) VALUES (?, ?, ?)")) {

            ps.setInt(1, clienteId);
            ps.setInt(2, productoId);
            ps.setInt(3, cantidad);
            ps.executeUpdate();

            response.sendRedirect("consultas/compras");
        } catch (Exception e) {
            throw new ServletException("Error al insertar compra", e);
        }
    }
}

