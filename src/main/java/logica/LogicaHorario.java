package logica;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Horario;
import modelo.Medico;
import persistencia.HorarioJpaController;
import persistencia.MedicoJpaController;
import persistencia.exceptions.NonexistentEntityException;


public class LogicaHorario {
    
    HorarioJpaController horarioJpaController = new HorarioJpaController();
    MedicoJpaController medicoJpaController = new MedicoJpaController();
    
    TimeZone timeZone = TimeZone.getTimeZone("America/Bogota");
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    

    
    public List<Horario> obtenerHorarios(){
        return horarioJpaController.findHorarioEntities();
    }

    public void crearHorario(String fecha, String medico) throws Exception {
        try{
            format.setTimeZone(timeZone);
            Date fechaDateTime = format.parse(fecha);
            
            Medico med = medicoJpaController.findMedico(Integer.parseInt(medico));
            
            
            Horario horario = new Horario();
            horario.setFecha(fechaDateTime);
            horario.setMedico(med);
            
            horarioJpaController.create(horario);
        }catch(Exception ex){
            throw new Exception("Error al guardar en la base de datos" + ex.getMessage());
        }
    }

    public void eliminarHorario(int id) {
        try {
            horarioJpaController.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(LogicaHorario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Horario obtenerHorarioPorId(int id) {
        return horarioJpaController.findHorario(id);
    }

    public void editarHorario(Horario horario, String fecha, String medico) throws Exception {
        try{
            format.setTimeZone(timeZone);
            Date fechaDateTime = format.parse(fecha);
            
            Medico med = medicoJpaController.findMedico(Integer.parseInt(medico));
            
            horario.setFecha(fechaDateTime);
            horario.setMedico(med);
            
            horarioJpaController.edit(horario);
            
        }catch(Exception ex){
            throw new Exception("Error al actualiar el horario " + ex.getMessage());
        }
    }
    
}
