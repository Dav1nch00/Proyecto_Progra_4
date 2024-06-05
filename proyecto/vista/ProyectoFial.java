package vista;

import Controlador.RoomManager;
import Controlador.ControladorUsuario;
import Controlador.controladorReservas;
import modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProyectoFinal extends JFrame {
    private ControladorUsuario controladorUsuario;
    private RoomManager controladorHabitacion;
    private controladorReservas controladorReservas;

    public ProyectoFinal(ControladorUsuario controladorUsuario, RoomManager controladorHabitacion, controladorReservas ControladorReservas) {
        this.controladorUsuario = controladorUsuario;
        this.controladorHabitacion = controladorHabitacion;
        this.controladorReservas = ControladorReservas;
        this.controladorUsuario.setProyectoFinal(this);

        setTitle("Bienvenido a My Hotel");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        JButton btnRegistrar = new JButton("Registrarse");

        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaInicioSesion();
            }
        });

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaRegistro();
            }
        });

        mainPanel.add(btnIniciarSesion);
        mainPanel.add(btnRegistrar);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void mostrarVentanaInicioSesion() {
        controladorUsuario.iniciarSesion(controladorHabitacion,controladorReservas);
        setVisible(false); // Ocultar la ventana actual
    }

    private void mostrarVentanaRegistro() {
        controladorUsuario.mostrarVentanaRegistro();
    }

    public List<Usuario> getRegistro() {
        return controladorUsuario.getUsuarios();
    }

    public static void main(String[] args) {
        ControladorUsuario controladorUsuario = new ControladorUsuario();
        RoomManager controladorHabitacion = new RoomManager();
        controladorReservas ControladorReservas = new controladorReservas();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ProyectoFinal(controladorUsuario, controladorHabitacion, ControladorReservas);
            }
        });
    }
}
