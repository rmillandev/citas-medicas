<%@page import="java.text.SimpleDateFormat"%>
<%@page import="modelo.Paciente"%>
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
                        <h1 class="mt-4">Modificar Pacientes</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Acontinuacion se muestra un formulario para la modificacion de los pacientes</li>
                        </ol>

                        <% %>

                        <div class="col-md-8">
                             <%
                                Paciente paciente = (Paciente) request.getSession().getAttribute("pacienteEditar");
                                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                                String fechaFormateada = formatoFecha.format(paciente.getFechaNacimiento());
                            %>

                            <form class="p-4 border rounded" action="/PacienteEditarSv" method="POST">
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
                                <button type="submit" class="btn btn-primary">Actualizar</button>

                            </form>
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