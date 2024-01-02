<%@page import="modelo.Especialidad"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="/componentes/head.jsp" %>
    <body class="sb-nav-fixed">

       <%@include file="/componentes/nav.jsp" %>
        
        <div id="layoutSidenav">
            <%@include file="/componentes/sidenav.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Editar Especialidad</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">En esta parte podra hacer la modificacion de una especialidad</li>
                        </ol>
                        <div class="col-md-4 mb-4">
                            
                            <% Especialidad especialidadEditar = (Especialidad) request.getSession().getAttribute("especialidadEditar"); %>
                            
                            <form class="p-4 border rounded" action="/EspecialidadEditarSv" method="POST">
                                <h3>Formulario Sede</h3>
                                <div class="mb-3">
                                    <label for="txtNombreEspecialidad" class="form-label">Nombre Especialidad</label>
                                    <input type="text" class="form-control" id="txtNombreEspecialidad" name="txtNombreEspecialidad" placeholder="Nombre de la especialidad" pattern="[A-Za-z\s]+" title="Solo se permiten letras y espacios" required value="<%=especialidadEditar.getNombre()%>">
                                </div>
                                <button type="submit" class="btn btn-primary">Actualizar</button>
                            </form>
                        </div>
                    </div>
                </main>

            </div>
        </div>

        <%@include file="/componentes/footer.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>
