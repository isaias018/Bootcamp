package com.roshka.bootcamp.servlets;

import com.roshka.bootcamp.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/consultas/compras")
public class ComprasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter();
             Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT id, cliente_id, producto_id, cantidad FROM compras")) {

            out.println("<h1>Lista de Compras</h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Cliente</th><th>Producto</th><th>Cantidad</th></tr>");
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getInt("cliente_id") + "</td>");
                out.println("<td>" + rs.getInt("producto_id") + "</td>");
                out.println("<td>" + rs.getInt("cantidad") + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<br><a href='../index.html'>Volver al menú</a>");
        } catch (SQLException e) {
            throw new ServletException("Error al consultar compras", e);
        }
    }
}
