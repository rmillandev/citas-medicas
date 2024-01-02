package servlet.administrador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.LogicaSede;
import modelo.Sede;


@WebServlet(name = "SedeEditarSv", urlPatterns = {"/SedeEditarSv"})
public class SedeEditarSv extends HttpServlet {

    LogicaSede logicaSede = new LogicaSede();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        
        Sede sede = logicaSede.obtenerSedePorId(id);
        
        HttpSession session = request.getSession();
        session.setAttribute("sedeEditar", sede);
        
        response.sendRedirect("/admin/editarSede.jsp");
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombreSede = request.getParameter("txtNombreSede");
        
        Sede sede = (Sede) request.getSession().getAttribute("sedeEditar");
        sede.setNombre(nombreSede);
        
        logicaSede.editarSede(sede);
        
        response.sendRedirect("/SedeSv");
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
