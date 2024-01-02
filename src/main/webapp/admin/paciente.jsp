<%@page import="modelo.Paciente"%>
<%@page import="modelo.Medico"%>
<%@page import="java.util.List"%>
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
                        <h1 class="mt-4">Pacientes</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Acontinuacion se muestra una tabla con diferentes datos de los pacientes que se encuentran registrados en el sistema</li>
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
                            <div class="card mb-4">
                                <div class="card-header">
                                    <i class="fas fa-table me-1"></i>
                                    Tabla Pacientes
                                </div>
                                <div class="card-body">
                                    <table id="datatablesSimple">
                                        <thead>
                                            <tr>
                                                <th>Numero Documento</th>
                                                <th>Nombres</th>
                                                <th>Apellidos</th>
                                                <th>Telefono</th>
                                                <th>Email</th>
                                                <th>Direccion</th>
                                                <th>Acciones</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <%
                                                List<Paciente> lista = (List) request.getSession().getAttribute("listaPacientes");

                                                if(lista != null){
                                                    for(Paciente result : lista){  

                                            %>
                                            <tr>
                                                <td><%=result.getNumeroDocumento()%></td>
                                                <td><%=result.getNombres()%></td>
                                                <td><%=result.getApellidos()%></td>
                                                <td><%=result.getTelefono()%></td>
                                                <td><%=result.getEmail()%></td>
                                                <td><%=result.getDireccion()%></td>
                                                <td>
                                                    <div class="d-flex gap-3">
                                                    <form name="eliminar" action="/PacienteEliminarSv" method="POST" >
                                                        <button type="submit" class="btn btn-danger btn-user btn-block">
                                                            <i class="fas fa-trash-alt"></i>
                                                        </button>
                                                        <input type="hidden" name="id" value="<%=result.getId()%>">
                                                    </form>

                                                    <form name="editar" action="/PacienteEditarSv" method="GET">
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
                                            }else{

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
