package servlet.medico;

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
import modelo.PacienteAtendido;


@WebServlet(name = "ReportesCitasMedicoSv", urlPatterns = {"/ReportesCitasMedicoSv"})
public class ReportesCitasMedicoSv extends HttpServlet {

    LogicaCita logicaCita = new LogicaCita();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        long documento = Long.parseLong(request.getParameter("documento"));
        
        List<PacienteAtendido> lista = logicaCita.historialCitasPorMedico(documento);
        
        HttpSession session = request.getSession();
        session.setAttribute("reporteCitas", lista);
        
        response.sendRedirect("/medico/reportes.jsp");
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
