package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Usuario;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-12-31T18:29:34")
@StaticMetamodel(Administrador.class)
public class Administrador_ { 

    public static volatile SingularAttribute<Administrador, String> apellidos;
    public static volatile SingularAttribute<Administrador, String> tipoDocumento;
    public static volatile SingularAttribute<Administrador, String> genero;
    public static volatile SingularAttribute<Administrador, Usuario> usuario;
    public static volatile SingularAttribute<Administrador, Integer> id;
    public static volatile SingularAttribute<Administrador, Long> numeroDocumento;
    public static volatile SingularAttribute<Administrador, String> telefono;
    public static volatile SingularAttribute<Administrador, String> email;
    public static volatile SingularAttribute<Administrador, String> nombres;

}