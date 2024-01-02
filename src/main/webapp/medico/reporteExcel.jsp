<%@page import="modelo.PacienteAtendido"%>
<%@page import="java.util.List"%>
<%@page contentType="application-/vnd.ms-excel"%>

<%
    String nombreArchivo = "citas-atendidas.xls";
    response.setHeader("Content-Disposition", "attachment;filename=" + nombreArchivo);
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Citas Atendidas</title>
    </head>
    <body>
        <h1 style="text-align: center">Reporte Citas</h1>
        <table border="1">
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

                    if (lista != null) {
                        for (PacienteAtendido result : lista) {

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
                } else {

                %>
                <!-- Manejo de la lista nula -->
                <tr>
                    <td colspan="3">La lista de sedes está vacía o es nula.</td>
                </tr>
                <%                                                }
                %>

            </tbody>
        </table>
    </body>
</html>