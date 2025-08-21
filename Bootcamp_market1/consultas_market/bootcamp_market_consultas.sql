--ejercicio1
SELECT c.id, c.nombre, c.apellido, COUNT(f.id) AS total_facturas
FROM cliente c
JOIN factura f ON c.id = f.cliente_id
GROUP BY c.id, c.nombre, c.apellido
ORDER BY total_facturas DESC;
--ejercicio 2
SELECT c.id, c.nombre, c.apellido,
       SUM(fd.cantidad * p.precio) AS total_gastado
FROM cliente c
JOIN factura f ON c.id = f.cliente_id
JOIN factura_detalle fd ON f.id = fd.factura_id
JOIN producto p ON fd.producto_id = p.id
GROUP BY c.id, c.nombre, c.apellido
ORDER BY total_gastado DESC;
--ejercicio 3
SELECT m.id, m.nombre, COUNT(f.id) AS cantidad_usos
FROM moneda m
JOIN factura f ON m.id = f.moneda_id
GROUP BY m.id, m.nombre
ORDER BY cantidad_usos DESC;
--ejercicio 4
SELECT pr.id, pr.nombre, COUNT(p.id) AS productos_suministrados
FROM proveedor pr
JOIN producto p ON pr.id = p.proveedor_id
GROUP BY pr.id, pr.nombre
ORDER BY productos_suministrados DESC;
--ejercicio 5
SELECT p.id, p.nombre, SUM(fd.cantidad) AS total_vendido
FROM producto p
JOIN factura_detalle fd ON p.id = fd.producto_id
GROUP BY p.id, p.nombre
ORDER BY total_vendido DESC;
--ejercicio 6
SELECT p.id, p.nombre, SUM(fd.cantidad) AS total_vendido
FROM producto p
JOIN factura_detalle fd ON p.id = fd.producto_id
GROUP BY p.id, p.nombre
ORDER BY total_vendido ASC;
--ejercicio 7
SELECT f.fecha_emision, c.nombre, c.apellido,
       p.nombre AS producto, fd.cantidad,
       ft.nombre AS tipo_factura
FROM factura f
JOIN cliente c ON f.cliente_id = c.id
JOIN factura_detalle fd ON f.id = fd.factura_id
JOIN producto p ON fd.producto_id = p.id
JOIN factura_tipo ft ON f.factura_tipo_id = ft.id
WHERE f.id = 1; -- cambiar el 1 por el id de la factura que quieras consultar
--ejercicio 8
SELECT f.id,
       SUM(fd.cantidad * p.precio) AS total_factura
FROM factura f
JOIN factura_detalle fd ON f.id = fd.factura_id
JOIN producto p ON fd.producto_id = p.id
GROUP BY f.id
ORDER BY total_factura DESC;
--ejercicio 9
SELECT f.id,
       SUM(fd.cantidad * p.precio) AS total_factura,
       SUM(fd.cantidad * p.precio) * 0.10 AS iva_10
FROM factura f
JOIN factura_detalle fd ON f.id = fd.factura_id
JOIN producto p ON fd.producto_id = p.id
GROUP BY f.id;
