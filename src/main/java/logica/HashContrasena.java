package logica;

import org.mindrot.jbcrypt.BCrypt;

public class HashContrasena {

    public String contrasenaEncriptada(String contrasena) {
        return BCrypt.hashpw(contrasena, BCrypt.gensalt());
    }

    public boolean verificarContrasena(String contrasenaPlana, String contrasenaEncriptadaAlmacenada) {
        return BCrypt.checkpw(contrasenaPlana, contrasenaEncriptadaAlmacenada);
    }

}
