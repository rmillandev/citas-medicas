package logica;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Especialidad;
import persistencia.EspecialidadJpaController;
import persistencia.exceptions.NonexistentEntityException;

public class LogicaEspecialidad {

    EspecialidadJpaController especialidadJpaController = new EspecialidadJpaController();

    public void crearEspecialidad(String especialidad) throws Exception {
        try {
            Especialidad e = new Especialidad();
            e.setNombre(especialidad);

            especialidadJpaController.create(e);
        } catch (Exception e) {
            throw new Exception("Error al guardar en la base de datos", e);
        }
    }

    public List<Especialidad> obtenerEspecialidades() {
        return especialidadJpaController.findEspecialidadEntities();
    }

    public void eliminarEspecialidad(int id) {
        try {
            especialidadJpaController.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(LogicaEspecialidad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Especialidad obtenerEspecialidadPorId(int id) {
        return especialidadJpaController.findEspecialidad(id);
    }

    public void editarEspecialidad(Especialidad especialidad) {
        try {
            especialidadJpaController.edit(especialidad);
        } catch (Exception ex) {
            Logger.getLogger(LogicaEspecialidad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
