package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.LogicaLogin;
import modelo.Usuario;

@WebServlet(name = "LoginSv", urlPatterns = {"/LoginSv"})
public class LoginSv extends HttpServlet {

    LogicaLogin logicaLogin = new LogicaLogin();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        int conf = Integer.parseInt(request.getParameter("conf"));

        if (conf == 0) {
            session.removeAttribute("usuario");
            session.invalidate();
            response.sendRedirect("login.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        String numeroDocumento = request.getParameter("txtNumeroDocumento");
        String contrasena = request.getParameter("txtContrasena");

        try {
            Usuario usuario = logicaLogin.validarUsuario(numeroDocumento, contrasena);

            if (usuario != null) {

                session.setAttribute("usuario", usuario);

                String rol = usuario.getRol();

                switch (rol) {
                    case "Administrador":
                        response.sendRedirect("/admin/panel.jsp");
                        break;
                    case "Medico":
                        response.sendRedirect("/medico/panel.jsp");
                        break;
                    case "Paciente":
                        response.sendRedirect("/paciente/index.jsp");
                        break;
                    default:
                        break;
                }

            } else {
                System.out.println("Error al entrar");
                response.sendRedirect("loginError.jsp");
            }

        } catch (Exception ex) {
            // Manejo de la excepción: redirigir a una página de error o registrar el error
            System.out.println("Error: " + ex.getMessage()); // Esto imprimirá la traza de la excepción en la consola del servidor
            response.sendRedirect("loginError.jsp"); // Puedes redirigir a una página de error personalizada
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
