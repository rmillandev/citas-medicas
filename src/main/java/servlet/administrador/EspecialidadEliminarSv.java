package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.LogicaEspecialidad;

@WebServlet(name = "EspecialidadEliminarSv", urlPatterns = {"/EspecialidadEliminarSv"})
public class EspecialidadEliminarSv extends HttpServlet {

    LogicaEspecialidad logicaEspecialidad = new LogicaEspecialidad();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            logicaEspecialidad.eliminarEspecialidad(id);

            String mensaje = "La especialidad se elimino correctamente";
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("/EspecialidadSv");
        } catch (Exception ex) {
            String mensajeError = "Hubo un error al eliminar la especialidad" + ex.getMessage();
            request.getSession().setAttribute("error", mensajeError);
            response.sendRedirect("/EspecialidadSv");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
