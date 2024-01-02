package servlet.paciente;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.LogicaCita;
import modelo.Cita;

@WebServlet(name = "CitasProgramadasSv", urlPatterns = {"/CitasProgramadasSv"})
public class CitasProgramadasSv extends HttpServlet {

    LogicaCita logicaCita = new LogicaCita();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long documento = Long.parseLong(request.getParameter("documento"));

        int id = logicaCita.pacienteId(documento);
        
        if (id != 0) {
            List<Cita> lista = logicaCita.obtenerCitasPorIdPaciente(id);
            if (lista != null) {
                HttpSession session = request.getSession();
                session.setAttribute("listaCitasProgramadas", lista);

                response.sendRedirect("/paciente/citas-programadas.jsp");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
