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
import logica.LogicaCita;
import modelo.Cita;


@WebServlet(name = "ReportesCitasSv", urlPatterns = {"/ReportesCitasSv"})
public class ReportesCitasSv extends HttpServlet {

    LogicaCita logicaCita = new LogicaCita();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Cita> listaCitas = logicaCita.historialCitas();
        
        HttpSession session = request.getSession();
        session.setAttribute("historialCitas", listaCitas);
        
        response.sendRedirect("/admin/reportes.jsp");
        
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
