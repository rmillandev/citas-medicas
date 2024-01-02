package servlet.administrador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.LogicaSede;
import modelo.Sede;


@WebServlet(name = "SedeSv", urlPatterns = {"/SedeSv"})
public class SedeSv extends HttpServlet {

    LogicaSede logicaSede = new LogicaSede();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Sede> listaSedes = logicaSede.obtenerSedes();
        
        HttpSession session = request.getSession();
        session.setAttribute("listaSedes", listaSedes);

        response.sendRedirect("/admin/sede.jsp");
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombreSede = request.getParameter("txtNombreSede");
        
        try {
            logicaSede.crearSede(nombreSede);
            String mensaje = "La sede se guardó correctamente";
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("/SedeSv");
        } catch (Exception ex) {
            String mensajeError = "Hubo un error al guardar la sede" + ex.getMessage();
            request.getSession().setAttribute("error", mensajeError);
            response.sendRedirect("/SedeSv");
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
