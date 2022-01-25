package org.aguzman.apiservlet.webapp.session.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String url = "jdbc:mysql://localhost:3306/java_curso?serverTimezone=America/Bogota";
    private static String username = "root";
    private static String password = "Admin_2021";



    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

}
