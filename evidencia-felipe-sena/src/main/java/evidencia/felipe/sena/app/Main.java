package evidencia.felipe.sena.app;

import evidencia.felipe.sena.config.ConnectionManager;
import evidencia.felipe.sena.model.Usuario;
import evidencia.felipe.sena.service.UsuarioService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final UsuarioService service = new UsuarioService();

    public static void main(String[] args) {
        System.out.println("=== Evidencia JDBC (Felipe Sena) ===");
        ensureSchema();
        runMenu();
    }

    private static void ensureSchema() {
        String schemaPath = ConnectionManager.getSchemaPath();
        try (Connection con = ConnectionManager.getConnection();
             Statement st = con.createStatement();
             InputStream in = Main.class.getResourceAsStream(schemaPath)) {
            if (in == null) {
                System.err.println("No se encontró el schema: " + schemaPath);
                return;
            }
            String sql = readAll(in);
            st.executeUpdate(sql);
        } catch (SQLException | IOException e) {
            System.err.println("Error creando esquema: " + e.getMessage());
        }
    }

    private static String readAll(InputStream in) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append('\n');
            }
            return sb.toString();
        }
    }

    private static void runMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("1) Crear usuario");
            System.out.println("2) Consultar usuario por ID");
            System.out.println("3) Listar usuarios");
            System.out.println("4) Actualizar usuario");
            System.out.println("5) Eliminar usuario");
            System.out.println("0) Salir");
            System.out.print("Seleccione opción: ");
            String op = sc.nextLine();
            try {
                switch (op) {
                    case "1": crear(sc); break;
                    case "2": consultar(sc); break;
                    case "3": listar(); break;
                    case "4": actualizar(sc); break;
                    case "5": eliminar(sc); break;
                    case "0": System.out.println("Adiós."); return;
                    default: System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private static void crear(Scanner sc) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Email: ");
        String email = sc.nextLine().trim();
        Usuario u = service.crearUsuario(nombre, email);
        System.out.println("Creado: " + u);
    }

    private static void consultar(Scanner sc) {
        System.out.print("ID: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        Optional<Usuario> u = service.obtenerUsuario(id);
        System.out.println(u.map(Object::toString).orElse("No encontrado"));
    }

    private static void listar() {
        List<Usuario> list = service.listarUsuarios();
        if (list.isEmpty()) {
            System.out.println("Sin registros.");
        } else {
            list.forEach(System.out::println);
        }
    }

    private static void actualizar(Scanner sc) {
        System.out.print("ID: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Nuevo email: ");
        String email = sc.nextLine().trim();
        boolean ok = service.actualizarUsuario(id, nombre, email);
        System.out.println(ok ? "Actualizado" : "No se actualizó (ID inexistente)" );
    }

    private static void eliminar(Scanner sc) {
        System.out.print("ID: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        boolean ok = service.eliminarUsuario(id);
        System.out.println(ok ? "Eliminado" : "No se eliminó (ID inexistente)" );
    }
}
