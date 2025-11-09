package evidencia.felipe.sena.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormularioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");
        String correo = req.getParameter("correo");

        if ((nombre != null && !nombre.isBlank()) || (correo != null && !correo.isBlank())) {
            req.setAttribute("metodo", "GET");
            req.setAttribute("nombre", nombre);
            req.setAttribute("correo", correo);
            req.getRequestDispatcher("/WEB-INF/views/resultado.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");
        String correo = req.getParameter("correo");

        req.setAttribute("metodo", "POST");
        req.setAttribute("nombre", nombre);
        req.setAttribute("correo", correo);

        req.getRequestDispatcher("/WEB-INF/views/resultado.jsp").forward(req, resp);
    }
}
