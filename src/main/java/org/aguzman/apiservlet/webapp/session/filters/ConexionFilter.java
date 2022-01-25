package org.aguzman.apiservlet.webapp.session.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.aguzman.apiservlet.webapp.session.services.ServiceJdbcException;
import org.aguzman.apiservlet.webapp.session.util.ConexionBD;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        try(Connection conn = ConexionBD.getConnection()){

            if (conn.getAutoCommit()){
                conn.setAutoCommit(false);

            }
            try{
                request.setAttribute("conn", conn);
                filterChain.doFilter(request, response);
                conn.commit();
            }catch (SQLException  | ServiceJdbcException e){
                conn.rollback();
                e.printStackTrace();
                ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }
}
