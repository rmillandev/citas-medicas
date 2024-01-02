<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Login - Sistema De Citas Medicas</title>
        <link rel="icon" href="/assets/medical-care.png" type="image/x-icon">
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-5">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">Bienvenidos</h3></div>
                                    <div class="card-body">
                                        <form action="/LoginSv" method="POST">
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="txtNumeroDocumento" name="txtNumeroDocumento" type="number" placeholder="Numero de Documento" required />
                                                <label for="txtNumeroDocumento">Numero Documento</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="txtContrasena" name="txtContrasena" type="password" placeholder="Contraseña" required />
                                                <label for="txtContrasena">Contraseña</label>
                                            </div>
                                            <div class="d-flex align-items-center justify-content-between mt-4 mb-0">
                                                <div class="small"><a href="registrarse.jsp">Registrarse</a></div>
                                                <button class="btn btn-primary" type="submit" >Entrar</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>    
                        </div>     
                        <div class="row justify-content-center text-center mt-3">
                            <div class="col-lg-5">
                                <button class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#modalCredencialesPrueba">Click para mostrar los Usuarios de prueba</button>
                            </div>
                        </div>
                    </div>
                </main>
            </div>

        </div>


        <div class="modal fade" id="modalCredencialesPrueba" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Usuarios de Prueba</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <p>Hay 3 usuarios con diferentes roles(Administrador, Medico y Paciente), si da en la opcion de registrarse su rol sera Paciente</p>
                        <ul>
                            <li>Administrador:
                                <p>numero documento: 181231922</p>
                                <p>contraseña: admin123</p>
                            </li>
                            <li>Medico:
                                <p>numero documento: 118239123</p>
                                <p>contraseña: maria123</p>
                            </li>
                            <li>Paciente:
                                <p>numero documento: 107265123</p>
                                <p>contraseña: julio123</p>
                            </li>
                        </ul>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>
