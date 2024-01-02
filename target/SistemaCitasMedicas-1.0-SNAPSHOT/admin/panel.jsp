<%@page import="modelo.Horario"%>
<%@page import="logica.LogicaHorario"%>
<%@page import="modelo.Paciente"%>
<%@page import="logica.LogicaPaciente"%>
<%@page import="logica.LogicaMedico"%>
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
                        <h1 class="mt-4">Panel</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Panel</li>
                        </ol>

                        <%
                            LogicaMedico logicaMedico = new LogicaMedico();
                            List<Medico> listaMedicos = logicaMedico.obtenerMedicos();

                            LogicaPaciente logicaPaciente = new LogicaPaciente();
                            List<Paciente> listaPacientes = logicaPaciente.obtenerPacientes();

                            LogicaHorario logicaHorario = new LogicaHorario();
                            List<Horario> listaHorarios = logicaHorario.obtenerHorarios();

                        %>
                        
                        <div class="row">
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-light text-dark mb-4">
                                    <div class="d-flex align-items-center">
                                        <div class="card-body"><i class="fas fa-user-doctor"></i> Medicos</div>
                                        <div class="mx-3"><%=listaMedicos.size()%></div>
                                    </div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-dark stretched-link" href="/MedicoSv">Ver Detalles</a>
                                        <div class="small text-dark"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-info text-white mb-4">
                                    <div class="d-flex align-items-center">
                                        <div class="card-body"><i class="fas fa-user"></i> Pacientes</div>
                                        <div class="mx-3"><%=listaPacientes.size()%></div>
                                    </div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="/PacienteSv">Ver Detalles</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-secondary text-white mb-4">
                                    <div class="d-flex align-items-center">
                                        <div class="card-body"><i class="fas fa-calendar"></i> Horarios</div>
                                        <div class="mx-3"><%=listaHorarios.size()%></div>
                                    </div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="/HorarioSv">Ver Detalles</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
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
