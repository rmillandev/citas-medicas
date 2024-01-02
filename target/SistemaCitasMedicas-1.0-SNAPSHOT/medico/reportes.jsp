<%@page import="modelo.PacienteAtendido"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="modelo.Cita"%>
<%@page import="java.util.List"%>
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
                        <h1 class="mt-4">Reporte Cita</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Acontinuacion se muestra una tabla con el detalle de la cita que fue atendida</li>
                        </ol>
                        
                        <a href="reporteExcel.jsp" class="btn btn-success mb-2">Generar Reportes <i class="fas fa-file-excel"></i></a>
                        
                        <div class="row">
                            <div class="card mb-4">
                                <div class="card-header">
                                    <i class="fas fa-table me-1"></i>
                                    Historial
                                </div>
                                <div class="card-body">
                                    <table id="datatablesSimple">
                                        <thead>
                                            <tr>
                                                <th>Documento Medico</th>
                                                <th>Documento Paciente</th>
                                                <th>Observaciones</th>
                                                <th>Medicamentos</th>
                                                <th>Instruccion Medicamento</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <%
                                                List<PacienteAtendido> lista = (List) request.getSession().getAttribute("reporteCitas");
                                    
                                                
                                                if(lista != null){
                                                    for(PacienteAtendido result : lista){  

                                            %>
                                            <tr>
                                                <td><%=result.getDocumento_medico()%></td>
                                                <td><%=result.getDocumento_paciente()%></td>
                                                <td><%=result.getObservaciones()%></td>
                                                <td><%=result.getMedicamentos()%></td>
                                                <td><%=result.getInstruccion_medicamento()%></td>
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
      

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="/js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="/js/datatables-simple-demo.js"></script>
    </body>
</html>