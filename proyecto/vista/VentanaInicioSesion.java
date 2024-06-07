package vista;

import Controlador.ControladorInicioSesion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaInicioSesion extends JFrame {
    private JTextField txtmail;
    private JPasswordField txtContrasena;
    private ControladorInicioSesion controladorInicioSesion;

    public VentanaInicioSesion(ControladorInicioSesion controladorInicioSesion) {
        this.controladorInicioSesion = controladorInicioSesion;

        setTitle("Inicio de Sesión");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        txtmail = new JTextField(20);
        txtContrasena = new JPasswordField(20);

        mainPanel.add(new JLabel("Correo:"));
        mainPanel.add(txtmail);
        mainPanel.add(new JLabel("Contraseña:"));
        mainPanel.add(txtContrasena);

        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });

        mainPanel.add(btnIniciarSesion);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }

    private void iniciarSesion() {
        String mail = txtmail.getText();
        String contrasena = new String(txtContrasena.getPassword());
        controladorInicioSesion.iniciarSesion(mail, contrasena);
    }
}
