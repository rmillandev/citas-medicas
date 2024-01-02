package servlet.administrador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.LogicaHorario;


@WebServlet(name = "HorarioEliminarSv", urlPatterns = {"/HorarioEliminarSv"})
public class HorarioEliminarSv extends HttpServlet {

    LogicaHorario logicaHorario = new LogicaHorario();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try{
            int id = Integer.parseInt(request.getParameter("id"));
            
            logicaHorario.eliminarHorario(id);
            
            String mensaje = "El horario se elimino correctamente";
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("/HorarioSv"); 
        }catch(Exception ex){
            String mensajeError = "Hubo un error al eliminar el horario" + ex.getMessage();
            request.getSession().setAttribute("error", mensajeError);
            response.sendRedirect("/HorarioSv");
        }
  
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
