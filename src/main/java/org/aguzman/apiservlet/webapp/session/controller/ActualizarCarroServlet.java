package org.aguzman.apiservlet.webapp.session.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.aguzman.apiservlet.webapp.session.model.Carro;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/carro/actualizar")
public class ActualizarCarroServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session.getAttribute("carro")!= null){
            Carro carro = (Carro) session.getAttribute("carro");
            updateProductos(request, carro);
            updateCantidades(request, carro);
        }

        response.sendRedirect(request.getContextPath()+"/carro/ver");
    }

    private void updateProductos(HttpServletRequest request, Carro carro){
        String [] deleteIds = request.getParameterValues("deleteProductos");
        if (deleteIds != null && deleteIds.length > 0){
            List<String> productoIds = Arrays.asList(deleteIds);
            carro.removeProductos(productoIds);
        }
    }

    private void updateCantidades(HttpServletRequest request, Carro carro){
        // Iteramos a través de los parámetros y buscamos los que empiezan con
        // "cant_". El campo cant en la vista fueron nombrados "cant_" + productoId.
        // Obtenemos el id de cada producto y su correspondiente cantidad ;-).
        Enumeration<String> enumere = request.getParameterNames();
        while (enumere.hasMoreElements()){
            String paramName = enumere.nextElement();
            if (paramName.startsWith("cant_")){
                String id = paramName.substring(5);
                String cantidad = request.getParameter(paramName);
                if (cantidad != null){
                    carro.updateCantidad(id, Integer.parseInt(cantidad));
                }
            }
        }

    }
}
