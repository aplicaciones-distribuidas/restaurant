<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>AltaAlumno</title>
</head>

<body>
<form action="Controller">
    <table border="1">
        <tr>
            <td>Nombre</td>
            <td><input name="nombre" size="50"/></td>
        </tr>
        <tr>
            <td>Documento</td>
            <td><input name="documento" size="20"/></td>
        </tr>
        <tr>
            <td colspan="2" align="right"><input name="boton" type="submit" value="Aceptar"/></td>
        </tr>
    </table>
    <input type="hidden" name="opcion" value="ver">
</form>
</body>
</html>