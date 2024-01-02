<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Register - SB Admin</title>
        <link href="css/styles.css" rel="stylesheet" />
        <link rel="icon" href="/assets/medical-care.png" type="image/x-icon">
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-7">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">Crear Cuenta</h3></div>
                                    <div class="card-body">
                                        <form action="/PacienteSv" method="POST">
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="txtNombres" name="txtNombres" type="text" placeholder="Nombres" pattern="[A-Za-z\s]+" title="Solo puede contener letras y espacios" required />
                                                        <label for="txtNombres">Nombres</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-floating">
                                                        <input class="form-control" id="txtApellidos" name="txtApellidos" type="text" placeholder="Apellidos" pattern="[A-Za-z\s]+" title="Solo puede contener letras y espacios" required />
                                                        <label for="txtApellidos">Apellidos</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="txtNumeroDocumento" name="txtNumeroDocumento" type="text" placeholder="Numero de Documento" pattern="[0-9]{6,10}" title="El número debe tener entre 6 y 10 dígitos" required />
                                                        <label for="txtNumeroDocumento">Numero Documento</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="txtDireccion" name="txtDireccion" type="text" placeholder="Direccion" required />
                                                        <label for="txtDireccion">Direccion</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="txtTelefono" name="txtTelefono" type="text" placeholder="Telefono" pattern="[0-9]{6,10}" title="El número debe tener entre 6 y 10 dígitos" required />
                                                        <label for="txtTelefono">Telefono</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="txtEmail" name="txtEmail" type="email" placeholder="Email" required />
                                                        <label for="txtEmail">Email</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <select class="form-select" id="txtTipoDocumento" name="txtTipoDocumento" aria-label="Default select example">
                                                            <option value="CC">CC</option>
                                                            <option value="TI">TI</option>
                                                            <option value="CE">CE</option>
                                                        </select>
                                                        <label for="txtTipoDocumento">Tipo Documento</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <select class="form-select" id="txtGenero" name="txtGenero" aria-label="Default select example">
                                                            <option value="Masculino">Masculino</option>
                                                            <option value="Femenino">Femenino</option>
                                                        </select>
                                                        <label for="txtGenero">Genero</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="txtFechaNacimiento" name="txtFechaNacimiento" type="date" placeholder="Fecha Nacimiento" required />
                                                        <label for="txtFechaNacimiento">Fecha Nacimiento</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="txtContrasena" name="txtContrasena" type="password" placeholder="Contrasena" pattern=".{6,15}" 
                                                               title="La contraseña debe tener entre 6 y 15 caracteres" required />
                                                        <label for="txtContrasena">Contrasena</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="mt-4 mb-0">
                                                <div class="d-grid"><button type="submit" class="btn btn-primary btn-block" href="">Crear Cuenta</button></div>
                                            </div>
                                        </form>
                                    </div>

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

                                    <div class="card-footer text-center py-3">
                                        <div class="small"><a href="login.jsp">Ya tienes cuenta? Ir al login</a></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>

            <script>
                // Código JavaScript para eliminar el mensaje después de 5 segundos (5000 milisegundos)
                setTimeout(function () {
                    var mensajeExito = document.getElementById("mensajeExito");
                    mensajeExito.style.display = "none";
                <% // Elimina el mensaje de éxito de la sesión después de ocultarlo en la página 
                    request.getSession().removeAttribute("mensaje");%>

                }, 3000);
            </script>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
            <script src="js/scripts.js"></script>
    </body>
</html>
