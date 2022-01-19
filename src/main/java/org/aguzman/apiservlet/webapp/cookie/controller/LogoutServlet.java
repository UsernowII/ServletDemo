package org.aguzman.apiservlet.webapp.cookie.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aguzman.apiservlet.webapp.cookie.services.LoginService;
import org.aguzman.apiservlet.webapp.cookie.services.LoginServiceImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceImpl();
        Optional <String> username = auth.getUsername(req);
        if (username.isPresent()){
            Cookie userNameCookie = new Cookie("username", "");
            userNameCookie.setMaxAge(0);
            resp.addCookie(userNameCookie);
        }

        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}
