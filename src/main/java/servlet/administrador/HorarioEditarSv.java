package servlet.administrador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.LogicaHorario;
import modelo.Horario;


@WebServlet(name = "HorarioEditarSv", urlPatterns = {"/HorarioEditarSv"})
public class HorarioEditarSv extends HttpServlet {

    LogicaHorario logicaHorario = new LogicaHorario();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id =Integer.parseInt(request.getParameter("id"));
        
        Horario horario = logicaHorario.obtenerHorarioPorId(id);
        
        HttpSession session = request.getSession();
        session.setAttribute("horarioEditar", horario);
        
        response.sendRedirect("/admin/editarHorario.jsp");
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fecha = request.getParameter("txtFecha");
        String medico = request.getParameter("txtMedico");
     
         try {
            Horario horario = (Horario) request.getSession().getAttribute("horarioEditar");
            
            logicaHorario.editarHorario(horario,fecha, medico);
             
            String mensaje = "El horario se actualizo correctamente";
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("/HorarioSv");
        } catch (Exception ex) {
            String mensajeError = "Hubo un error al actualizar el horario" + ex.getMessage();
            request.getSession().setAttribute("error", mensajeError);
            response.sendRedirect("/HorarioSv");
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
