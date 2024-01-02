package servlet.medico;

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

@WebServlet(name = "AtenderPacienteSv", urlPatterns = {"/AtenderPacienteSv"})
public class AtenderPacienteSv extends HttpServlet {

    LogicaCita logicaCita = new LogicaCita();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Cita cita = logicaCita.obtenerCitaPorId(id);

        HttpSession session = request.getSession();
        session.setAttribute("detalleCita", cita);

        response.sendRedirect("/medico/detalle-cita.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("idCita"));
        String observaciones = request.getParameter("txtObservaciones");
        String medicamentos = request.getParameter("txtMedicamentos");
        String instrucciones = request.getParameter("txtInstrucciones");
        long doc = Long.parseLong(request.getParameter("doc"));
        long docPaciente = Long.parseLong(request.getParameter("docPaciente"));

        try {
            logicaCita.atenderPaciente(id, observaciones, medicamentos, instrucciones, docPaciente, doc);
            
            String mensaje = "La cita finalizo exitosamente";
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("/CitasMedicoSv?documento=" + doc);
        } catch (Exception ex) {
            String mensajeError = "Hubo un error al finalizar la cita: " + ex.getMessage();
            request.getSession().setAttribute("error", mensajeError);
            response.sendRedirect("/CitasMedicoSv?documento=" + doc);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
