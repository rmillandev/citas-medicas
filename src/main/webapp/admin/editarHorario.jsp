<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Medico"%>
<%@page import="logica.LogicaMedico"%>
<%@page import="modelo.Horario"%>
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
                        <h1 class="mt-4">Editar Horario</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">En esta parte podra hacer la modificacion de un horario</li>
                        </ol>
                        <div class="col-md-4 mb-4">

                            <%

                                Horario horarioEditar = (Horario) request.getSession().getAttribute("horarioEditar");
                                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                                String fechaFormateada = formatoFecha.format(horarioEditar.getFecha());
                            %>

                            <form class="p-4 border rounded" action="/HorarioEditarSv" method="POST">
                                <h3>Formulario Horarios</h3>
                                <div class="mb-3">
                                    <label for="txtFecha" class="form-label">Fecha</label>
                                    <input type="datetime-local" class="form-control" id="txtFecha" name="txtFecha"  required value="<%=fechaFormateada%>">
                                </div>
                                <div class="mb-3">
                                    <label for="txtMedico" class="form-label">Seleccionar Medico</label>
                                    <select class="form-select" id="txtMedico" name="txtMedico" aria-label="Default select example">
                                        <%
                                            LogicaMedico logicaMedico = new LogicaMedico();
                                            List<Medico> listaMedicos = logicaMedico.obtenerMedicos();
                                            int idSeleccionado = horarioEditar.getMedico().getId();

                                            if (listaMedicos != null) {
                                                for (Medico medico : listaMedicos) {
                                                    String selected = (medico.getId() == idSeleccionado) ? "selected" : "";
                                        %>
                                        <option value="<%=medico.getId()%>" <%=selected%>><%=medico.getNombres() + " " + medico.getApellidos()%></option>
                                        <%
                                                }
                                            }
                                        %>
                                    </select>
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
