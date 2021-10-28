package org.aguzman.webapp2.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/parametros/url-get")
public class ParametrosGetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String saludo = request.getParameter("saludo");
        String nombre = request.getParameter("nombre");

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("    <head>");
        out.println("        <meta charset=\"UTF-8\">");
        out.println("        <title>Parametros Get de la Url</title>");
        out.println("    </head>");
        out.println("    <body>");
        out.println("        <h1>Parametros Get de la Url</h1>");
        if(saludo != null && nombre != null){
            out.println("        <h2>El saludo enviado es: "+ saludo + " " + nombre + "<h/2");
        }else if(saludo != null) {
            out.println("        <h2>El saludo enviado es: " + saludo + "<h/2");
        }else if(nombre != null){
            out.println("        <h2>Hola mi nombre es: " + nombre + "<h/2");
        }else{
            out.println("        <h2>No se han enviado los parametros saludos ni nombre<h/2");
        }
        out.println("<br>");
        try{
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            out.println("<h3>El codigo enviado es: "+ codigo+"</h3>");
        }catch (NumberFormatException e){
            out.println("<h3>El codigo no se ha enviado es null</h3>");
        }
        out.println("    </body>");
        out.println("</html>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
