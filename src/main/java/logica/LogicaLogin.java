package logica;

import modelo.Usuario;
import persistencia.UsuarioJpaController;

public class LogicaLogin {

    UsuarioJpaController usuarioJpaController = new UsuarioJpaController();
    HashContrasena hashContrasena = new HashContrasena();

    public Usuario validarUsuario(String numeroDocumento, String contrasena) throws Exception {
        try {
            long numDoc = Long.parseLong(numeroDocumento);

            Usuario usuario = usuarioJpaController.FindUserByUsername(numDoc);

            if (usuario != null && hashContrasena.verificarContrasena(contrasena, usuario.getContrasena())) {
                return usuario; // Devuelve el objeto Usuario si la validación es exitosa
            } else {
                return null; // Devuelve null si no se encuentra el usuario o la contraseña es incorrecta
            }

        } catch (NumberFormatException ex) {
            // Manejar el error si el número de documento no es válido
            throw new Exception("Número de documento no válido" + ex);
        } catch (Exception ex) {
            // Manejar cualquier otra excepción que pueda ocurrir
            throw new Exception("Error al validar el usuario" + ex);
        }
    }

}
