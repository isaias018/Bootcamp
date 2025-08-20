package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        try (Connection conn = Conexion.get();
             Statement stmt = conn.createStatement();
             Scanner sc = new Scanner(System.in)) {

            boolean salir = false;

            while (!salir) {
                System.out.println("\n=== MENÚ DE CONSULTAS ===");
                System.out.println("1 - Top clientes con más facturas");
                System.out.println("2 - Top clientes que más gastaron");
                System.out.println("3 - Top monedas más utilizadas");
                System.out.println("4 - Top proveedores de productos");
                System.out.println("5 - Productos más vendidos");
                System.out.println("6 - Productos menos vendidos");
                System.out.println("7 - Detalle de factura específica");
                System.out.println("8 - Montos de facturas ordenadas");
                System.out.println("9 - IVA 10% de cada factura");
                System.out.println("0 - Salir");
                System.out.print("Ingrese la opción deseada: ");

                int opcion = sc.nextInt();
                sc.nextLine(); // limpiar buffer

              switch (opcion) {
    case 1:
        ResultSet rs1 = stmt.executeQuery(
            "SELECT c.nombre, c.apellido, COUNT(f.id) AS total_facturas " +
            "FROM cliente c " +
            "JOIN factura f ON c.id = f.cliente_id " +
            "GROUP BY c.id " +
            "ORDER BY total_facturas DESC " +
            "LIMIT 5");
        System.out.printf("%-20s %-20s %-10s%n", "Nombre", "Apellido", "Facturas");
        while (rs1.next()) {
            System.out.printf("%-20s %-20s %-10d%n",
                    rs1.getString("nombre"),
                    rs1.getString("apellido"),
                    rs1.getInt("total_facturas"));
        }
        break;

    case 2:
        ResultSet rs2 = stmt.executeQuery(
            "SELECT c.nombre, c.apellido, SUM(p.precio * fd.cantidad) AS total_gastado " +
            "FROM cliente c " +
            "JOIN factura f ON c.id = f.cliente_id " +
            "JOIN factura_detalle fd ON f.id = fd.factura_id " +
            "JOIN producto p ON fd.producto_id = p.id " +
            "GROUP BY c.id " +
            "ORDER BY total_gastado DESC " +
            "LIMIT 5");
        System.out.printf("%-20s %-20s %-15s%n", "Nombre", "Apellido", "Total gastado");
        while (rs2.next()) {
            System.out.printf("%-20s %-20s $%-15.2f%n",
                    rs2.getString("nombre"),
                    rs2.getString("apellido"),
                    rs2.getDouble("total_gastado"));
        }
        break;

    case 3:
        ResultSet rs3 = stmt.executeQuery(
            "SELECT m.nombre, COUNT(f.id) AS total_usos " +
            "FROM moneda m " +
            "JOIN factura f ON m.id = f.moneda_id " +
            "GROUP BY m.id " +
            "ORDER BY total_usos DESC");
        System.out.printf("%-20s %-10s%n", "Moneda", "Usos");
        while (rs3.next()) {
            System.out.printf("%-20s %-10d%n",
                    rs3.getString("nombre"),
                    rs3.getInt("total_usos"));
        }
        break;

    case 4:
        ResultSet rs4 = stmt.executeQuery(
            "SELECT pr.nombre, SUM(fd.cantidad) AS total_vendido " +
            "FROM proveedor pr " +
            "JOIN producto p ON pr.id = p.proveedor_id " +
            "JOIN factura_detalle fd ON p.id = fd.producto_id " +
            "GROUP BY pr.id " +
            "ORDER BY total_vendido DESC " +
            "LIMIT 5");
        System.out.printf("%-30s %-10s%n", "Proveedor", "Unidades vendidas");
        while (rs4.next()) {
            System.out.printf("%-30s %-10d%n",
                    rs4.getString("nombre"),
                    rs4.getInt("total_vendido"));
        }
        break;

    case 5:
        ResultSet rs5 = stmt.executeQuery(
            "SELECT p.nombre, SUM(fd.cantidad) AS total_vendido " +
            "FROM producto p " +
            "JOIN factura_detalle fd ON p.id = fd.producto_id " +
            "GROUP BY p.id " +
            "ORDER BY total_vendido DESC " +
            "LIMIT 5");
        System.out.printf("%-30s %-10s%n", "Producto", "Unidades");
        while (rs5.next()) {
            System.out.printf("%-30s %-10d%n",
                    rs5.getString("nombre"),
                    rs5.getInt("total_vendido"));
        }
        break;

    case 6:
        ResultSet rs6 = stmt.executeQuery(
            "SELECT p.nombre, SUM(fd.cantidad) AS total_vendido " +
            "FROM producto p " +
            "JOIN factura_detalle fd ON p.id = fd.producto_id " +
            "GROUP BY p.id " +
            "ORDER BY total_vendido ASC " +
            "LIMIT 5");
        System.out.printf("%-30s %-10s%n", "Producto", "Unidades");
        while (rs6.next()) {
            System.out.printf("%-30s %-10d%n",
                    rs6.getString("nombre"),
                    rs6.getInt("total_vendido"));
        }
        break;

    case 7:
        System.out.print("Ingrese ID de factura: ");
        int facturaId = sc.nextInt();
        sc.nextLine(); // limpiar buffer
        ResultSet rs7 = stmt.executeQuery(
            "SELECT f.fecha_emision, c.nombre, c.apellido, p.nombre AS producto, fd.cantidad, ft.nombre AS tipo_factura " +
            "FROM factura f " +
            "JOIN cliente c ON f.cliente_id = c.id " +
            "JOIN factura_detalle fd ON f.id = fd.factura_id " +
            "JOIN producto p ON fd.producto_id = p.id " +
            "JOIN factura_tipo ft ON f.factura_tipo_id = ft.id " +
            "WHERE f.id = " + facturaId);
        System.out.printf("%-12s %-20s %-20s %-30s %-10s %-15s%n",
                "Fecha", "Nombre", "Apellido", "Producto", "Cantidad", "Tipo factura");
        while (rs7.next()) {
            System.out.printf("%-12s %-20s %-20s %-30s %-10d %-15s%n",
                    rs7.getDate("fecha_emision"),
                    rs7.getString("nombre"),
                    rs7.getString("apellido"),
                    rs7.getString("producto"),
                    rs7.getInt("cantidad"),
                    rs7.getString("tipo_factura"));
        }
        break;

    case 8:
        ResultSet rs8 = stmt.executeQuery(
            "SELECT f.id, SUM(p.precio * fd.cantidad) AS total_factura " +
            "FROM factura f " +
            "JOIN factura_detalle fd ON f.id = fd.factura_id " +
            "JOIN producto p ON fd.producto_id = p.id " +
            "GROUP BY f.id " +
            "ORDER BY total_factura DESC");
        System.out.printf("%-10s %-10s%n", "Factura ID", "Total");
        while (rs8.next()) {
            System.out.printf("%-10d $%-10.2f%n",
                    rs8.getInt("id"),
                    rs8.getDouble("total_factura"));
        }
        break;

    case 9:
        ResultSet rs9 = stmt.executeQuery(
            "SELECT f.id, SUM(p.precio * fd.cantidad) AS total_factura, " +
            "SUM(p.precio * fd.cantidad) * 0.10 AS iva_10 " +
            "FROM factura f " +
            "JOIN factura_detalle fd ON f.id = fd.factura_id " +
            "JOIN producto p ON fd.producto_id = p.id " +
            "GROUP BY f.id");
        System.out.printf("%-10s %-12s %-10s%n", "Factura ID", "Total", "IVA 10%");
        while (rs9.next()) {
            System.out.printf("%-10d $%-12.2f $%-10.2f%n",
                    rs9.getInt("id"),
                    rs9.getDouble("total_factura"),
                    rs9.getDouble("iva_10"));
        }
        break;

    case 0:
        salir = true;
        System.out.println("Saliendo del programa...");
        break;

    default:
        System.out.println("Opción inválida. Intente de nuevo.");
        break;
}

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
