<%@page import="modelo.Cita"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="/componentes/head.jsp" %>
    <body class="sb-nav-fixed">

        <%@include file="/componentes/nav.jsp" %>

        <div id="layoutSidenav">
            <%@include file="/componentes/sidenavmedico.jsp" %>

            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Detalle Cita</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Detalle Cita</li>
                        </ol>

                        <div class="row">
                            <div class="card mb-4">
                                <div class="card-header">
                                    <i class="fas fa-circle-info"></i>
                                    Detalle Cita
                                </div>
                                <%
                                    Cita detalle = (Cita) request.getSession().getAttribute("detalleCita");
                                    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                                    String fechaFormateada = formatoFecha.format(detalle.getFecha());
                                %>
                                <div class="card-body">
                                    <form class="p-4 border rounded" action="/AtenderPacienteSv" method="POST">
                                        <h4>Detalle Cita</h4>
                                        <div class="row mb-2">
                                            <div class="col">
                                                <label for="txtNombresPaciente">Nombres Paciente</label>
                                                <input type="text" class="form-control" id="txtNombresPaciente" name="txtNombresPaciente" placeholder="Paciente" value="<%=detalle.getPaciente().getNombres() + ' ' + detalle.getPaciente().getApellidos()%>" disabled>
                                            </div>
                                            <div class="col">
                                                <label for="txtNombresMedico">Nombres Medico</label>
                                                <input type="text" class="form-control" id="txtNombresMedico" name="txtNombresMedico" placeholder="Medico" value="<%=detalle.getMedico().getNombres() + ' ' + detalle.getMedico().getApellidos()%>" disabled>
                                            </div>
                                        </div>
                                        <div class="row mb-2">
                                            <div class="col">
                                                <label for="txtFecha" class="form-label">Fecha</label>
                                                <input type="datetime-local" class="form-control" id="txtFecha" name="txtFecha"  required value="<%=fechaFormateada%>" disabled>
                                            </div>
                                            <div class="col">
                                                <label for="txtEstado">Estado</label>
                                                <input type="text" class="form-control" id="txtEstado" name="txtEstado" placeholder="Estado" value="<%=detalle.getEstado()%>" disabled>
                                            </div>
                                        </div>
                                        <div class="row mb-2">
                                            <div class="col">
                                                <label for="txtMedicamentos">Medicamentos</label>
                                                <% if (detalle.getEstado().equals("Atendido")) {%>
                                                <textarea class="form-control" id="txtMedicamentos" name="txtMedicamentos" rows="6" placeholder="Medicamentos" style="resize: none" disabled required><%=detalle.getObservaciones()%></textarea>
                                                <% } else {%>
                                                <textarea class="form-control" id="txtMedicamentos" name="txtMedicamentos" rows="6" placeholder="Medicamentos" style="resize: none" required><%=detalle.getObservaciones()%></textarea>
                                                <% }%>
                                                <small class="text-dark">Resetar medicamentos</small>
                                            </div>
                                        </div>
                                        <div class="row mb-2">
                                            <div class="col">
                                                <label for="txtInstrucciones">Instrucciones Medicamentos</label>
                                                <% if (detalle.getEstado().equals("Atendido")) {%>
                                                <textarea class="form-control" id="txtInstrucciones" name="txtInstrucciones" rows="6" placeholder="Instrucciones de los medicamentos" style="resize: none" disabled required><%=detalle.getObservaciones()%></textarea>
                                                <% } else {%>
                                                <textarea class="form-control" id="txtInstrucciones" name="txtInstrucciones" rows="6" placeholder="Instrucciones de los medicamentos" style="resize: none" required><%=detalle.getObservaciones()%></textarea>
                                                <% }%>
                                                <small class="text-dark">Instrucciones de los medicamentos</small>
                                            </div>
                                        </div>
                                        <div class="row mb-2">
                                            <div class="col">
                                                <label for="txtObservaciones">Observaciones Medicas</label>
                                                <% if (detalle.getEstado().equals("Atendido")) {%>
                                                <textarea class="form-control" id="txtObservaciones" name="txtObservaciones" rows="6" placeholder="Observaciones" style="resize: none" disabled required><%=detalle.getObservaciones()%></textarea>
                                                <% } else {%>
                                                <textarea class="form-control" id="txtObservaciones" name="txtObservaciones" rows="6" placeholder="Observaciones" style="resize: none" required><%=detalle.getObservaciones()%></textarea>
                                                <% }%>
                                                <small class="text-dark">Observaciones medicas</small>
                                            </div>
                                        </div>
                                        <input type="hidden" name="idCita" value="<%=detalle.getId()%>">
                                        <input type="hidden" name="docPaciente" value="<%=detalle.getPaciente().getNumeroDocumento()%>">
                                        <input type="hidden" name="doc" value="<%=usuario.getNumeroDocumento()%>">
                                        <% if (detalle.getEstado().equals("Atendido")) { %>
                                        <button type="submit" class="btn btn-primary" disabled>Finalizar Cita</button>
                                        <% } else { %>
                                        <button type="submit" class="btn btn-primary">Finalizar Cita</button>
                                        <% }%>
                                    </form>
                                </div>
                            </div>

                        </div>
                    </div>
                </main>

            </div>
        </div>


        <%@include file="/componentes/footer.jsp" %>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="/js/scripts.js"></script>
    </body>
</html>
