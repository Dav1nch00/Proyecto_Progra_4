package Controlador;

import modelo.Usuario;
import vista.VentanaOpcionesAdministrador;
import vista.VentanaOpcionesCliente;


import javax.swing.*;
import java.util.List;

public class ControladorInicioSesion {
    private List<Usuario> listaUsuarios;
    private RoomManager controladorHabitacion;
    private controladorReservas controladorReservas;

    public ControladorInicioSesion(List<Usuario> listaUsuarios,RoomManager controladorHabitacion,controladorReservas ControladorReservas) {
        this.listaUsuarios = listaUsuarios;
        this.controladorHabitacion = controladorHabitacion;
        this.controladorReservas = ControladorReservas;
    }

    public void iniciarSesion(String mail, String contrasena) {
        boolean encontrado = false;
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getCorreo().equals(mail) && usuario.getContrasena().equals(contrasena)) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso", "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
                abrirJFrameInicio(usuario);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirJFrameInicio(Usuario usuario) {
        if (usuario != null) {
            if (usuario.getRol().equals("administrador")) {
                abrirJFrameOpcionesAdministrador();
            } else {
                abrirJFrameOpcionesCliente();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error al obtener información del usuario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirJFrameOpcionesAdministrador() {
        VentanaOpcionesAdministrador opcionesAdmin = new VentanaOpcionesAdministrador(controladorHabitacion);
        opcionesAdmin.setVisible(true);
    }

    private void abrirJFrameOpcionesCliente() {
        VentanaOpcionesCliente opcionesCliente = new VentanaOpcionesCliente(controladorReservas,controladorHabitacion);
        opcionesCliente.setVisible(true);
    }
}
