
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script >
    const perfilImg = document.querySelector(".perfil")
    const loguot = document.querySelector(".loguot")


    perfilImg.addEventListener("click", () => loguot.classList.toggle("active"))
</script>