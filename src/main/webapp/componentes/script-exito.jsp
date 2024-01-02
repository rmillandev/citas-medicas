<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>
    // Código JavaScript para eliminar el mensaje después de 5 segundos (5000 milisegundos)
    setTimeout(function () {
        var mensajeExito = document.getElementById("mensajeExito");
        mensajeExito.style.display = "none";
    <% // Elimina el mensaje de éxito de la sesión después de ocultarlo en la página 
                request.getSession().removeAttribute("mensaje");%>

    }, 3000);
    
    setTimeout(function () {
        var mensajeExito = document.getElementById("mensajeError");
        mensajeExito.style.display = "none";
    <% // Elimina el mensaje de éxito de la sesión después de ocultarlo en la página 
                request.getSession().removeAttribute("error");%>

    }, 3000);
</script>