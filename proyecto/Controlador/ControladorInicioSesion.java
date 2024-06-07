package Controlador;

import modelo.Usuario;
import vista.VentanaOpcionesAdministrador;
import vista.VentanaOpcionesCliente;

import javax.print.DocFlavor.STRING;
import javax.swing.*;
import java.util.List;

public class ControladorInicioSesion {
    private List<Usuario> listaUsuarios;
    private ControladorHabitacion controladorHabitacion;
    private ControladorReserva controladorReservas;
    private ControladorUsuario controladorUsuario;

    public ControladorInicioSesion(List<Usuario> listaUsuarios,ControladorHabitacion controladorHabitacion,ControladorReserva ControladorReservas) {
        this.listaUsuarios = listaUsuarios;
        this.controladorHabitacion = controladorHabitacion;
        this.controladorReservas = ControladorReservas;
    }

    public void iniciarSesion(String mail, String contrasena) {
        boolean encontrado = false;
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getCorreo().equals(mail) && usuario.getContrasena().equals(contrasena)) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso", "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
                abrirJFrameInicio(usuario, mail);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirJFrameInicio(Usuario usuario, String mail) {
        if (usuario != null) {
            if (usuario.getRol().equals("administrador")) {
                abrirJFrameOpcionesAdministrador();
            } else {
                abrirJFrameOpcionesCliente(mail);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error al obtener información del usuario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirJFrameOpcionesAdministrador() {
        VentanaOpcionesAdministrador opcionesAdmin = new VentanaOpcionesAdministrador(controladorHabitacion);
        opcionesAdmin.setVisible(true);
    }

    private void abrirJFrameOpcionesCliente(String mail) {
        VentanaOpcionesCliente opcionesCliente = new VentanaOpcionesCliente(controladorUsuario, controladorReservas,controladorHabitacion, mail);
        opcionesCliente.setVisible(true);
    }
}
