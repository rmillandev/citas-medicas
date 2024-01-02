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
import logica.LogicaPaciente;
import modelo.Paciente;


@WebServlet(name = "PacienteSv", urlPatterns = {"/PacienteSv"})
public class PacienteSv extends HttpServlet {

    LogicaPaciente logicaPaciente = new LogicaPaciente();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Paciente> listaPacientes = logicaPaciente.obtenerPacientes();
        
        HttpSession session = request.getSession();
        session.setAttribute("listaPacientes", listaPacientes);
        
        response.sendRedirect("/admin/paciente.jsp");
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String numeroDocumento = request.getParameter("txtNumeroDocumento");
        String nombres = request.getParameter("txtNombres");
        String apellidos = request.getParameter("txtApellidos");
        String direccion = request.getParameter("txtDireccion");
        String telefono = request.getParameter("txtTelefono");
        String email = request.getParameter("txtEmail");
        String tipoDocumento = request.getParameter("txtTipoDocumento");
        String genero = request.getParameter("txtGenero");
        String fechaNacimiento = request.getParameter("txtFechaNacimiento");
        String contrasena = request.getParameter("txtContrasena");
        
        try{
            logicaPaciente.crearPaciente(numeroDocumento, nombres, apellidos, direccion, telefono, email, tipoDocumento, genero, fechaNacimiento, contrasena);
            String mensaje = "Se registro exitosamente";
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("registrarse.jsp");
        }catch(Exception ex){
            String mensajeError = "Hubo un error al registrar el paciente " + ex.getMessage();
            request.getSession().setAttribute("error", mensajeError);
            response.sendRedirect("registrarse.jsp");
        }
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
