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
import logica.LogicaHorario;
import modelo.Horario;


@WebServlet(name = "HorarioSv", urlPatterns = {"/HorarioSv"})
public class HorarioSv extends HttpServlet {

    LogicaHorario logicaHorario = new LogicaHorario();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Horario> listaHorarios = logicaHorario.obtenerHorarios();
        
        HttpSession session = request.getSession();
        session.setAttribute("listaHorarios", listaHorarios);
        
        response.sendRedirect("/admin/horario.jsp");
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String fecha = request.getParameter("txtFecha");
        String medico = request.getParameter("txtMedico");
        
        
        try{
            logicaHorario.crearHorario(fecha, medico);
            String mensaje = "El horario se guardó correctamente";
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("/HorarioSv"); 
        }catch(Exception ex){
            String mensajeError = "Hubo un error al guardar el horario" + ex.getMessage();
            request.getSession().setAttribute("error", mensajeError);
            response.sendRedirect("/HorarioSv");
        }
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
