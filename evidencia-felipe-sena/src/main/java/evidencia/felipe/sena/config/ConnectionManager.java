package evidencia.felipe.sena.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        // Si es SQLite con ruta a archivo, aseguramos que exista el directorio
        if (url != null && url.startsWith("jdbc:sqlite:")) {
            String dbPath = url.substring("jdbc:sqlite:".length());
            // ignorar memoria
            if (!dbPath.equals(":memory:")) {
                // Normalizar prefijo ./ y similares
                Path p = Paths.get(dbPath.replaceFirst("^\\./", ""));
                Path parent = p.toAbsolutePath().getParent();
                if (parent != null && !Files.exists(parent)) {
                    try {
                        Files.createDirectories(parent);
                    } catch (IOException e) {
                        throw new SQLException("No se pudo crear el directorio de la BD: " + parent, e);
                    }
                }
            }
        }
        String user = PROPS.getProperty("jdbc.username", "");
        String pass = PROPS.getProperty("jdbc.password", "");
        return DriverManager.getConnection(url, user, pass);
    }

    public static String getSchemaPath() {
        return PROPS.getProperty("schema.path", "/schema.sql");
    }
}
