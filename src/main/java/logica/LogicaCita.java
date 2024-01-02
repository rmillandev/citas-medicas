package logica;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.Cita;
import modelo.CitasAgendadas;
import modelo.Medico;
import modelo.MedicoDTO;
import modelo.Paciente;
import modelo.PacienteAtendido;
import persistencia.CitaJpaController;
import persistencia.CitasAgendadasJpaController;
import persistencia.MedicoJpaController;
import persistencia.PacienteAtendidoJpaController;
import persistencia.PacienteJpaController;

public class LogicaCita {

    CitaJpaController citaJpaController = new CitaJpaController();
    MedicoJpaController medicoJpaController = new MedicoJpaController();
    PacienteJpaController pacienteJpaController = new PacienteJpaController();
    PacienteAtendidoJpaController pacienteAtendidoJpaController = new PacienteAtendidoJpaController();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    CitasAgendadasJpaController citasAgendadasJpaController = new CitasAgendadasJpaController();

    public List<MedicoDTO> obtenerResultadosBusqueda(int id) {
        return medicoJpaController.getMedicoDTO(id);
    }

    public int pacienteId(long documentoPaciente) {
        return pacienteJpaController.obtenerIdPacientePorNumeroDocumento(documentoPaciente);
    }

    public int medicoId(long documentoMedico) {
        return medicoJpaController.obtenerIdMedicoPorNumeroDocumento(documentoMedico);
    }

    public List<Cita> obtenerCitasPorIdPaciente(int id) {
        return citaJpaController.obtenerCitasPorIdPaciente(id);
    }

    public List<Cita> obtenerCitasPorIdMedico(int id) {
        return citaJpaController.obtenerCitasPorIdMedico(id);
    }
   
    public List<PacienteAtendido> historialCitasPorMedico(long documento){
        return pacienteAtendidoJpaController.historialCitasPorMedico(documento);
    }

    public Cita obtenerCitaPorId(int id) {
        return citaJpaController.findCita(id);
    }

    public void agendarCita(String fecha, String documentoPaciente, String idMedico) throws Exception {
        try {
            long docPac = Long.parseLong(documentoPaciente);
            int idMed = Integer.parseInt(idMedico);
            Date fec = format.parse(fecha);

            int idPac = pacienteId(docPac);

            if (idPac != 0) {
                boolean citaExistente = citasAgendadasJpaController.existeCitaAgendada(idPac, fec);
                System.out.println(citaExistente);
                if (citaExistente) {
                    throw new Exception("La cita ya esta agendada.");
                }

                Paciente paciente = pacienteJpaController.findPaciente(idPac);
                Medico medico = medicoJpaController.findMedico(idMed);
                Cita cita = new Cita();
                cita.setEstado("Pendiente");
                cita.setFecha(fec);
                cita.setObservaciones("");
                cita.setPaciente(paciente);
                cita.setMedico(medico);
                citaJpaController.create(cita);

                CitasAgendadas citasAgendadas = new CitasAgendadas();

                citasAgendadas.setIdentificador(idMed);
                citasAgendadas.setFecha(fec);

                citasAgendadasJpaController.create(citasAgendadas);

            } else {
                throw new Exception("No se encontró el paciente con el documento especificado.");
            }

        } catch (Exception ex) {
            throw new Exception("Error al agendar la cita " + ex.getMessage());
        }
    }

    public void cancelarCita(int id, String observaciones) throws Exception {
        try {
            Cita cita = citaJpaController.findCita(id);

            if (cita != null) {
                cita.setObservaciones(observaciones);
                cita.setEstado("Cancelada");

                citaJpaController.edit(cita);
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public List<Cita> historialCitas() {
        return citaJpaController.findCitaEntities();
    }

    public void atenderPaciente(int id, String observaciones, String medicamentos, String instrucciones, long docPaciente, long docMedico) throws Exception {
        try {
            Cita cita = citaJpaController.findCita(id);

            if (cita != null) {
                cita.setObservaciones(observaciones);
                cita.setEstado("Atendido");

                citaJpaController.edit(cita);

                PacienteAtendido pacienteAtendido = new PacienteAtendido();
                pacienteAtendido.setObservaciones(observaciones);
                pacienteAtendido.setMedicamentos(medicamentos);
                pacienteAtendido.setInstruccion_medicamento(instrucciones);
                pacienteAtendido.setDocumento_paciente(docPaciente);
                pacienteAtendido.setDocumento_medico(docMedico);
                
                pacienteAtendidoJpaController.create(pacienteAtendido);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

}
