package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Medico;
import modelo.Paciente;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-12-31T18:29:34")
@StaticMetamodel(Cita.class)
public class Cita_ { 

    public static volatile SingularAttribute<Cita, Date> fecha;
    public static volatile SingularAttribute<Cita, String> estado;
    public static volatile SingularAttribute<Cita, Paciente> paciente;
    public static volatile SingularAttribute<Cita, Medico> medico;
    public static volatile SingularAttribute<Cita, String> observaciones;
    public static volatile SingularAttribute<Cita, Integer> id;

}