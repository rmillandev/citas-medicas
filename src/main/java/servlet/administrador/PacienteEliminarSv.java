package servlet.administrador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.LogicaPaciente;


@WebServlet(name = "PacienteEliminarSv", urlPatterns = {"/PacienteEliminarSv"})
public class PacienteEliminarSv extends HttpServlet {

    LogicaPaciente logicaPaciente = new LogicaPaciente();

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
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        try{
            logicaPaciente.eliminarPaciente(id);
            String mensaje = "Se elimino el paciente exitosamente";
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("/PacienteSv");
        }catch(Exception ex){
            String mensajeError = "Hubo un error al eliminar el paciente " + ex.getMessage();
            request.getSession().setAttribute("error", mensajeError);
            response.sendRedirect("/PacienteSv");
        }
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
