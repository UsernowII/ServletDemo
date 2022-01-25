package org.aguzman.apiservlet.webapp.session.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aguzman.apiservlet.webapp.session.model.Producto;
import org.aguzman.apiservlet.webapp.session.services.ProductoService;
import org.aguzman.apiservlet.webapp.session.services.ProductoServiceJdbcImpl;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/productos/eliminar")
public class ProductoEliminarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImpl(conn);
        long id;
        try{
            id = Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0L;
        }

        if (id> 0){
            Optional<Producto> o = service.findById(id);
            if (o.isPresent()){
                service.delete(id);
                resp.sendRedirect(req.getContextPath()+"/productos");
            }else {
               resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el producto en la base de datos");
            }

        }else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "El id no se ha registrado");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
