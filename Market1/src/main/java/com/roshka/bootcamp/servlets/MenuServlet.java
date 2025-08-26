package com.roshka.bootcamp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html><body>");
            out.println("<h1>Menu de Consultas - Bootcamp Market</h1>");
            out.println("<ul>");
            out.println("<li><a href='consulta?tipo=clientes'>Clientes</a></li>");
            out.println("<li><a href='consulta?tipo=productos'>Productos</a></li>");
            out.println("<li><a href='consulta?tipo=ventas'>Ventas</a></li>");
            out.println("</ul>");
            out.println("<h2>Formularios para Agregar Datos</h2>");
            out.println("<ul>");
            out.println("<li><a href='formulario?tabla=productos'>Agregar Producto</a></li>");
            out.println("</ul>");
            out.println("</body></html>");
        }
    }
}
