package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Medico;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-12-31T18:29:34")
@StaticMetamodel(Horario.class)
public class Horario_ { 

    public static volatile SingularAttribute<Horario, Date> fecha;
    public static volatile SingularAttribute<Horario, Medico> medico;
    public static volatile SingularAttribute<Horario, Integer> id;

}