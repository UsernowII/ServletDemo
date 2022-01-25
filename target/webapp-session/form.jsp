<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, org.aguzman.apiservlet.webapp.session.model.*"  %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<%
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
    Map<String, String> errrores = (Map<String, String>) request.getAttribute("errores");
    Producto producto = (Producto) request.getAttribute("producto");
    String fecha = producto.getFechaRegistro() != null ?
            producto.getFechaRegistro().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Formulario Producto</title>
</head>
<body>
    <h1>Formulario Producto</h1>

    <form action="<%=request.getContextPath()%>/productos/form" method="post">
        <div>
            <label for="nombre">Nombre</label>
                <div>
                    <input type="text" name="nombre" id="nombre"
                           value="<%=producto.getNombre() != null ? producto.getNombre() : ""%>">
                </div>
            <%if (errrores != null && errrores.containsKey("nombre")){%>
                <div style="color: coral"><%=errrores.get("nombre")%></div>
            <%}%>
        </div>
        <div>
            <label for="precio">Precio</label>
            <div>
                <input type="number" name="precio" id="precio"
                       value="<%=producto.getPrecio() >0 ? producto.getPrecio() : "" %>">
            </div>
            <%if (errrores != null && errrores.containsKey("precio")){%>
            <div style="color: coral"><%=errrores.get("precio")%></div>
            <%}%>
        </div>
        <div>
            <label for="sku">SKU</label>
            <div>
                <input type="text" name="sku" id="sku" value="<%=producto.getSku() != null ? producto.getSku() : ""%>">
            </div>
            <%if (errrores != null && errrores.containsKey("sku")){%>
            <div style="color: coral"><%=errrores.get("sku")%></div>
            <%}%>
        </div>
        <div>
            <label for="fecha_registro">Fecha de Registro</label>
            <div>
                <input type="date" name="fecha_registro" id="fecha_registro" value="<%=fecha%>">
            </div>
            <%if (errrores != null && errrores.containsKey("fecha_registro")){%>
            <div style="color: coral"><%=errrores.get("fecha_registro")%></div>
            <%}%>
        </div>
        <div>
            <label for="categoria"></label>
            <div>
                <select type="text" name="categoria" id="categoria">
                    <option value="">--Seleccionar--</option>
                    <% for (Categoria c : categorias){%>
                    <option value="<%=c.getId()%>"
                        <%=c.getId().equals(producto.getCategoria().getId()) ? "selected" : ""%>
                        ><%=c.getNombre()%></option>
                    <%}%>
                </select>
            </div>
            <%if (errrores != null && errrores.containsKey("categoria")){%>
            <div style="color: coral"><%=errrores.get("categoria")%></div>
            <%}%>
        </div>
        <div>
            <input type="submit" value="<%=(producto.getId() != null && producto.getId() >0) ? "Editar" : "Crear"%>">
        </div>
        <input type="hidden" name="id" value="<%=producto.getId()%>">
    </form>
</body>
</html>