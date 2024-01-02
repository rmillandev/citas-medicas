<%@page import="modelo.Medico"%>
<%@page import="modelo.Especialidad"%>
<%@page import="logica.LogicaEspecialidad"%>
<%@page import="modelo.Sede"%>
<%@page import="java.util.List"%>
<%@page import="logica.LogicaSede"%>
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
                        <h1 class="mt-4">Modificar Medicos</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Acontinuacion se muestra un formulario para la modificacion de los medicos</li>
                        </ol>

                        <% Medico medico = (Medico) request.getSession().getAttribute("medicoEditar"); %>

                        <div class="col-md-8">
                            <form class="p-4 border rounded" action="/MedicoEditarSv" method="POST">
                                <h2>Formulario Medicos</h2>
                                <div class="row mb-2">
                                    <div class="col">
                                        <label for="txtNumeroDocumento">Numero Documento</label>
                                        <input type="number" class="form-control" id="txtNumeroDocumento" name="txtNumeroDocumento" placeholder="Numero de documento" required value="<%=medico.getNumeroDocumento()%>"> 
                                    </div>
                                    <div class="col">
                                        <label for="txtNombres">Nombres</label>
                                        <input type="text" class="form-control" id="txtNombres" name="txtNombres" placeholder="Nombres" pattern="[A-Za-z\s]+" title="Solo se permiten letras y espacios" required value="<%=medico.getNombres()%>">
                                    </div>
                                </div>
                                <div class="row mb-2">
                                    <div class="col">
                                        <label for="txtApellidos">Apellidos</label>
                                        <input type="text" class="form-control" id="txtApellidos" name="txtApellidos" placeholder="Apellidos" pattern="[A-Za-z\s]+" title="Solo se permiten letras y espacios" required value="<%=medico.getApellidos()%>">
                                    </div>
                                    <div class="col">
                                        <label for="txtTelefono">Telefono</label>
                                        <input type="number" class="form-control" id="txtTelefono" name="txtTelefono" placeholder="Telefono" required value="<%=medico.getTelefono()%>">
                                    </div>
                                </div>
                                <div class="row mb-2">
                                    <div class="col">
                                        <label for="txtEmail">Email</label>
                                        <input type="email" class="form-control" id="txtEmail" name="txtEmail" placeholder="example@example.com" required value="<%=medico.getEmail()%>">
                                    </div>
                                    <div class="col">
                                        <label for="txtGenero">Seleccionar Genero</label>
                                        <select class="form-select" id="txtGenero" name="txtGenero" aria-label="Default select example" >
                                            <option value="Masculino" <%=medico.getGenero().equals("Masculino") ? "selected" : "" %> >Masculino</option>
                                            <option value="Femenino"  <%=medico.getGenero().equals("Femenino") ? "selected" : "" %>>Femenino</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row mb-2">
                                    <div class="col">
                                        <label for="txtTipoDocumento">Seleccionar Tipo Documento</label>
                                        <select class="form-select" id="txtTipoDocumento" name="txtTipoDocumento" aria-label="Default select example" >
                                            <option value="CC" <%=medico.getTipoDocumento().equals("CC") ? "selected" : "" %> >CC</option>
                                            <option value="CE" <%=medico.getTipoDocumento().equals("CE") ? "selected" : "" %> >CE</option>
                                        </select>
                                    </div>
                                    <div class="col">
                                        <label for="txtSede">Seleccionar Sede</label>
                                        <select class="form-select" id="txtSede" name="txtSede" aria-label="Default select example" >

                                            <%
                                                LogicaSede logicaSede = new LogicaSede();
                                                List<Sede> listaSedes = logicaSede.obtenerSedes();
                                                int idSedeSeleccionada = medico.getSede().getId();
                                                
                                                if (listaSedes != null) {
                                                    for (Sede sede : listaSedes) {
                                                    String selected = (sede.getId() == idSedeSeleccionada) ? "selected" : "";
                                            %>

                                            <option value="<%=sede.getId()%>" <%=selected%> ><%=sede.getNombre()%></option>

                                            <%
                                                    }
                                                }
                                            %>

                                        </select>
                                    </div>
                                </div>
                                <div class="row mb-2">
                                    <div class="col">
                                        <label for="txtEspecialidad">Seleccionar Especialidad</label>
                                        <select class="form-select" id="txtEspecialidad" name="txtEspecialidad" aria-label="Default select example" >

                                            <%
                                                LogicaEspecialidad logicaEspecialidad = new LogicaEspecialidad();
                                                List<Especialidad> listaEspecialidad = logicaEspecialidad.obtenerEspecialidades();
                                                int idEspecialidadSeleccionada = medico.getEspecialidad().getId();

                                                if (listaEspecialidad != null) {
                                                    for (Especialidad especialidad : listaEspecialidad) {
                                                    String selected = (especialidad.getId() == idEspecialidadSeleccionada) ? "selected" : "";

                                            %>

                                            <option value="<%=especialidad.getId()%>" <%=selected%>><%=especialidad.getNombre()%></option>

                                            <%
                                                    }
                                                }
                                            %>

                                        </select>
                                    </div>
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
        <script src="/js/scripts.js"></script>
    </body>
</html>
