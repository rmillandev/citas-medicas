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


@WebServlet(name = "CitasMedicoSv", urlPatterns = {"/CitasMedicoSv"})
public class CitasMedicoSv extends HttpServlet {

    LogicaCita logicaCita = new LogicaCita();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long documento = Long.parseLong(request.getParameter("documento"));
        
        int id = logicaCita.medicoId(documento);
        
        if(id != 0){
            List<Cita> lista = logicaCita.obtenerCitasPorIdMedico(id);
            if(lista != null){
                HttpSession session = request.getSession();
                session.setAttribute("citasMedico", lista);

                response.sendRedirect("/medico/citas.jsp");
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
