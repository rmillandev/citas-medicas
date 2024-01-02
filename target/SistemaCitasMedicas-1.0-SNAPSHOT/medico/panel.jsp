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
                        <h1 class="mt-4">Panel</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Panel</li>
                        </ol>
                        
                        <div class="row">
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-light text-dark mb-4">
                                    <div class="d-flex align-items-center">
                                        <div class="card-body"><i class="fas fa-notes-medical"></i> Citas</div>
                                        <div class="mx-3"></div>
                                    </div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                         <% if (usuario != null) {%>
                                        <a class="small text-dark stretched-link" href="/CitasMedicoSv?documento=<%=usuario.getNumeroDocumento()%>">Ver Detalles</a>
                                        <% } %>
                                        <div class="small text-dark"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-secondary text-white mb-4">
                                    <div class="d-flex align-items-center">
                                        <div class="card-body"><i class="fas fa-file-export"></i> Reportes</div>
                                        <div class="mx-3"></div>
                                    </div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                         <% if (usuario != null) {%>
                                        <a class="small text-white stretched-link" href="/ReportesCitasMedicoSv?documento=<%=usuario.getNumeroDocumento()%>">Ver Detalles</a>
                                        <% } %>
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
