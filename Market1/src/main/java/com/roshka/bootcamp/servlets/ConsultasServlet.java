package com.roshka.bootcamp.servlets;

import com.roshka.bootcamp.utils.ConexionDB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/consultas")
public class ConsultasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String consulta = request.getParameter("q");
        String sql = "";

        switch (consulta) {
            case "1":
                sql = "SELECT c.nombre, c.apellido, COUNT(f.id_factura) AS cantidad " +
                        "FROM clientes c JOIN factura f ON c.id_cliente=f.id_cliente " +
                        "GROUP BY c.id_cliente ORDER BY cantidad DESC LIMIT 5";
                break;
            case "2":
                sql = "SELECT c.nombre, c.apellido, SUM(f.total) AS gastado " +
                        "FROM clientes c JOIN factura f ON c.id_cliente=f.id_cliente " +
                        "GROUP BY c.id_cliente ORDER BY gastado DESC LIMIT 5";
                break;
            case "3":
                sql = "SELECT m.nombre_moneda, COUNT(*) AS uso " +
                        "FROM factura f JOIN moneda m ON f.id_moneda=m.id_moneda " +
                        "GROUP BY m.id_moneda ORDER BY uso DESC LIMIT 5";
                break;
            case "4":
                sql = "SELECT p.nombre_proveedor, COUNT(pr.id_producto) AS productos " +
                        "FROM proveedor p JOIN producto pr ON p.id_proveedor=pr.id_proveedor " +
                        "GROUP BY p.id_proveedor ORDER BY productos DESC LIMIT 1";
                break;
            case "5":
                sql = "SELECT pr.nombre, SUM(fd.cantidad) AS vendidos " +
                        "FROM factura_detalle fd JOIN producto pr ON fd.id_producto=pr.id_producto " +
                        "GROUP BY pr.id_producto ORDER BY vendidos DESC LIMIT 5";
                break;
            case "6":
                sql = "SELECT pr.nombre, SUM(fd.cantidad) AS vendidos " +
                        "FROM factura_detalle fd JOIN producto pr ON fd.id_producto=pr.id_producto " +
                        "GROUP BY pr.id_producto ORDER BY vendidos ASC LIMIT 5";
                break;
            case "7":
                sql = "SELECT f.fecha_emision, c.nombre, c.apellido, pr.nombre, fd.cantidad, tf.nombre " +
                        "FROM factura f JOIN clientes c ON f.id_cliente=c.id_cliente " +
                        "JOIN factura_detalle fd ON f.id_factura=fd.id_factura " +
                        "JOIN producto pr ON fd.id_producto=pr.id_producto " +
                        "JOIN factura_tipo tf ON f.id_tipo=tf.id_tipo " +
                        "WHERE f.id_factura=1"; // cambiar según factura
                break;
            case "8":
                sql = "SELECT id_factura, total FROM factura ORDER BY total DESC";
                break;
            case "9":
                sql = "SELECT id_factura, total, (total*0.1) AS iva10 FROM factura";
                break;
            default:
                sql = "";
                break;
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter();
             Connection con = ConexionDB.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            out.println("<html><body><h2>Resultado de la consulta</h2><table border='1'>");
            int cols = rs.getMetaData().getColumnCount();
            out.println("<tr>");
            for (int i = 1; i <= cols; i++) {
                out.println("<th>" + rs.getMetaData().getColumnName(i) + "</th>");
            }
            out.println("</tr>");

            while (rs.next()) {
                out.println("<tr>");
                for (int i = 1; i <= cols; i++) {
                    out.println("<td>" + rs.getString(i) + "</td>");
                }
                out.println("</tr>");
            }
            out.println("</table><br><a href='menu'>Volver al menú</a></body></html>");
        } catch (Exception e) {
            throw new ServletException("Error en la consulta: " + e.getMessage(), e);
        }
    }
}
