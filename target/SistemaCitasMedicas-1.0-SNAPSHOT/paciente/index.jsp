<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Inicio - Sistema de Citas Medicas</title>
        <link rel="icon" href="/assets/medical-care.png" type="image/x-icon">
        <link rel="stylesheet" href="/css/styles.css">
        <link rel="stylesheet" href="csspaciente/style.css">
    </head>
    <body>

        <%@include file="/componentes/navpaciente.jsp" %>

        <h3 class="title">BIENVENIDOS</h3>

        <div class="container" style="width: 90%">
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
        </div>

        <section class="container-main">
            <h5 class="text-center">SELECCIONA EL SERVICIO QUE DESEAS</h5>
            <article class="container-box">
                <div class="box">
                    <figure class="box-img box-img1">
                        <img src="img/calendario.png" alt="">
                    </figure>
                    <p class="title-box">MIS CITAS</p>
                    <p class="description-box">
                        Agenda, consulta o cancela tu cita programada
                    </p>
                    <a href="asignacion-citas.jsp" class="ingresar">Ingresar</a>
                </div>
                <div class="box">
                    <figure class="box-img box-img2">
                        <img src="img/configuracion.png" alt="">
                    </figure>
                    <p class="title-box">GESTIONAR PERFIL</p>
                    <p class="description-box">
                        Aqui podras actualizar tu informacion personal
                    </p>
                    <% if (usuario != null) {%>
                    <a href="/GestionarPerfilSv?documento=<%=usuario.getNumeroDocumento()%>" class="ingresar">Ingresar</a>
                     <% }%>
                </div>

            </article>
        </section>

        <%@include file="/componentes/script-paciente.jsp" %>
        <%@include file="/componentes/script-exito.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    </body>
</html>
