<%@page import="java.text.SimpleDateFormat"%>
<%@page import="modelo.Cita"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Detalle Cita - Sistema de Citas Medicas</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <link rel="icon" href="/assets/medical-care.png" type="image/x-icon">
        <link rel="stylesheet" href="/css/styles.css">
        <link href="csspaciente/style.css" rel="stylesheet" />
    </head>
    <body>

        <%@include file="/componentes/navpaciente.jsp" %>


        <h3 class="text-center my-4" style="color: #439CD5">DETALLE CITA</h3>

        <div class="container">

            <div class="row mb-4">

                <div class="col-md-2 d-flex text-center flex-column gap-3  opciones" style="background-color: #439CD5; padding: 1rem">
                    <a href="citas-programadas.jsp" class="regresar">Regresar</a>
                </div>
                <div class="col-md-10" style="background-color:  #fff; padding: 1rem">

                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="fas fa-circle-info"></i>
                            Detalle de la Cita 
                        </div>
                        <div class="card-body">     

                            <%
                                Cita detalle = (Cita) request.getSession().getAttribute("detalleCita");
                                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                                String fechaFormateada = formatoFecha.format(detalle.getFecha());
                            %>

                            <form class="p-4 border rounded" action="/DetalleCitaSv" method="POST">
                                <h4>Detalle Cita</h4>
                                <div class="row mb-2">
                                    <div class="col">
                                        <label for="txtNombresPaciente">Nombres Paciente</label>
                                        <input type="text" class="form-control" id="txtNombresPaciente" name="txtNombresPaciente" placeholder="Paciente" value="<%=detalle.getPaciente().getNombres()+' '+detalle.getPaciente().getApellidos()%>" disabled>
                                    </div>
                                    <div class="col">
                                        <label for="txtNombresMedico">Nombres Medico</label>
                                        <input type="text" class="form-control" id="txtNombresMedico" name="txtNombresMedico" placeholder="Medico" value="<%=detalle.getMedico().getNombres()+' '+detalle.getMedico().getApellidos()%>" disabled>
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
                                        <label for="txtObservaciones">Observaciones</label>
                                        <% if(detalle.getEstado().equals("Cancelada")) { %>
                                        <textarea class="form-control" id="txtObservaciones" name="txtObservaciones" rows="6" placeholder="Observaciones" style="resize: none" disabled><%=detalle.getObservaciones()%></textarea>
                                        <% } else if(detalle.getEstado().equals("Atendido")) { %>
                                        <textarea class="form-control" id="txtObservaciones" name="txtObservaciones" rows="6" placeholder="Observaciones" style="resize: none" disabled><%=detalle.getObservaciones()%></textarea>
                                        <% } else { %>
                                        <textarea class="form-control" id="txtObservaciones" name="txtObservaciones" rows="6" placeholder="Observaciones" style="resize: none"><%=detalle.getObservaciones()%></textarea>
                                        <small class="text-dark">Si desea puede escribir el motivo por el cual cancela la cita.</small>
                                        <% } %>
                                    </div>
                                </div>
                                <input type="hidden" name="id" value="<%=detalle.getId()%>">
                                <input type="hidden" name="doc" value="<%=usuario.getNumeroDocumento()%>">
                                <% if(detalle.getEstado().equals("Cancelada")) { %>
                                <button type="submit" class="btn btn-danger" disabled>Cancelar Cita</button>
                                <% } else if (detalle.getEstado().equals("Atendido")) { %>
                                
                                <% } else { %>
                                <button type="submit" class="btn btn-danger">Cancelar Cita</button>
                                <small class="text-danger">Si cancela la cita, ya no podra revertir el proceso</small>
                                <% } %>
                                
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <%@include file="/componentes/script-paciente.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    </body>
</html>