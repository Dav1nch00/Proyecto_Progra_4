package Controlador;

import modelo.Usuario;

public class ControladorRegistro {
    private ControladorUsuario controladorUsuario;

    public ControladorRegistro(ControladorUsuario controladorUsuario) {
        this.controladorUsuario = controladorUsuario;
    }

    public boolean registrarUsuario(String documentoIdentificacion, String nombre, String apellido, String correo, String direccion, String ciudad, String telefono, String contrasena, String rol) {
        Usuario usuario = new Usuario(documentoIdentificacion, nombre, apellido, correo, direccion, ciudad, telefono, contrasena, rol);
        controladorUsuario.agregarUsuario(usuario);
        return true;
    }
}
