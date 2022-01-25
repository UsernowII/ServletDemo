<%@page contentType="text/html;" pageEncoding="UTF-8"  %>
<%@page import="org.aguzman.apiservlet.webapp.session.model.*" %>
<%@page import="java.util.*"%>

<%
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
    Optional<Producto> username = (Optional<Producto>) request.getAttribute("username");
    String mensajeRequest = (String) request.getAttribute("mensaje");
    String mensajeApp = (String) request.getServletContext().getAttribute("mensaje");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Lista de Productos</title>
</head>
<body>
<h1>Lista de productos</h1>
<% if (username.isPresent()){%>
<div>hola, Bienvenido <%=username.get()%></div>
    <p><a href="<%=request.getContextPath()%>/productos/form">Crear [+]</a></p>
<%}%>
<table>
    <tr>

        <th>Id</th>
        <th>Nombre</th>
        <th>Categoría</th>
        <% if (username.isPresent()){%>
        <th>Precio</th>
        <th>Agregar</th>
        <th>Editar</th>
        <th>Eliminar</th>
        <%}%>
    </tr>
    <% for (Producto p: productos) {%>
    <tr>
        <td><%=p.getId()%></td>
        <td><%=p.getNombre()%></td>
        <td><%=p.getCategoria().getNombre()%></td>
        <% if (username.isPresent()){%>
        <td><%=p.getPrecio()%></td>
        <td><a href="<%=request.getContextPath()%>/carro/agregar?id=<%=p.getId()%> ">Agregar al carro</a></td>
        <td><a href="<%=request.getContextPath()%>/productos/form?id=<%=p.getId()%> ">Editar</a></td>
        <td><a onclick="return confirm('Esta seguro que desea eliminar?');"
                href="<%=request.getContextPath()%>/productos/eliminar?id=<%=p.getId()%> ">Eliminar</a></td>
        <%}%>
    </tr>
    <%}%>
</table>

<p><%=mensajeApp%></p>
<p><%=mensajeRequest%></p>

</body>
</html>