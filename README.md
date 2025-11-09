# Evidencia BC - Felipe Sena

Aplicación web Java (WAR) con Servlets + JSP (Jakarta) y JDBC (SQLite). Proyecto listo para ejecutarse localmente con Jetty 11.

## Requisitos
- Java 17
- Maven 3.9+

## Ejecutar en local (recomendado: Jetty 11)
Jetty 11 usa Jakarta Servlet 5 y funciona bien con Java 17.

- Compilar y ejecutar sin limpiar (evita bloqueos en Windows):
```bash
mvn -U -Dmaven.clean.skip=true package jetty:run
```
- Accede en el navegador:
  - http://localhost:8080/
  - http://localhost:8080/form?nombre=Ana&correo=ana%40mail.com

## Estructura principal
```
src/
  main/
    java/
      evidencia/felipe/sena/web/
        FormularioServlet.java   # Servlet con GET y POST
    resources/
      application.properties
      schema.sql
    webapp/
      index.jsp                  # Formulario (GET/POST)
      WEB-INF/
        web.xml                  # Descriptor Jakarta 5.0
        views/
          resultado.jsp          # Muestra datos con JSTL
```

## Endpoints
- `GET /` → `index.jsp` (formularios GET/POST)
- `GET /form?nombre=..&correo=..` → muestra `resultado.jsp`
- `POST /form` → procesa formulario y muestra `resultado.jsp`

## Dependencias clave
- Jakarta Servlet API 5.0 (scope provided)
- Jakarta JSP API 3.0 (scope provided)
- JSTL Jakarta 2.0
- SQLite JDBC

## Notas sobre ejecución con Tomcat (opcional via Cargo)
Este proyecto incluye configuración de Cargo para Tomcat 10, pero en Windows puede generar bloqueos de archivos dentro de `target/cargo/*` que impiden `mvn clean`.

- Ejecutar:
```bash
mvn -U package cargo:run
```
- Detener:
```bash
mvn cargo:stop
```
- Si `mvn clean` falla con error de borrado de `target/cargo/.../*.jar`:
  - Asegúrate de detener el contenedor (`mvn cargo:stop`).
  - Cierra el Explorador de archivos en `target/`.
  - Borra manualmente `target/cargo/installs/`.
  - Luego sí: `mvn clean`.

## Problemas comunes
- 404 en `/`: verifica que Jetty esté corriendo y que accedes a `http://localhost:8080/`.
- JSP no renderiza: asegúrate de usar Jetty 11 (Servlet 5) y `web.xml` con esquema 5.0.
- `mvn clean` falla en Windows: ejecuta sin `clean` y limpia después de detener el contenedor.

## Versionamiento
El proyecto está preparado para control de versiones (Git). Recomendado:
```bash
git init
git add .
git commit -m "feat: webapp WAR con Servlets/JSP y Jetty 11"
```

## Licencia
Uso académico.
