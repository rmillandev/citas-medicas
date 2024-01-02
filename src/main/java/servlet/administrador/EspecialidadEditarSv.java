package servlet.administrador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.LogicaEspecialidad;
import modelo.Especialidad;


@WebServlet(name = "EspecialidadEditarSv", urlPatterns = {"/EspecialidadEditarSv"})
public class EspecialidadEditarSv extends HttpServlet {

    LogicaEspecialidad logicaEspecialidad = new LogicaEspecialidad();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        
        Especialidad especialidad = logicaEspecialidad.obtenerEspecialidadPorId(id);
        
        HttpSession session = request.getSession();
        session.setAttribute("especialidadEditar", especialidad);
        
        response.sendRedirect("/admin/editarEspecialidad.jsp");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombreEspecialidad = request.getParameter("txtNombreEspecialidad");
        
        Especialidad especialidad = (Especialidad) request.getSession().getAttribute("especialidadEditar");
        especialidad.setNombre(nombreEspecialidad);
        
        logicaEspecialidad.editarEspecialidad(especialidad);
        
        response.sendRedirect("/EspecialidadSv");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
