package servlet.administrador;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.LogicaEspecialidad;
import modelo.Especialidad;

@WebServlet(name = "EspecialidadSv", urlPatterns = {"/EspecialidadSv"})
public class EspecialidadSv extends HttpServlet {

    LogicaEspecialidad logicaEspecialidad = new LogicaEspecialidad();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Especialidad> listaEspecialidad = logicaEspecialidad.obtenerEspecialidades();

        HttpSession session = request.getSession();
        session.setAttribute("listaEspecialidad", listaEspecialidad);

        response.sendRedirect("/admin/especialidad.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String especialidad = request.getParameter("txtNombreEspecialidad");

        try {
            logicaEspecialidad.crearEspecialidad(especialidad);
            String mensaje = "La especialidad se guardó correctamente";
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("/EspecialidadSv"); // Redirige al servlet SvEspecialidad

        } catch (Exception ex) {
            String mensajeError = "Hubo un error al guardar la especialidad" + ex.getMessage();
            request.getSession().setAttribute("error", mensajeError);
            response.sendRedirect("/EspecialidadSv");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
