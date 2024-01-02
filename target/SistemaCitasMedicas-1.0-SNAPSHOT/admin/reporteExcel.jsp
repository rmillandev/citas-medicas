<%@page import="modelo.Cita"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="application-/vnd.ms-excel"%>

<%
    String nombreArchivo = "reporte-citas.xls";
    response.setHeader("Content-Disposition", "attachment;filename=" + nombreArchivo);
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Reporte Citas</title>
    </head>
    <body>
        <h1 style="text-align: center">Reporte Citas</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Fecha</th>
                    <th>Estado</th>
                    <th>Observaciones</th>
                    <th>Paciente</th>
                    <th>Medico</th>
                </tr>
            </thead>
            <tbody>

                <%
                    List<Cita> lista = (List) request.getSession().getAttribute("historialCitas");
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    if (lista != null) {
                        for (Cita result : lista) {
                            String fechaFormateada = formatoFecha.format(result.getFecha());

                %>
                <tr>
                    <td><%=fechaFormateada%></td>
                    <td><%=result.getEstado()%></td>
                    <td><%=result.getObservaciones()%></td>
                    <td><%=result.getPaciente().getNombres() + " " + result.getPaciente().getApellidos()%></td>
                    <td><%=result.getMedico().getNombres() + " " + result.getMedico().getApellidos()%></td>
                </tr>
                <%
                    }
                } else {

                %>
                <!-- Manejo de la lista nula -->
                <tr>
                    <td>No hay datos disponibles</td>
                </tr>
                <%                                                }
                %>

            </tbody>
        </table>
    </body>
</html>
