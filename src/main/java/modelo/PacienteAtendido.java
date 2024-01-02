package modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PacienteAtendido implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "observaciones")
    private String observaciones;
    
    @Column(name = "medicamentos")
    private String medicamentos;
    
    @Column(name = "instruccion_medicamento")
    private String instruccion_medicamento;
    
    @Column(name = "documento_paciente")
    private long documento_paciente;
    
    @Column(name = "documento_medico")
    private long documento_medico;

    public PacienteAtendido() {
    }

    public PacienteAtendido(int id, String observaciones, String medicamentos, String instruccion_medicamento, long documento_paciente, long documento_medico) {
        this.id = id;
        this.observaciones = observaciones;
        this.medicamentos = medicamentos;
        this.instruccion_medicamento = instruccion_medicamento;
        this.documento_paciente = documento_paciente;
        this.documento_medico = documento_medico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getInstruccion_medicamento() {
        return instruccion_medicamento;
    }

    public void setInstruccion_medicamento(String instruccion_medicamento) {
        this.instruccion_medicamento = instruccion_medicamento;
    }

    public long getDocumento_paciente() {
        return documento_paciente;
    }

    public void setDocumento_paciente(long documento_paciente) {
        this.documento_paciente = documento_paciente;
    }

    public long getDocumento_medico() {
        return documento_medico;
    }

    public void setDocumento_medico(long documento_medico) {
        this.documento_medico = documento_medico;
    }
    
}
