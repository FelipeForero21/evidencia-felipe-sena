# Evidencia JDBC - Felipe Sena

Proyecto Java, CRUD básico sobre la entidad .

## Requisitos
- Java JDK 17 o superior (probado con JDK 21).
- Maven instalado y en PATH.

Verifica:
```powershell
java -version
mvn -v
```

## Obtener el proyecto
- Clona tu repo o descarga el ZIP.
- Abre una terminal en el directorio del proyecto: `evidencia-felipe-sena/`

## Configuración (opcional)
Por defecto usa SQLite y crea la base en `./data/evidencia.db`.
Puedes ajustar `src/main/resources/application.properties`:
```properties
jdbc.url=jdbc:sqlite:./data/evidencia.db
jdbc.username=
jdbc.password=
```

## Compilar
```powershell
mvn -q -DskipTests package
```

## Ejecutar (Windows PowerShell)
El classpath debe ir entre comillas por el separador `;`:
```powershell
java -cp "target\evidencia-felipe-sena-1.0.0.jar;target\lib\*" evidencia.felipe.sena.app.Main
```

## Uso (menú CRUD)
- 1) Crear usuario (nombre, email)
- 2) Consultar por ID
- 3) Listar usuarios
- 4) Actualizar usuario
- 5) Eliminar usuario
- 0) Salir

La tabla `usuario` se crea automáticamente al iniciar.

## Notas
- Los avisos SLF4J pueden ignorarse.
- La carpeta `data/` está en `.gitignore` y no se sube al repositorio.
