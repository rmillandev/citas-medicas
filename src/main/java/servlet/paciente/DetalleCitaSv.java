package servlet.paciente;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.LogicaCita;
import modelo.Cita;

@WebServlet(name = "DetalleCitaSv", urlPatterns = {"/DetalleCitaSv"})
public class DetalleCitaSv extends HttpServlet {

    LogicaCita logicaCita = new LogicaCita();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("idCita"));
        Cita detalle = logicaCita.obtenerCitaPorId(id);
        
        HttpSession session = request.getSession();
        session.setAttribute("detalleCita", detalle);

        response.sendRedirect("/paciente/detalle-cita.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String observaciones = request.getParameter("txtObservaciones");
        long doc = Long.parseLong(request.getParameter("doc"));

        try {
            logicaCita.cancelarCita(id, observaciones);
            
            String mensaje = "La cita se cancelo correctamente";
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("/CitasProgramadasSv?documento="+doc);
        } catch (Exception ex) {
            String mensajeError = "Hubo un error al cancelar la cita: " + ex.getMessage();
            request.getSession().setAttribute("error", mensajeError);
            response.sendRedirect("/CitasProgramadasSv?documento="+doc);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
