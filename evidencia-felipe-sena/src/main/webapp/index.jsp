<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Formulario - Evidencia</title>
  <style>
    body { font-family: system-ui, Arial, sans-serif; margin: 2rem; }
    form { display: grid; gap: 1rem; max-width: 420px; }
    label { display: grid; gap: 0.25rem; }
    input, button { padding: 0.6rem 0.8rem; font-size: 1rem; }
    .row { display: grid; gap: 0.25rem; }
  </style>
</head>
<body>
  <h1>Registro simple</h1>
  <p>Este formulario envía datos al Servlet usando métodos GET y POST.</p>

  <h2>Enviar por GET</h2>
  <form action="form" method="get">
    <label>
      Nombre
      <input type="text" name="nombre" required />
    </label>
    <label>
      Correo
      <input type="email" name="correo" required />
    </label>
    <button type="submit">Enviar GET</button>
  </form>

  <h2>Enviar por POST</h2>
  <form action="form" method="post">
    <label>
      Nombre
      <input type="text" name="nombre" required />
    </label>
    <label>
      Correo
      <input type="email" name="correo" required />
    </label>
    <button type="submit">Enviar POST</button>
  </form>
</body>
</html>
