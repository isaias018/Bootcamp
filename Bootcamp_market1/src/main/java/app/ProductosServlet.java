package com.roshka.bootcamp.servlets;

import com.roshka.bootcamp.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/consultas/productos")
public class ProductosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter();
             Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, nombre, precio FROM productos")) {

            out.println("<h1>Lista de Productos</h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Nombre</th><th>Precio</th></tr>");
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("nombre") + "</td>");
                out.println("<td>" + rs.getDouble("precio") + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<br><a href='../index.html'>Volver al menú</a>");
        } catch (SQLException e) {
            throw new ServletException("Error al consultar productos", e);
        }
    }
}

