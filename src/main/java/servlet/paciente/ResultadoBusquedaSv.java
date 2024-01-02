package servlet.paciente;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.LogicaCita;
import modelo.Cita;
import modelo.MedicoDTO;

@WebServlet(name = "ResultadoBusquedaSv", urlPatterns = {"/ResultadoBusquedaSv"})
public class ResultadoBusquedaSv extends HttpServlet {

    LogicaCita logicaCita = new LogicaCita();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("txtEspecialidad"));

        List<MedicoDTO> lista = logicaCita.obtenerResultadosBusqueda(id);

        HttpSession session = request.getSession();
        session.setAttribute("listaResultado", lista);

        response.sendRedirect("/paciente/asignacion-citas.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fecha = request.getParameter("fecha");
        String documentoPaciente = request.getParameter("idPaciente");
        String idMedico = request.getParameter("idMedico");

        try {
            logicaCita.agendarCita(fecha, documentoPaciente, idMedico);

            String mensaje = "La cita se agendó correctamente. Puede revisar sus citas programadas.";
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("/paciente/asignacion-citas.jsp");
        } catch (Exception ex) {
            String mensajeError = ex.getMessage();
            request.getSession().setAttribute("error", mensajeError);
            response.sendRedirect("/paciente/asignacion-citas.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
