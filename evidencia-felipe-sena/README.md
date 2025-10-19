# Evidencia JDBC - Felipe Sena

Proyecto Java (Maven) con JDBC y CRUD completo, siguiendo estándares de codificación y versionamiento.

## Tecnologías
- Java 17
- Maven
- JDBC (SQLite)

## Estructura
- `src/main/java/evidencia/felipe/sena/config/ConnectionManager.java`: gestión de conexiones JDBC.
- `src/main/java/evidencia/felipe/sena/model/Usuario.java`: entidad de dominio.
- `src/main/java/evidencia/felipe/sena/dao/UsuarioDao.java`: interfaz DAO.
- `src/main/java/evidencia/felipe/sena/dao/jdbc/UsuarioJdbcDao.java`: implementación JDBC del DAO.
- `src/main/java/evidencia/felipe/sena/service/UsuarioService.java`: capa de negocio.
- `src/main/java/evidencia/felipe/sena/app/Main.java`: app de consola con menú CRUD.
- `src/main/resources/application.properties`: configuración JDBC.
- `src/main/resources/schema.sql`: esquema de BD.

## Configuración
- Por defecto usa SQLite en `jdbc:sqlite:./data/evidencia.db`.
- Puedes cambiar `jdbc.url` en `application.properties` para MySQL o PostgreSQL.

Ejemplos:
- MySQL: `jdbc.url=jdbc:mysql://localhost:3306/mi_base?user=root&password=pass`
- PostgreSQL: `jdbc.url=jdbc:postgresql://localhost:5432/mi_base`
  - Y setear `jdbc.username`, `jdbc.password` si aplica.

## Uso
```bash
# Compilar y empacar
mvn -q -e -DskipTests package

# Ejecutar
java -cp target/evidencia-felipe-sena-1.0.0.jar;target/lib/* evidencia.felipe.sena.app.Main
```
En Linux/Mac usa `:` en lugar de `;` para el classpath.

Al iniciar, se aplica `schema.sql` para crear la tabla `usuario` si no existe.

## Estándares de codificación
- Paquetes: `evidencia.felipe.sena.*`
- Clases: PascalCase (`UsuarioService`)
- Métodos/variables: camelCase (`crearUsuario`, `nombreCompleto`)
- Constantes: `UPPER_SNAKE_CASE`
- Patrón DAO (Interfaz + Implementación JDBC)

## Criterios de evaluación
- [x] Conexión a BD con JDBC (`ConnectionManager` + SQLite).
- [x] CRUD completo (`UsuarioJdbcDao`, `UsuarioService`, `Main`).
- [x] Versionamiento (usar Git y commits por hito).
- [x] Estándar de codificación y capas.

## Git (sugerido)
```bash
git init
git add .
git commit -m "chore: scaffold maven project with JDBC and CRUD"
```

## Notas
- Si cambias a MySQL/PostgreSQL debes agregar los drivers JDBC en `pom.xml`.
- SQLite crea el archivo de base automáticamente en `./data/evidencia.db`.
