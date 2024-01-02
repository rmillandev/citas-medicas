package servlet.administrador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.LogicaSede;

@WebServlet(name = "SedeEliminarSv", urlPatterns = {"/SedeEliminarSv"})
public class SedeEliminarSv extends HttpServlet {

    LogicaSede logicaSede = new LogicaSede();

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

            logicaSede.eliminarSede(id);

            String mensaje = "La sede se elimino correctamente";
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("/SedeSv");
        } catch (Exception ex) {
            String mensajeError = "Hubo un error al eliminar la sede" + ex.getMessage();
            request.getSession().setAttribute("error", mensajeError);
            response.sendRedirect("/SedeSv");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
