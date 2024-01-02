package logica;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Paciente;
import modelo.Usuario;
import persistencia.PacienteJpaController;
import persistencia.UsuarioJpaController;
import persistencia.exceptions.NonexistentEntityException;

public class LogicaPaciente {

    PacienteJpaController pacienteJpaController = new PacienteJpaController();
    UsuarioJpaController usuarioJpaController = new UsuarioJpaController();
    HashContrasena hashContrasena = new HashContrasena();

    TimeZone timeZone = TimeZone.getTimeZone("America/Bogota");
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public List<Paciente> obtenerPacientes() {
        return pacienteJpaController.findPacienteEntities();
    }

    public void crearPaciente(String numeroDocumento, String nombres, String apellidos, String direccion, String telefono, String email, String tipoDocumento, String genero, String fechaNacimiento, String contrasena) throws Exception {
        try {
            format.setTimeZone(timeZone);
            Date fechaFormateada = format.parse(fechaNacimiento);

            long numDoc = Long.parseLong(numeroDocumento);

            Usuario usuario = new Usuario();
            usuario.setNumeroDocumento(numDoc);
            usuario.setContrasena(hashContrasena.contrasenaEncriptada(contrasena));
            usuario.setNombres(nombres);
            usuario.setApellidos(apellidos);
            usuario.setRol("Paciente");

            usuarioJpaController.create(usuario);

            Paciente paciente = new Paciente();
            paciente.setNumeroDocumento(numDoc);
            paciente.setNombres(nombres);
            paciente.setApellidos(apellidos);
            paciente.setDireccion(direccion);
            paciente.setTelefono(telefono);
            paciente.setEmail(email);
            paciente.setTipoDocumento(tipoDocumento);
            paciente.setGenero(genero);
            paciente.setFechaNacimiento(fechaFormateada);
            paciente.setUsuario(usuario);

            pacienteJpaController.create(paciente);

        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public Paciente obtenerPacientePorId(int id) {
        return pacienteJpaController.findPaciente(id);
    }

    public void actualizarInformacion(String id, String nombres, String apellidos, String tipoDocumento, String numeroDocumento, String direccion, String telefono, String email, String fechaNacimiento, String genero) throws Exception {
        try {
            int idPaciente = Integer.parseInt(id);
            Paciente paciente = pacienteJpaController.findPaciente(idPaciente);

            if (paciente != null) {
                Usuario usuario = paciente.getUsuario();

                if (usuario != null) {
                    long numDoc = Long.parseLong(numeroDocumento);
                    Date fec = format.parse(fechaNacimiento);

                    paciente.setNombres(nombres);
                    paciente.setApellidos(apellidos);
                    paciente.setNumeroDocumento(numDoc);
                    paciente.setTipoDocumento(tipoDocumento);
                    paciente.setTelefono(telefono);
                    paciente.setDireccion(direccion);
                    paciente.setGenero(genero);
                    paciente.setEmail(email);
                    paciente.setFechaNacimiento(fec);

                    usuario.setNumeroDocumento(numDoc);
                    usuario.setNombres(nombres);
                    usuario.setApellidos(apellidos);

                    pacienteJpaController.edit(paciente);
                    usuarioJpaController.edit(usuario);
                }
            }

        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public void eliminarPaciente(int id) {
        try {
            Paciente paciente = pacienteJpaController.findPaciente(id);

            if (paciente != null) {
                Usuario usuario = paciente.getUsuario();

                pacienteJpaController.destroy(id);
                
                if (usuario != null) {
                    usuarioJpaController.destroy(usuario.getId());
                }
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(LogicaPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
