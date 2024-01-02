package logica;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Especialidad;
import modelo.Medico;
import modelo.Sede;
import modelo.Usuario;
import persistencia.EspecialidadJpaController;
import persistencia.MedicoJpaController;
import persistencia.SedeJpaController;
import persistencia.UsuarioJpaController;
import persistencia.exceptions.NonexistentEntityException;

public class LogicaMedico {

    MedicoJpaController medicoJpaController = new MedicoJpaController();
    UsuarioJpaController usuarioJpaController = new UsuarioJpaController();
    HashContrasena hashContrasena = new HashContrasena();

    SedeJpaController sedeJpaController = new SedeJpaController();
    EspecialidadJpaController especialidadJpaController = new EspecialidadJpaController();

    public void crearMedico(String numeroDocumento, String nombres, String apellidos, String telefono, String email, String genero, String tipoDocumento, String sede, String especialidad, String contrasena) throws Exception {
        try {
            long numDoc = Long.parseLong(numeroDocumento);

            Usuario usuario = new Usuario();
            usuario.setNumeroDocumento(numDoc);
            usuario.setContrasena(hashContrasena.contrasenaEncriptada(contrasena));
            usuario.setNombres(nombres);
            usuario.setApellidos(apellidos);
            usuario.setRol("Medico");

            usuarioJpaController.create(usuario);

            Sede sedeId = sedeJpaController.findSede(Integer.parseInt(sede));
            Especialidad especialidadId = especialidadJpaController.findEspecialidad(Integer.parseInt(especialidad));

            Medico medico = new Medico();
            medico.setNumeroDocumento(numDoc);
            medico.setNombres(nombres);
            medico.setApellidos(apellidos);
            medico.setTelefono(telefono);
            medico.setEmail(email);
            medico.setGenero(genero);
            medico.setTipoDocumento(tipoDocumento);
            medico.setSede(sedeId);
            medico.setEspecialidad(especialidadId);
            medico.setUsuario(usuario);

            medicoJpaController.create(medico);

        } catch (Exception ex) {
            throw new Exception("Error al guardar en la base de datos", ex);
        }
    }

    public List<Medico> obtenerMedicos() {
        return medicoJpaController.findMedicoEntities();
    }

    public void eliminarMedico(int id) {
        try {
            Medico medico = medicoJpaController.findMedico(id);

            if (medico != null) {
                Usuario usuario = medico.getUsuario();

                medicoJpaController.destroy(id);

                if (usuario != null) {
                    usuarioJpaController.destroy(usuario.getId());
                }

            }

        } catch (NonexistentEntityException ex) {
            Logger.getLogger(LogicaMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Medico obtenerMedicoPorId(int id) {
        return medicoJpaController.findMedico(id);
    }

    public void editarMedico(Medico medico, String numeroDocumento, String nombres, String apellidos, String telefono, String email, String genero, String tipoDocumento, String sede, String especialidad) throws Exception {
        try {
            Medico med = medicoJpaController.findMedico(medico.getId());

            if (med != null) {
                Usuario usuario = med.getUsuario();

                if (usuario != null) {
                    medico.setNumeroDocumento(Long.parseLong(numeroDocumento));
                    medico.setNombres(nombres);
                    medico.setApellidos(apellidos);
                    medico.setTelefono(telefono);
                    medico.setEmail(email);
                    medico.setGenero(genero);
                    medico.setTipoDocumento(tipoDocumento);

                    Sede sedeId = sedeJpaController.findSede(Integer.parseInt(sede));
                    Especialidad especialidadId = especialidadJpaController.findEspecialidad(Integer.parseInt(especialidad));

                    medico.setSede(sedeId);
                    medico.setEspecialidad(especialidadId);

                    usuario.setNumeroDocumento(Long.parseLong(numeroDocumento));
                    usuario.setNombres(nombres);
                    usuario.setApellidos(apellidos);
                    
                    medicoJpaController.edit(medico);
                    usuarioJpaController.edit(usuario);
                }
            }

        } catch (Exception ex) {
            throw new Exception("Error al actualizar en la base de datos", ex);
        }
    }

}
