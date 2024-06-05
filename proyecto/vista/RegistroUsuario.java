package vista;

import Controlador.ControladorRegistro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroUsuario extends JFrame {


    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtDocumentoIdentificacion;
    private JTextField txtEmail;
    private JPasswordField txtContrasena;
    private JPasswordField txtVerificarContrasena;
    private JTextField txtTelefono;
    private JTextField txtDireccion;
    private JTextField txtCiudad;
    private JComboBox<String> cbRol;
    private ControladorRegistro controladorRegistro;
    private ProyectoFinal parent;

    public RegistroUsuario(ProyectoFinal parent, ControladorRegistro controladorRegistro) {
        this.parent = parent;
        this.controladorRegistro = controladorRegistro;

        setTitle("Registro de Usuario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(13, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        txtNombre = new JTextField(20);
        txtApellido = new JTextField(20);
        txtDocumentoIdentificacion = new JTextField(20);
        txtEmail = new JTextField(20);
        txtContrasena = new JPasswordField(20);
        txtVerificarContrasena = new JPasswordField(20);
        txtTelefono = new JTextField(20);
        txtDireccion = new JTextField(20);
        txtCiudad = new JTextField(20);
        cbRol = new JComboBox<>(new String[]{"usuario", "administrador"});

        mainPanel.add(new JLabel("Nombre:"));
        mainPanel.add(txtNombre);
        mainPanel.add(new JLabel("Apellido:"));
        mainPanel.add(txtApellido);
        mainPanel.add(new JLabel("Documento de Identificación:"));
        mainPanel.add(txtDocumentoIdentificacion);
        mainPanel.add(new JLabel("Email:"));
        mainPanel.add(txtEmail);
        mainPanel.add(new JLabel("Contraseña:"));
        mainPanel.add(txtContrasena);
        mainPanel.add(new JLabel("Verificar Contraseña:"));
        mainPanel.add(txtVerificarContrasena);
        mainPanel.add(new JLabel("Teléfono:"));
        mainPanel.add(txtTelefono);
        mainPanel.add(new JLabel("Dirección:"));
        mainPanel.add(txtDireccion);
        mainPanel.add(new JLabel("Ciudad:"));
        mainPanel.add(txtCiudad);
        mainPanel.add(new JLabel("Rol:"));
        mainPanel.add(cbRol);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });

        mainPanel.add(btnRegistrar);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }

    private void registrarUsuario() {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String documentoIdentificacion = txtDocumentoIdentificacion.getText();
        String email = txtEmail.getText();
        String contrasena = new String(txtContrasena.getPassword());
        String verificarContrasena = new String(txtVerificarContrasena.getPassword());
        String telefono = txtTelefono.getText();
        String direccion = txtDireccion.getText();
        String ciudad = txtCiudad.getText();
        String rol = (String) cbRol.getSelectedItem();

        if (!contrasena.equals(verificarContrasena)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean registrado = controladorRegistro.registrarUsuario(documentoIdentificacion, nombre, apellido, email, direccion, ciudad, telefono, contrasena, rol);

        if (registrado) {
            JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            parent.mostrarVentanaInicioSesion();
            dispose();
        }
    }
}
