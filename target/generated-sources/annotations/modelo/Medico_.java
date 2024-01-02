package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Cita;
import modelo.Especialidad;
import modelo.Horario;
import modelo.Sede;
import modelo.Usuario;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-12-31T18:29:34")
@StaticMetamodel(Medico.class)
public class Medico_ { 

    public static volatile SingularAttribute<Medico, String> apellidos;
    public static volatile SingularAttribute<Medico, Sede> sede;
    public static volatile SingularAttribute<Medico, Especialidad> especialidad;
    public static volatile SingularAttribute<Medico, String> nombres;
    public static volatile ListAttribute<Medico, Horario> listaHorario;
    public static volatile SingularAttribute<Medico, String> tipoDocumento;
    public static volatile ListAttribute<Medico, Cita> listaCitas;
    public static volatile SingularAttribute<Medico, String> genero;
    public static volatile SingularAttribute<Medico, Usuario> usuario;
    public static volatile SingularAttribute<Medico, Integer> id;
    public static volatile SingularAttribute<Medico, Long> numeroDocumento;
    public static volatile SingularAttribute<Medico, String> telefono;
    public static volatile SingularAttribute<Medico, String> email;

}