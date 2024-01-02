package servlet.administrador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.LogicaMedico;
import modelo.Especialidad;
import modelo.Medico;
import modelo.Sede;
import persistencia.EspecialidadJpaController;
import persistencia.SedeJpaController;

@WebServlet(name = "MedicoEditarSv", urlPatterns = {"/MedicoEditarSv"})
public class MedicoEditarSv extends HttpServlet {

    LogicaMedico logicaMedico = new LogicaMedico();
    
    SedeJpaController sedeJpaController = new SedeJpaController();
    EspecialidadJpaController especialidadJpaController = new EspecialidadJpaController();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        
        Medico medico = logicaMedico.obtenerMedicoPorId(id);
        
        HttpSession session = request.getSession();
        session.setAttribute("medicoEditar", medico);
        
        response.sendRedirect("/admin/editarMedico.jsp");
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String numeroDocumento = request.getParameter("txtNumeroDocumento");
        String nombres = request.getParameter("txtNombres");
        String apellidos = request.getParameter("txtApellidos");
        String telefono = request.getParameter("txtTelefono");
        String email = request.getParameter("txtEmail");
        String genero = request.getParameter("txtGenero");
        String tipoDocumento = request.getParameter("txtTipoDocumento");
        String sede = request.getParameter("txtSede");
        String especialidad = request.getParameter("txtEspecialidad");

        try {
            Medico medico = (Medico) request.getSession().getAttribute("medicoEditar");
            
            logicaMedico.editarMedico(medico, numeroDocumento, nombres, apellidos, telefono, email, genero, tipoDocumento, sede, especialidad);
            String mensaje = "El medico se actualizo correctamente";
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("/MedicoSv");
        } catch (Exception ex) {
            String mensajeError = "Hubo un error al actualizar el medico" + ex.getMessage();
            request.getSession().setAttribute("error", mensajeError);
            response.sendRedirect("/MedicoSv");
        }
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
