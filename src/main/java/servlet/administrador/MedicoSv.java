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
import logica.LogicaMedico;
import modelo.Medico;

@WebServlet(name = "MedicoSv", urlPatterns = {"/MedicoSv"})
public class MedicoSv extends HttpServlet {

    LogicaMedico logicaMedico = new LogicaMedico();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Medico> listaMedicos = logicaMedico.obtenerMedicos();
        
        HttpSession session = request.getSession();
        session.setAttribute("listaMedicos", listaMedicos);
        
        response.sendRedirect("/admin/medico.jsp");
        
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
        String contrasena = request.getParameter("txtContrasena");

        try {
            logicaMedico.crearMedico(numeroDocumento, nombres, apellidos, telefono, email, genero, tipoDocumento, sede, especialidad, contrasena);
            String mensaje = "El medico se guardo correctamente";
            request.getSession().setAttribute("mensaje", mensaje);
            response.sendRedirect("/MedicoSv");
        } catch (Exception ex) {
            String mensajeError = "Hubo un error al guardar el medico" + ex.getMessage();
            request.getSession().setAttribute("error", mensajeError);
            response.sendRedirect("/MedicoSv");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
