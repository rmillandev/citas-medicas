<%@page import="modelo.Especialidad"%>
<%@page import="logica.LogicaEspecialidad"%>
<%@page import="java.util.List"%>
<%@page import="logica.LogicaSede"%>
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
                        <h1 class="mt-4">Guardar Medicos</h1>

                        <div class="row">

                            <div class="col-md-8">
                                <form class="p-4 border rounded" action="/MedicoSv" method="POST">
                                    <h2>Formulario Medicos</h2>
                                    <div class="row mb-2">
                                        <div class="col">
                                            <label for="txtNumeroDocumento">Numero Documento</label>
                                            <input type="number" class="form-control" id="txtNumeroDocumento" name="txtNumeroDocumento" placeholder="Numero de documento" required>
                                        </div>
                                        <div class="col">
                                            <label for="txtNombres">Nombres</label>
                                            <input type="text" class="form-control" id="txtNombres" name="txtNombres" placeholder="Nombres" pattern="[A-Za-z\s]+" title="Solo se permiten letras y espacios" required>
                                        </div>
                                    </div>
                                    <div class="row mb-2">
                                        <div class="col">
                                            <label for="txtApellidos">Apellidos</label>
                                            <input type="text" class="form-control" id="txtApellidos" name="txtApellidos" placeholder="Apellidos" pattern="[A-Za-z\s]+" title="Solo se permiten letras y espacios" required>
                                        </div>
                                        <div class="col">
                                            <label for="txtTelefono">Telefono</label>
                                            <input type="number" class="form-control" id="txtTelefono" name="txtTelefono" placeholder="Telefono" required>
                                        </div>
                                    </div>
                                    <div class="row mb-2">
                                        <div class="col">
                                            <label for="txtEmail">Email</label>
                                            <input type="email" class="form-control" id="txtEmail" name="txtEmail" placeholder="example@example.com" required>
                                        </div>
                                        <div class="col">
                                            <label for="txtGenero">Seleccionar Genero</label>
                                            <select class="form-select" id="txtGenero" name="txtGenero" aria-label="Default select example" >
                                                <option value="Masculino">Masculino</option>
                                                <option value="Femenino">Femenino</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="row mb-2">
                                        <div class="col">
                                            <label for="txtTipoDocumento">Seleccionar Tipo Documento</label>
                                            <select class="form-select" id="txtTipoDocumento" name="txtTipoDocumento" aria-label="Default select example" >
                                                <option value="CC">CC</option>
                                                <option value="CE">CE</option>
                                            </select>
                                        </div>
                                        <div class="col">
                                            <label for="txtSede">Seleccionar Sede</label>
                                            <select class="form-select" id="txtSede" name="txtSede" aria-label="Default select example" >

                                                <%
                                                    LogicaSede logicaSede = new LogicaSede();
                                                    List<Sede> listaSedes = logicaSede.obtenerSedes();

                                                    if (listaSedes != null) {
                                                        for (Sede sede : listaSedes) {

                                                %>

                                                <option value="<%=sede.getId()%>"><%=sede.getNombre()%></option>

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

                                                    if (listaEspecialidad != null) {
                                                        for (Especialidad especialidad : listaEspecialidad) {

                                                %>

                                                <option value="<%=especialidad.getId()%>"><%=especialidad.getNombre()%></option>

                                                <%
                                                        }
                                                    }
                                                %>

                                            </select>
                                        </div>
                                        <div class="col">
                                            <label for="txtContrasena">Contrasena</label>
                                            <input type="password" class="form-control" id="txtContrasena" name="txtContrasena" placeholder="*********" pattern=".{6,15}" 
                                                   title="La contraseña debe tener entre 6 y 15 caracteres" required>
                                        </div>
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

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="/js/scripts.js"></script>
    </body>
</html>