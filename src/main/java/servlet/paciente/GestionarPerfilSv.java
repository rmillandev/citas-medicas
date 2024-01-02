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
import logica.LogicaPaciente;
import modelo.Paciente;


@WebServlet(name = "GestionarPerfilSv", urlPatterns = {"/GestionarPerfilSv"})
public class GestionarPerfilSv extends HttpServlet {

    LogicaPaciente logicaPaciente = new LogicaPaciente();
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
            Paciente paciente = logicaPaciente.obtenerPacientePorId(id);
            if (paciente != null) {
                HttpSession session = request.getSession();
                session.setAttribute("paciente", paciente);

                response.sendRedirect("/paciente/gestionar-perfil.jsp");
            }
        }
        
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
            
            String mensaje = "Se actualizo correctamente la informacion";
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("/paciente/index.jsp");
        }catch(Exception ex){
            String mensajeError = "Hubo un error al actualizar la informacion: " + ex.getMessage();
            request.getSession().setAttribute("error", mensajeError);
            response.sendRedirect("/paciente/index.jsp");
        }
 
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
