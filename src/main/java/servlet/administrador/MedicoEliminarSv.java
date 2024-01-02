package servlet.administrador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.LogicaMedico;

@WebServlet(name = "MedicoEliminarSv", urlPatterns = {"/MedicoEliminarSv"})
public class MedicoEliminarSv extends HttpServlet {

    LogicaMedico logicaMedico = new LogicaMedico();

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

            logicaMedico.eliminarMedico(id);

            String mensaje = "El medico se elimino correctamente";
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("/MedicoSv");
        } catch (Exception ex) {
            String mensajeError = "Hubo un error al eliminar el medico" + ex.getMessage();
            request.getSession().setAttribute("error", mensajeError);
            response.sendRedirect("/MedicoSv");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
