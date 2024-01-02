<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("../sinLogin.jsp");
    }

    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setDateHeader("Expires", 0); // Proxies.

%>

<nav class="nav">
    <figure class="container-logo">
        <img src="img/eps.png" alt="logo" class="logo">
    </figure>
    <div class="container-info">
        <div class="box-name">
            <% if (usuario != null) {%>
            <p class="name"><%=usuario.getNombres() + " " + usuario.getApellidos()%></p>
            <% }%>
        </div>
        <figure class="container-perfil">
            <img src="img/perfil.png" alt="" class="perfil">
        </figure>
        <a href="/LoginSv?conf=0" class="loguot">Salir</a>
    </div>
</nav>
