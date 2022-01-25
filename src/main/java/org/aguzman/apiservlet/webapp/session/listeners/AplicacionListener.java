package org.aguzman.apiservlet.webapp.session.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.aguzman.apiservlet.webapp.session.model.Carro;

@WebListener
public class AplicacionListener implements ServletContextListener ,
        ServletRequestListener, HttpSessionListener {

    // Para que exista en el contexto de la HttpSesion
    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("Inicializando la aplicación");
        servletContext = sce.getServletContext();
        servletContext.setAttribute("mensaje", "Algún valor Global de la app!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //sce.getServletContext().log("Destruyendo la aplicación");
        servletContext.log("Destruyendo la aplicación");

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        //sre.getServletContext().log("Inicializando el request");
        servletContext.log("Inicializando el request");
        sre.getServletRequest().setAttribute("mensaje", "Guardando algún valor para el request");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        //sre.getServletContext().log("Inicializando el request");
        servletContext.log("Destruyendo el request");
    }


    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("Inicializando la sesión Http");
        //Creamos la sesion carro de compras con el objeto carro
        Carro carro = new Carro();
        HttpSession httpSession = se.getSession();
        httpSession.setAttribute("carro", carro);

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("Destruyendo la sesión Http");

    }
}
