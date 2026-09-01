package com.example.agenda.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.InputStream;
import java.util.Properties;

public class ConexionBD {

    private static String URL;
    private static String USER;
    private static String PASSWORD;

    // MODIFICACIÓN: Bloque estático para leer credenciales desde un archivo de configuración
    static {
        try (InputStream input = ConexionBD.class.getClassLoader().getResourceAsStream("database.properties")) {
            Properties prop = new Properties();
            if (input != null) {
                prop.load(input);
                URL = prop.getProperty("db.url");
                USER = prop.getProperty("db.user");
                PASSWORD = prop.getProperty("db.password");
            } else {
                // Fallback por defecto si no creas el archivo
                URL = "jdbc:mariadb://localhost:3306/agenda?allowPublicKeyRetrieval=true";
                USER = "usuario1";
                PASSWORD = "superpassword";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}