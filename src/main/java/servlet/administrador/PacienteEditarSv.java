
package servlet.administrador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.LogicaPaciente;
import modelo.Paciente;


@WebServlet(name = "PacienteEditarSv", urlPatterns = {"/PacienteEditarSv"})
public class PacienteEditarSv extends HttpServlet {

    LogicaPaciente logicaPaciente = new LogicaPaciente();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        Paciente paciente = logicaPaciente.obtenerPacientePorId(id);
        
        HttpSession session = request.getSession();
        session.setAttribute("pacienteEditar", paciente);
        
        response.sendRedirect("/admin/editarPaciente.jsp");
        
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String nombres = request.getParameter("txtNombres");
        String apellidos = request.getParameter("txtApellidos");
        String tipoDocumento = request.getParameter("txtTipoDocumento");
        String numeroDocumento = request.getParameter("txtNumeroDocumento");
        String direccion = request.getParameter("txtDireccion");
        String telefono = request.getParameter("txtTelefono");
        String email = request.getParameter("txtEmail");
        String fechaNacimiento = request.getParameter("txtFecha");
        String genero = request.getParameter("txtGenero");
        
        try{
            logicaPaciente.actualizarInformacion(id, nombres, apellidos, tipoDocumento, numeroDocumento, direccion, telefono, email, fechaNacimiento, genero);
            
            String mensaje = "Se actualizo correctamente el paciente";
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("/PacienteSv");
        }catch(Exception ex){
            String mensajeError = "Hubo un error al actualizar el paciente: " + ex.getMessage();
            request.getSession().setAttribute("error", mensajeError);
            response.sendRedirect("/PacienteSv");
        }
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
