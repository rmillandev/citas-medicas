package logica;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Sede;
import persistencia.SedeJpaController;
import persistencia.exceptions.NonexistentEntityException;

public class LogicaSede {

    SedeJpaController sedeJpaController = new SedeJpaController();

    public List<Sede> obtenerSedes() {
        return sedeJpaController.findSedeEntities();
    }

    public void crearSede(String nombreSede) throws Exception {
        try {
            Sede sede = new Sede();
            sede.setNombre(nombreSede);

            sedeJpaController.create(sede);
        } catch (Exception e) {
            throw new Exception("Error al guardar en la base de datos", e);
        }
    }

    public void eliminarSede(int id) {
        try {
            sedeJpaController.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(LogicaSede.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Sede obtenerSedePorId(int id) {
        return sedeJpaController.findSede(id);
    }

    public void editarSede(Sede sede) {
        try {
            sedeJpaController.edit(sede);
        } catch (Exception ex) {
            Logger.getLogger(LogicaSede.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
