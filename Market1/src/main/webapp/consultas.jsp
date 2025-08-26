<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*, com.roshka.bootcamp.utils.ConexionDB" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consultas</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 16px; }
        h1, h2 { margin: 0.2rem 0 1rem; }
        table { border-collapse: collapse; width: 100%; margin-top: 12px; }
        th, td { border: 1px solid #ddd; padding: 8px; }
        th { background: #f2f2f2; text-align: left; }
        .note { color: #666; font-size: 0.9rem; }
        .back { margin-top: 16px; display: inline-block; }
        .error { color: #c00; font-weight: bold; }
        form.inline { display:inline-block; margin-left: 8px; }
    </style>
</head>
<body>

<%
    String q = request.getParameter("q");              // 1..9
    String facturaIdParam = request.getParameter("facturaId");
    if (facturaIdParam == null || facturaIdParam.isBlank()) facturaIdParam = "1";

    String titulo = "Consulta";
    String sql = null;
    boolean usePrepared = false; // solo la #7 usa parámetro

    // Ajusta nombres de columnas según tu esquema real si difieren.
    if ("1".equals(q)) {
        titulo = "1) Top clientes con más facturas";
        sql =
          "SELECT c.id_cliente, c.nombre, c.apellido, COUNT(f.id_factura) AS cantidad_facturas " +
          "FROM clientes c " +
          "JOIN factura f ON f.id_cliente = c.id_cliente " +
          "GROUP BY c.id_cliente, c.nombre, c.apellido " +
          "ORDER BY cantidad_facturas DESC " +
          "LIMIT 10";
    } else if ("2".equals(q)) {
        titulo = "2) Top clientes que más gastaron";
        // Si no tienes columna 'total' en factura, calcula con detalle:
        // SUM(fd.cantidad * fd.precio_unitario)
        sql =
          "SELECT c.id_cliente, c.nombre, c.apellido, COALESCE(SUM(f.total), 0) AS total_gastado " +
          "FROM clientes c " +
          "JOIN factura f ON f.id_cliente = c.id_cliente " +
          "GROUP BY c.id_cliente, c.nombre, c.apellido " +
          "ORDER BY total_gastado DESC " +
          "LIMIT 10";
    } else if ("3".equals(q)) {
        titulo = "3) Top monedas más utilizadas";
        // Cambia m.nombre por m.codigo si tu tabla moneda usa 'codigo'
        sql =
          "SELECT m.id_moneda, m.nombre AS moneda, COUNT(*) AS uso " +
          "FROM factura f " +
          "JOIN moneda m ON m.id_moneda = f.id_moneda " +
          "GROUP BY m.id_moneda, m.nombre " +
          "ORDER BY uso DESC " +
          "LIMIT 10";
    } else if ("4".equals(q)) {
        titulo = "4) Top proveedor de productos (por cantidad de productos distintos)";
        // Requiere que producto tenga id_proveedor
        sql =
          "SELECT p.id_proveedor, p.nombre_proveedor, COUNT(pr.id_producto) AS cantidad_productos " +
          "FROM proveedor p " +
          "JOIN producto pr ON pr.id_proveedor = p.id_proveedor " +
          "GROUP BY p.id_proveedor, p.nombre_proveedor " +
          "ORDER BY cantidad_productos DESC " +
          "LIMIT 10";
    } else if ("5".equals(q)) {
        titulo = "5) Productos más vendidos (por cantidad)";
        sql =
          "SELECT pr.id_producto, pr.nombre_producto, COALESCE(SUM(fd.cantidad), 0) AS total_vendidos " +
          "FROM producto pr " +
          "JOIN factura_detalle fd ON fd.id_producto = pr.id_producto " +
          "GROUP BY pr.id_producto, pr.nombre_producto " +
          "ORDER BY total_vendidos DESC " +
          "LIMIT 10";
    } else if ("6".equals(q)) {
        titulo = "6) Productos menos vendidos (por cantidad)";
        sql =
          "SELECT pr.id_producto, pr.nombre_producto, COALESCE(SUM(fd.cantidad), 0) AS total_vendidos " +
          "FROM producto pr " +
          "LEFT JOIN factura_detalle fd ON fd.id_producto = pr.id_producto " +
          "GROUP BY pr.id_producto, pr.nombre_producto " +
          "ORDER BY total_vendidos ASC " +
          "LIMIT 10";
    } else if ("7".equals(q)) {
        titulo = "7) Detalle de factura específica (fecha, cliente, productos, cantidades, tipo)";
        // Cambiable con ?facturaId=#
        sql =
          "SELECT f.fecha_emision, c.nombre AS nombre_cliente, c.apellido AS apellido_cliente, " +
          "       pr.nombre_producto, fd.cantidad, tf.nombre_tipo " +
          "FROM factura f " +
          "JOIN clientes c ON c.id_cliente = f.id_cliente " +
          "JOIN factura_detalle fd ON fd.id_factura = f.id_factura " +
          "JOIN producto pr ON pr.id_producto = fd.id_producto " +
          "JOIN factura_tipo tf ON tf.id_tipo = f.id_tipo " +
          "WHERE f.id_factura = ?";
        usePrepared = true;
    } else if ("8".equals(q)) {
        titulo = "8) Montos de facturas ordenadas por total";
        sql =
          "SELECT f.id_factura, f.fecha_emision, f.total " +
          "FROM factura f " +
          "ORDER BY f.total DESC";
    } else if ("9".equals(q)) {
        titulo = "9) IVA 10% de los montos totales de facturas";
        sql =
          "SELECT f.id_factura, f.total, ROUND(f.total * 0.10, 2) AS iva_10 " +
          "FROM factura f";
    } else {
        titulo = "Consulta no válida";
    }
%>

<h1><%= titulo %></h1>

<% if ("7".equals(q)) { %>
    <form class="inline" method="get" action="consultas.jsp">
        <input type="hidden" name="q" value="7"/>
        Factura ID: <input type="number" name="facturaId" value="<%= facturaIdParam %>" min="1" required/>
        <button type="submit">Ver</button>
    </form>
<% } %>

<p class="note">
    (Usa el menú para otras consultas, o cambia el parámetro <code>q</code> en la URL. 
    Para la #7 podés pasar <code>?q=7&amp;facturaId=123</code>).
</p>

<%
    if (sql == null) {
%>
    <p class="error">No se ha definido una consulta para el parámetro <code>q</code>.</p>
<%
    } else {
        Connection con = null;
        Statement st = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ConexionDB.getConnection();

            if (usePrepared) {
                ps = con.prepareStatement(sql);
                try {
                    ps.setInt(1, Integer.parseInt(facturaIdParam));
                } catch (NumberFormatException nfe) {
                    throw new SQLException("El parámetro facturaId debe ser numérico.", nfe);
                }
                rs = ps.executeQuery();
            } else {
                st = con.createStatement();
                rs = st.executeQuery(sql);
            }

            ResultSetMetaData md = rs.getMetaData();
            int cols = md.getColumnCount();

%>
            <table>
                <tr>
                    <% for (int i = 1; i <= cols; i++) { %>
                        <th><%= md.getColumnName(i) %></th>
                    <% } %>
                </tr>
                <%
                    boolean hayDatos = false;
                    while (rs.next()) {
                        hayDatos = true;
                %>
                    <tr>
                        <% for (int i = 1; i <= cols; i++) { %>
                            <td><%= rs.getString(i) %></td>
                        <% } %>
                    </tr>
                <% } %>
            </table>

            <% if (!hayDatos) { %>
                <p class="note">No se encontraron registros para esta consulta.</p>
            <% } %>

<%
        } catch (Exception e) {
%>
            <p class="error">Error en la consulta: <%= e.getMessage() %></p>
<%
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignore) {}
            try { if (ps != null) ps.close(); } catch (Exception ignore) {}
            try { if (st != null) st.close(); } catch (Exception ignore) {}
            try { if (con != null) con.close(); } catch (Exception ignore) {}
        }
    }
%>

<p class="back"><a href="menu">⬅ Volver al menú</a></p>

</body>
</html>
