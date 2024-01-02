<%@page import="java.util.List"%>
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
                        <h1 class="mt-4">Especialidades</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">En esta parte podra hacer gestion referente a las especialidades medicas disponibles</li>
                        </ol>

                        <% String mensaje = (String) request.getSession().getAttribute("mensaje");
                            String mensajeError = (String) request.getSession().getAttribute("error");

                            if (mensaje != null) {

                        %>

                        <div class="alert alert-success" id="mensajeExito" role="alert">
                            <%=mensaje%>
                        </div>

                        <%

                        } else if (mensajeError != null) {

                        %>

                        <div class="alert alert-danger" id="mensajeError" role="alert">
                            <%=mensajeError%>
                        </div>

                        <% }%>

                        <div class="row">
                            <div class="col-md-8 card mb-4">
                                <div class="card-header">
                                    <i class="fas fa-table me-1"></i>
                                    Tabla Especialidades
                                </div>

                                <div class="card-body">
                                    <table id="datatablesSimple">
                                        <thead>
                                            <tr>
                                                <th>Nombre</th>
                                                <th>Acciones</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <%
                                                List<Especialidad> lista = (List) request.getSession().getAttribute("listaEspecialidad");

                                                if (lista != null) {
                                                    for (Especialidad result : lista) {
                                            %>
                                            <tr>
                                                <td><%=result.getNombre()%></td>
                                                <td >
                                                    <div class="d-flex gap-3">
                                                        <form name="eliminar" action="/EspecialidadEliminarSv" method="POST" >
                                                            <button type="submit" class="btn btn-danger btn-user btn-block">
                                                                <i class="fas fa-trash-alt"></i>
                                                            </button>
                                                            <input type="hidden" name="id" value="<%=result.getId()%>">
                                                        </form>

                                                        <form name="editar" action="/EspecialidadEditarSv" method="GET">
                                                            <button type="submit" class="btn btn-primary btn-user btn-block">
                                                                <i class="fas fa-pencil-alt"></i>
                                                            </button>
                                                            <input type="hidden" name="id" value="<%=result.getId()%>">
                                                        </form>
                                                    </div>
                                                </td>
                                            </tr>
                                            <%
                                                }
                                            } else {
                                            %>
                                            <!-- Manejo de la lista nula -->
                                            <tr>
                                                <td colspan="3">La lista de especialidades está vacía o es nula.</td>
                                            </tr>
                                            <%
                                                }
                                            %>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="col-md-4 mb-4">
                                <form class="p-4 border rounded" action="/EspecialidadSv" method="POST">
                                    <h3>Formulario Especialidad</h3>
                                    <div class="mb-3">
                                        <label for="txtNombreEspecialidad" class="form-label">Nombre Especialidad</label>
                                        <input type="text" class="form-control" id="txtNombreEspecialidad" name="txtNombreEspecialidad" placeholder="Nombre de la especialidad" pattern="[A-Za-z\s]+" title="Solo se permiten letras y espacios" required>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Guardar</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </main>

            </div>
        </div>


        <%@include file="/componentes/footer.jsp" %>

        <%@include file="/componentes/script-exito.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="/js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="/js/datatables-simple-demo.js"></script>
    </body>
</html>
