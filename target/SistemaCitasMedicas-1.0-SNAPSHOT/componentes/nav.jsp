<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("../sinLogin.jsp");
    }

    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
    response.setHeader("Pragma", "no-cache"); 
    response.setDateHeader("Expires", 0); 
%>

<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
    <!-- Navbar Brand-->
    <a class="navbar-brand ps-3" href="index.jsp">Sistema De Citas</a>
    <!-- Sidebar Toggle-->
    <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
    <!-- Navbar-->
    <ul class="d-md-inline-block navbar-nav ms-auto me-0 me-md-3 my-2 my-md-0">
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                <% if (usuario != null) {%>
                <li><p class="dropdown-item" ><%=usuario.getNombres() + " " + usuario.getApellidos()%></p></li>
                <li><p class="dropdown-item" ><%=usuario.getRol()%></p></li>
                <li><hr class="dropdown-divider" /></li>
                <li><a class="dropdown-item" href="/LoginSv?conf=0">Cerrar Session</a></li>
                    <% }%>
            </ul>
        </li>
    </ul>
</nav>