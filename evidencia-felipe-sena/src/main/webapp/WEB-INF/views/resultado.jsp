<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Resultado</title>
  <style>
    body { font-family: system-ui, Arial, sans-serif; margin: 2rem; }
    .card { border: 1px solid #e5e7eb; border-radius: 8px; padding: 1rem; max-width: 480px; }
    a { color: #2563eb; }
    dt { font-weight: 600; }
    dd { margin: 0 0 0.5rem 0; }
  </style>
</head>
<body>
  <h1>Datos recibidos</h1>

  <div class="card">
    <dl>
      <dt>Método</dt>
      <dd><c:out value="${metodo}" default="POST"/></dd>
      <dt>Nombre</dt>
      <dd><c:out value="${nombre}"/></dd>
      <dt>Correo</dt>
      <dd><c:out value="${correo}"/></dd>
    </dl>
  </div>

  <p>
    <a href="<c:url value='/'/>">Volver al formulario</a>
  </p>
</body>
</html>
