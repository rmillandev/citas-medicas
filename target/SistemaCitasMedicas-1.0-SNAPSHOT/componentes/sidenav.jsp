<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">
                <div class="sb-sidenav-menu-heading">Panel</div>
                <a class="nav-link" href="/admin/panel.jsp">
                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    Panel
                </a>
                <div class="sb-sidenav-menu-heading">Gestion</div>
                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pacientes" aria-expanded="false" aria-controls="collapseLayouts">
                    <div class="sb-nav-link-icon"><i class="fas fa-user"></i></div>
                    Pacientes
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="pacientes" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav">
                        <a class="nav-link" href="/PacienteSv">Ver Pacientes</a>
                    </nav>
                </div>
                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#medicos" aria-expanded="false" aria-controls="collapseLayouts">
                    <div class="sb-nav-link-icon"><i class="fas fa-user-doctor"></i></div>
                    Medicos
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="medicos" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav">
                        <a class="nav-link" href="/MedicoSv">Ver Medicos</a>
                        <a class="nav-link" href="guardarMedico.jsp">Nuevo Medico</a>
                    </nav>
                </div>
                <div class="sb-sidenav-menu-heading">Adicionales</div>
                <a class="nav-link" href="/SedeSv">
                    <div class="sb-nav-link-icon"><i class="fas fa-hospital"></i></div>
                    Sedes
                </a>
                <a class="nav-link" href="/EspecialidadSv">
                    <div class="sb-nav-link-icon"><i class="fas fa-hand-holding-medical"></i></div>
                    Especialidades
                </a>
                <a class="nav-link" href="/HorarioSv">
                    <div class="sb-nav-link-icon"><i class="fas fa-calendar"></i></div>
                    Horarios
                </a>
                <a class="nav-link" href="/ReportesCitasSv">
                    <div class="sb-nav-link-icon"><i class="fas fa-file-export"></i></div>
                    Reportes Citas
                </a>
            </div>
        </div>
    </nav>
</div>
