package evidencia.felipe.sena.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Gestiona la creación de conexiones JDBC usando propiedades externas.
 */
public final class ConnectionManager {

    private static final String PROPERTIES_FILE = "/application.properties";
    private static final Properties PROPS = new Properties();

    static {
        try (InputStream in = ConnectionManager.class.getResourceAsStream(PROPERTIES_FILE)) {
            if (in == null) {
                throw new IllegalStateException("No se encontró el archivo de configuración: " + PROPERTIES_FILE);
            }
            PROPS.load(in);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Error cargando configuración JDBC: " + e.getMessage());
        }

        // Para SQLite no es necesario registrar driver manualmente.
    }

    private ConnectionManager() {}

    public static Connection getConnection() throws SQLException {
        String url = PROPS.getProperty("jdbc.url");
        String user = PROPS.getProperty("jdbc.username", "");
        String pass = PROPS.getProperty("jdbc.password", "");
        return DriverManager.getConnection(url, user, pass);
    }

    public static String getSchemaPath() {
        return PROPS.getProperty("schema.path", "/schema.sql");
    }
}
