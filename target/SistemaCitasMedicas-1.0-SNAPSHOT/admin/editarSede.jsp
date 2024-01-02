<%@page import="modelo.Sede"%>
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
                        <h1 class="mt-4">Editar Sede</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">En esta parte podra hacer la modificacion de una sede</li>
                        </ol>
                        <div class="col-md-4 mb-4">
                            
                            <% Sede sedeEditar = (Sede) request.getSession().getAttribute("sedeEditar"); %>
                            
                            <form class="p-4 border rounded" action="/SedeEditarSv" method="POST">
                                <h3>Formulario Sede</h3>
                                <div class="mb-3">
                                    <label for="txtNombreSede" class="form-label">Nombre Sede</label>
                                    <input type="text" class="form-control" id="txtNombreSede" name="txtNombreSede" placeholder="Nombre de la sede" pattern="[A-Za-z\s]+" title="Solo se permiten letras y espacios" required value="<%=sedeEditar.getNombre()%>">
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
