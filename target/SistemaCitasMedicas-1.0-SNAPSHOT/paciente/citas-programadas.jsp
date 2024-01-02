
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="modelo.Cita"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Citas - Sistema de Citas Medicas</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <link rel="icon" href="/assets/medical-care.png" type="image/x-icon">
        <link rel="stylesheet" href="/css/styles.css">
        <link href="csspaciente/style.css" rel="stylesheet" />
    </head>
    <body>

        <%@include file="/componentes/navpaciente.jsp" %>


        <h3 class="text-center my-4" style="color: #439CD5">CITAS PROGRAMADAS</h3>

        <div class="container">

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

            <div class="row mb-4">

                <div class="col-md-2 d-flex text-center flex-column gap-3  opciones" style="background-color: #439CD5; padding: 1rem">
                    <a href="index.jsp" class="regresar">Regresar</a>
                    <a href="asignacion-citas.jsp" class="agendar btn btn-light">Agendar Nueva Cita</a>
                    <% if (usuario != null) {%>
                    <a href="/CitasProgramadasSv?documento=<%=usuario.getNumeroDocumento()%>" class="citas btn btn-light">Citas Programadas</a>
                    <% } %>
                </div>
                <div class="col-md-10" style="background-color:  #fff; padding: 1rem">

                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="fas fa-table me-1"></i>
                            Mis Citas
                        </div>
                        <div class="card-body">             
                            <table id="datatablesSimple" class="table">
                                <thead>
                                    <tr>
                                        <th>Medico</th>
                                        <th>Fecha</th>
                                        <th>Estado</th>
                                        <th>Ver Detalles</th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <%
                                        List<Cita> lista = (List) request.getSession().getAttribute("listaCitasProgramadas");
                                        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        if (lista != null) {
                                            for (Cita result : lista) {
                                                String fechaFormateada = formatoFecha.format(result.getFecha());
                                    %>

                                    <tr>
                                        <td><%=result.getMedico().getNombres() + " " + result.getMedico().getApellidos()%></td>
                                        <td><%=fechaFormateada%></td>
                                        <td><%=result.getEstado()%></td>
                                        <td>
                                            <form name="citas" action="/DetalleCitaSv" method="GET" class="text-center">
                                                <button type="submit" class="btn btn-success">
                                                    <i class="fas fa-eye"></i>
                                                </button>
                                                <input type="hidden" name="idCita" value="<%=result.getId()%>">
                                            </form>
                                        </td>

                                    </tr>
                                    <%
                                            }
                                        }
                                    %>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <%@include file="/componentes/script-paciente.jsp" %>
        <%@include file="/componentes/script-exito.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="/js/datatables-simple-demo.js"></script>
    </body>
</html>
