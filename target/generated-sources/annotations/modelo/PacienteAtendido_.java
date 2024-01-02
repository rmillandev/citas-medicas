package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-12-31T18:29:34")
@StaticMetamodel(PacienteAtendido.class)
public class PacienteAtendido_ { 

    public static volatile SingularAttribute<PacienteAtendido, Long> documento_medico;
    public static volatile SingularAttribute<PacienteAtendido, Long> documento_paciente;
    public static volatile SingularAttribute<PacienteAtendido, String> instruccion_medicamento;
    public static volatile SingularAttribute<PacienteAtendido, String> observaciones;
    public static volatile SingularAttribute<PacienteAtendido, Integer> id;
    public static volatile SingularAttribute<PacienteAtendido, String> medicamentos;

}