<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="dto.SucursalView" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Restaurant</title>
    <link rel="stylesheet" href="/estilos.css">
</head>

<body>
<ul>
    <li><a href="mesas-disponibles">Mesas Disponibles</a></li>
    <li><a href="mesas-ocupadas">Mesas Ocupadas</a></li>
    <li><a href="mesa-abrir">Abrir Mesa</a></li>
    <li><a href="mesa-reservar">Reservar Mesa</a></li>
</ul>
<form action="mesa-abrir" method="post">
    <table>
        <tr>
            <td>Sucursal</td>
            <td>
                <select name="sucursal" id="sucursal">
                    <%
                        List<SucursalView> sucursales = (List<SucursalView>) request.getAttribute("sucursales");

                        for (SucursalView sucursal : sucursales) {
                    %>
                    <option value="<%=sucursal.getNombre()%>"><%=sucursal.getNombre()%>
                    </option>
                    <%
                        }
                    %>
                </select>
        </tr>
        <tr>
            <td>Cantidad de Personas</td>
            <td>
                <input name="cantidad_de_personas" type="text">
            </td>
        </tr>
        <tr>
            <td>Empleado</td>
            <td>
                <input name="empleado_id" type="text">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="right"><input type="submit" value="Abrir"/></td>
        </tr>
    </table>
</form>
</body>
</html>