<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="dto.MesaOcupacionView" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.MesaView" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Restaurant</title>
    <link rel="stylesheet" href="/estilos.css">
    <script type="application/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>

<body>
<ul>
    <li><a href="mesas-disponibles">Mesas Disponibles</a></li>
    <li><a href="mesas-ocupadas">Mesas Ocupadas</a></li>
    <li><a href="mesa-abrir">Abrir Mesa</a></li>
    <li><a href="mesa-reservar">Reservar Mesa</a></li>
</ul>
<div id="section-mesas-ocupadas">
    <h3>Mesas Ocupadas</h3>
    <table>
        <tr>
            <th>Mesas</th>
            <th>Sector Salón</th>
            <th>Productos</th>
            <th>Cerrar</th>
        </tr>
        <%
            List<MesaOcupacionView> mesas = (List<MesaOcupacionView>) request.getAttribute("mesas");

            for (MesaOcupacionView mesa : mesas) {
                String mesasAsignadas = "";
                String sectorSalon = "";
                for (MesaView m : mesa.getMesaItems()) {
                    if (mesasAsignadas.length() > 0)
                        mesasAsignadas += ", ";
                    mesasAsignadas += m.getNumero();
                    sectorSalon = m.getSectorSalon().getNombre();
                }
        %>

        <tr>
            <td>
                <%=mesasAsignadas%>
            </td>
            <td>
                <%=sectorSalon%>
            </td>
            <td>
                <button id="btn-agregar-producto-mesa" mesa-ocupacion-id="<%=mesa.getId()%>">Agregar</button>
            </td>
            <td>
                <button id="btn-cerrar-mesa" mesa-ocupacion-id="<%=mesa.getId()%>">Cerrar</button>
            </td>
        </tr>

        <%
            }
        %>
    </table>
</div>

<br>

<div id="section-agregar-producto-mesa" style="display: none;">
    <h4>Agregar productos a mesa</h4>
    <form action="mesa-agregar-producto" method="post">
        <table>
            <tr style="display: none">
                <td>Mesa Ocupación ID</td>
                <td>
                    <input name="mesa_ocupacion_id" type="text">
                </td>
            </tr>
            <tr>
                <td>Producto</td>
                <td>
                    <input name="producto_id" type="text">
                </td>
            </tr>
            <tr>
                <td>Cantidad</td>
                <td>
                    <input name="cantidad_producto" type="text">
                </td>
            </tr>
            <tr>
                <td><input type="button" class="btn-cancelar" value="Cancelar"/></td>
                <td><input type="submit" value="Agregar"/></td>
            </tr>
        </table>
    </form>
</div>

<div id="section-cerrar-mesa" style="display: none;">
    <h4>Cerrar mesa</h4>
    <form action="mesa-cerrar" method="post">
        <table>
            <tr style="display: none">
                <td>Mesa Ocupación ID</td>
                <td>
                    <input name="mesa_ocupacion_id" type="text">
                </td>
            </tr>
            <tr>
                <td>Total a pagar</td>
                <td>
                    <label>$X,XX</label>
                </td>
            </tr>
            <tr>
                <td>Forma de pago</td>
                <td>
                    <input name="forma_de_pago_id" type="text">
                </td>
            </tr>
            <tr>
                <td><input type="button" class="btn-cancelar" value="Cancelar"/></td>
                <td><input type="submit" value="Cerrar"/></td>
            </tr>
        </table>
    </form>
</div>

<script>
    $(function () {
        $('#btn-agregar-producto-mesa').click(function () {
            $('#section-mesas-ocupadas').hide();
            $('#section-agregar-producto-mesa').show();
            var mesaOcupacionId = $(this).attr('mesa-ocupacion-id');
            $('#section-agregar-producto-mesa input[name=mesa_ocupacion_id]').val(mesaOcupacionId);
        });

        $('#btn-cerrar-mesa').click(function () {
            $('#section-mesas-ocupadas').hide();
            $('#section-cerrar-mesa').show();
            var mesaOcupacionId = $(this).attr('mesa-ocupacion-id');
            $('#section-cerrar-mesa input[name=mesa_ocupacion_id]').val(mesaOcupacionId);
        });

        $('.btn-cancelar').click(function () {
            $('#section-agregar-producto-mesa').hide();
            $('#section-cerrar-mesa').hide();
            $('#section-mesas-ocupadas').show();
        });
    });
</script>

</body>
</html>