<%@page import="java.text.SimpleDateFormat"%>
<%@page import="modelo.Horario"%>
<%@page import="modelo.Medico"%>
<%@page import="java.util.List"%>
<%@page import="logica.LogicaMedico"%>
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
                        <h1 class="mt-4">Horarios</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">En esta parte podra hacer gestion referente a los horarios medicos</li>
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
                                    Tabla Horarios
                                </div>
                                <div class="card-body">
                                    <table id="datatablesSimple">
                                        <thead>
                                            <tr>
                                                <th>Fecha</th>
                                                <th>Medico</th>
                                                <th>Acciones</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <%
                                                List<Horario> listaHorarios = (List) request.getSession().getAttribute("listaHorarios");
                                                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                                                if (listaHorarios != null) {
                                                    for (Horario horario : listaHorarios) {
                                                        String fechaFormateada = formatoFecha.format(horario.getFecha());

                                            %>
                                            <tr>
                                                <td><%=fechaFormateada%></td>
                                                <td><%=horario.getMedico().getNombres() + " " + horario.getMedico().getApellidos()%></td>
                                                <td >
                                                    <div class="d-flex gap-3">
                                                        <form name="eliminar" action="/HorarioEliminarSv" method="POST" >
                                                            <button type="submit" class="btn btn-danger btn-user btn-block">
                                                                <i class="fas fa-trash-alt"></i>
                                                            </button>
                                                            <input type="hidden" name="id" value="<%=horario.getId()%>">
                                                        </form>

                                                        <form name="editar" action="/HorarioEditarSv" method="GET">
                                                            <button type="submit" class="btn btn-primary btn-user btn-block">
                                                                <i class="fas fa-pencil-alt"></i>
                                                            </button>
                                                            <input type="hidden" name="id" value="<%=horario.getId()%>">
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
                                                <td colspan="3">La lista de sedes está vacía o es nula.</td>
                                            </tr>
                                            <%
                                                }
                                            %>

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="col-md-4 mb-4">
                                <form class="p-4 border rounded" action="/HorarioSv" method="POST" onsubmit="return validarFecha()">
                                    <h3>Formulario Horarios</h3>
                                    <div class="mb-3">
                                        <label for="txtFecha" class="form-label">Fecha</label>
                                        <input type="datetime-local" class="form-control" id="txtFecha" name="txtFecha"  required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="txtMedico" class="form-label">Seleccionar Medico</label>
                                        <select class="form-select" id="txtMedico" name="txtMedico" aria-label="Default select example">
                                            <%
                                                LogicaMedico logicaMedico = new LogicaMedico();
                                                List<Medico> listaMedicos = logicaMedico.obtenerMedicos();

                                                if (listaMedicos != null) {
                                                    for (Medico medico : listaMedicos) {

                                            %>
                                            <option value="<%=medico.getId()%>"><%=medico.getNombres() + " " + medico.getApellidos()%></option>
                                            <%
                                                    }
                                                }
                                            %>
                                        </select>
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

        <script>
            function validarFecha() {
                // Obtener el valor del campo de fecha
                var inputFecha = document.getElementById('txtFecha').value;

                // Obtener la fecha actual en el formato requerido para comparación
                var fechaActual = new Date().toISOString().slice(0, 16); // Formato datetime-local

                // Comparar la fecha ingresada con la fecha actual
                if (inputFecha < fechaActual) {
                    alert('La fecha ingresada ya ha pasado. Por favor, ingresa una fecha futura.');
                    return false; // Evita que se envíe el formulario si la fecha es anterior a la actual
                }
                return true; // Envía el formulario si la fecha es válida
            }
        </script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="/js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="/js/datatables-simple-demo.js"></script>
    </body>
</html>