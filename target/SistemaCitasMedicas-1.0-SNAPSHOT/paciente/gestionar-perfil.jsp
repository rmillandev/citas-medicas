<%@page import="java.text.SimpleDateFormat"%>
<%@page import="modelo.Paciente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Gestionar Perfil - Sistema de Citas Medicas</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <link rel="icon" href="/assets/medical-care.png" type="image/x-icon">
        <link rel="stylesheet" href="/css/styles.css">
        <link href="csspaciente/style.css" rel="stylesheet" />
    </head>
    <body>

        <%@include file="/componentes/navpaciente.jsp" %>


        <h3 class="text-center my-4" style="color: #439CD5">GESTIONAR PERFIL</h3>

        <div class="container">

            <div class="row mb-4">

                <div class="col-md-2 d-flex text-center flex-column gap-3  opciones" style="background-color: #439CD5; padding: 1rem">
                    <a href="index.jsp" class="regresar">Regresar</a>
                </div>
                <div class="col-md-10" style="background-color:  #fff; padding: 1rem">

                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="fas fa-circle-info"></i>
                            Informacion del Paciente 
                        </div>
                        <div class="card-body">     

                            <%
                                Paciente paciente = (Paciente) request.getSession().getAttribute("paciente");
                                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                                String fechaFormateada = formatoFecha.format(paciente.getFechaNacimiento());
                            %>

                            <form class="p-4 border rounded" action="/GestionarPerfilSv" method="POST">
                                <h4>Informacion Paciente</h4>
                                <div class="row mb-2">
                                    <div class="col">
                                        <label for="txtNombres">Nombres</label>
                                        <input type="text" class="form-control" id="txtNombres" name="txtNombres" placeholder="Nombres" value="<%=paciente.getNombres()%>" pattern="[A-Za-z\s]+" title="Solo se permiten letras y espacios" required >
                                    </div>
                                    <div class="col">
                                        <label for="txtApellidos">Apellidos</label>
                                        <input type="text" class="form-control" id="txtApellidos" name="txtApellidos" placeholder="Apellidos" value="<%=paciente.getApellidos()%>" pattern="[A-Za-z\s]+" title="Solo se permiten letras y espacios" required>
                                    </div>
                                </div>
                                <div class="row mb-2">
                                    <div class="col">
                                        <label for="txtTipoDocumento">Seleccionar Tipo Documento</label>
                                        <select class="form-select" id="txtTipoDocumento" name="txtTipoDocumento" aria-label="Default select example" >
                                            <option value="CC" <%=paciente.getTipoDocumento().equals("CC") ? "selected" : ""%> >CC</option>
                                            <option value="CE" <%=paciente.getTipoDocumento().equals("CE") ? "selected" : ""%> >CE</option>
                                            <option value="TI" <%=paciente.getTipoDocumento().equals("TI") ? "selected" : ""%> >TI</option>
                                        </select>
                                    </div>
                                    <div class="col">
                                        <label for="txtNumeroDocumento">Numero Documento</label>
                                        <input type="number" class="form-control" id="txtNumeroDocumento" name="txtNumeroDocumento" placeholder="Numero documento" value="<%=paciente.getNumeroDocumento()%>">
                                    </div>
                                </div>
                                <div class="row mb-2">
                                    <div class="col">
                                        <label for="txtDireccion">Direccion</label>
                                        <input type="text" class="form-control" id="txtDireccion" name="txtDireccion" placeholder="Direccion" value="<%=paciente.getDireccion()%>" >
                                    </div>
                                    <div class="col">
                                        <label for="txtTelefono">Telefono</label>
                                        <input type="number" class="form-control" id="txtTelefono" name="txtTelefono" placeholder="Telefono" value="<%=paciente.getTelefono()%>" >
                                    </div>
                                </div>
                                <div class="row mb-2">
                                    <div class="col">
                                        <label for="txtEmail">Email</label>
                                        <input type="email" class="form-control" id="txtEmail" name="txtEmail" placeholder="Email" value="<%=paciente.getEmail()%>" >
                                    </div>
                                    <div class="col">
                                        <label for="txtFecha" class="form-label">Fecha Nacimiento</label>
                                        <input type="date" class="form-control" id="txtFecha" name="txtFecha"  required value="<%=fechaFormateada%>">
                                    </div>
                                </div>
                                <div class="row mb-2">
                                    <div class="col">
                                        <label for="txtGenero">Seleccionar Genero</label>
                                        <select class="form-select" id="txtGenero" name="txtGenero" aria-label="Default select example" >
                                            <option value="Masculino" <%=paciente.getGenero().equals("Masculino") ? "selected" : ""%> >Masculino</option>
                                            <option value="Femenino" <%=paciente.getGenero().equals("Femenino") ? "selected" : ""%> >Femenino</option>
                                        </select>
                                    </div>
                                </div>
                                <input type="hidden" name="id" value="<%=paciente.getId()%>">
                                <button type="submit" class="btn btn-primary">Actualizar Informacion</button>

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