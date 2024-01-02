package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Cita;
import modelo.Usuario;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-12-31T18:29:34")
@StaticMetamodel(Paciente.class)
public class Paciente_ { 

    public static volatile SingularAttribute<Paciente, String> apellidos;
    public static volatile SingularAttribute<Paciente, String> tipoDocumento;
    public static volatile ListAttribute<Paciente, Cita> listaCitas;
    public static volatile SingularAttribute<Paciente, Date> fechaNacimiento;
    public static volatile SingularAttribute<Paciente, String> genero;
    public static volatile SingularAttribute<Paciente, String> direccion;
    public static volatile SingularAttribute<Paciente, Usuario> usuario;
    public static volatile SingularAttribute<Paciente, Integer> id;
    public static volatile SingularAttribute<Paciente, Long> numeroDocumento;
    public static volatile SingularAttribute<Paciente, String> telefono;
    public static volatile SingularAttribute<Paciente, String> email;
    public static volatile SingularAttribute<Paciente, String> nombres;

}