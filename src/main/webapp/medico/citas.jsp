<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Cita"%>
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
                        <h1 class="mt-4">Citas</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Acontinuacion se muestra una tabla con las diferentes citas que estan en estado "pendiente", al atender al paciente esta cita ya no se muestra</li>
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
                        
                        <a href="/ReportesCitasMedicoSv?documento=<%=usuario.getNumeroDocumento()%>" class="btn btn-primary mb-2">Ver Reportes</a>
                        
                        <div class="row">
                            <div class="card mb-4">
                                <div class="card-header">
                                    <i class="fas fa-table me-1"></i>
                                    Tabla Medicos
                                </div>
                                <div class="card-body">
                                    <table id="datatablesSimple">
                                        <thead>
                                            <tr>
                                                <th>Fecha</th>
                                                <th>Observaciones</th>
                                                <th>Paciente</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <%
                                                List<Cita> lista = (List) request.getSession().getAttribute("citasMedico");
                                                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                                if(lista != null){
                                                    for(Cita result : lista){  
                                                    String fechaFormateada = formatoFecha.format(result.getFecha());
                                            %>
                                            <tr>
                                                <td><%=fechaFormateada%></td>
                                                <td><%=result.getObservaciones()%></td>
                                                <td><%=result.getPaciente().getNombres()+" "+result.getPaciente().getApellidos()%></td>
                                                <td>
                                                    <div class="d-flex gap-3">
                                                    <form name="atender" action="/AtenderPacienteSv" method="GET" >
                                                        <button type="submit" class="btn btn-primary btn-user btn-block">
                                                            Atender
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