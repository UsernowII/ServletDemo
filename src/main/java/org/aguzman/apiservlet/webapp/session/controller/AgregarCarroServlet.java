package org.aguzman.apiservlet.webapp.session.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.aguzman.apiservlet.webapp.session.model.Carro;
import org.aguzman.apiservlet.webapp.session.model.ItemCarro;
import org.aguzman.apiservlet.webapp.session.model.Producto;
import org.aguzman.apiservlet.webapp.session.services.ProductoService;
import org.aguzman.apiservlet.webapp.session.services.ProductoServiceImpl;
import org.aguzman.apiservlet.webapp.session.services.ProductoServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id")) ;
        //Obtenemos la connexion en el request del filter
        Connection conn = (Connection)req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImpl(conn);
        Optional <Producto> producto = service.findById(id);
        if (producto.isPresent()){
            ItemCarro itemCarro = new ItemCarro(1, producto.get());
            HttpSession session = req.getSession();
            Carro carro = (Carro) session.getAttribute("carro");
            carro.addItemCarro(itemCarro);
        }

        resp.sendRedirect(req.getContextPath() + "/carro/ver");

    }
}
