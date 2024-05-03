import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Proyecto_Final extends JFrame {

    private List<Usuario> registro = new ArrayList<>(); 

    public List<Usuario> getRegistro() {
        return registro;
    }

    public Proyecto_Final() {
        setTitle("Bienvenido a My Hotel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    private void mostrarVentanaInicioSesion() {
        VentanaInicioSesion ventanaInicioSesion = new VentanaInicioSesion(registro);
        ventanaInicioSesion.setVisible(true);
        setVisible(false); // Ocultar la ventana actual
    }

    private void mostrarVentanaRegistro() {
        RegistroUsuario registroUsuario = new RegistroUsuario(this);
        registroUsuario.setVisible(true);
        setVisible(false); // Ocultar la ventana actual
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Proyecto_Final();
            }
        });
    }
}



class VentanaInicioSesion extends JFrame {
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private List<Usuario> listaUsuarios; 

    public VentanaInicioSesion(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;

        setTitle("Iniciar Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel correoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblCorreo = new JLabel("Correo:");
        txtCorreo = new JTextField(20);
        correoPanel.add(lblCorreo);
        correoPanel.add(txtCorreo);

        JPanel contrasenaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblContrasena = new JLabel("Contraseña:");
        txtContrasena = new JPasswordField(20);
        contrasenaPanel.add(lblContrasena);
        contrasenaPanel.add(txtContrasena);

        JPanel botonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnIniciarSesion = new JButton("Iniciar Sesión");

        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = txtCorreo.getText();
                String contrasena = new String(txtContrasena.getPassword());

                
                if (validarInicioSesion(correo, contrasena)) {
                    
                    abrirJFrameInicio();
                } else {
                    JOptionPane.showMessageDialog(VentanaInicioSesion.this, "Correo electrónico o contraseña incorrectos", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botonPanel.add(btnIniciarSesion);

        mainPanel.add(correoPanel);
        mainPanel.add(contrasenaPanel);
        mainPanel.add(botonPanel);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }

    private boolean validarInicioSesion(String correo, String contrasena) {
        
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getCorreo().equals(correo) && usuario.getContrasena().equals(contrasena)) {
                return true; // Inicio de sesión exitoso
            }
        }
        return false; // Inicio de sesión fallido
    }

    private void abrirJFrameInicio() {
        
        Usuario usuario = obtenerUsuario(txtCorreo.getText());
    
        
        if (usuario != null) {
            if (usuario.getRol().equals("Administrador")) {
                
                abrirJFrameOpcionesAdministrador();
            } else {
                
                abrirJFrameOpcionesCliente();
            }
        } else {
            JOptionPane.showMessageDialog(VentanaInicioSesion.this, "Error al obtener información del usuario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private Usuario obtenerUsuario(String correo) {
        
        for (Usuario u : listaUsuarios) {
            if (u.getCorreo().equals(correo)) {
                return u;
            }
        }
        return null; // Usuario no encontrado
    }
    
    private void abrirJFrameOpcionesAdministrador() {
        JFrame opcionesAdmin = new JFrame("Opciones del Administrador");
        opcionesAdmin.setSize(400, 300);
        opcionesAdmin.setLocationRelativeTo(null);
        
        
        JPanel panelAdmin = new JPanel();
        panelAdmin.setLayout(new GridLayout(6, 1, 10, 10)); 
        
        
        JButton btnVerificarDisponibilidad = new JButton("Verificar Disponibilidad");
        JButton btnAgregarHabitacion = new JButton("Agregar Habitación al Inventario");
        JButton btnEditarHabitacion = new JButton("Editar Habitación en el Inventario");
        JButton btnEliminarHabitacion = new JButton("Eliminar Habitación del Inventario");
        
        
        panelAdmin.add(btnVerificarDisponibilidad);
        panelAdmin.add(btnAgregarHabitacion);
        panelAdmin.add(btnEditarHabitacion);
        panelAdmin.add(btnEliminarHabitacion);
        
        btnVerificarDisponibilidad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaEnBlanco("Verificar Disponibilidad");
            }
        });
    
        
        btnAgregarHabitacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaEnBlanco("Agregar Habitación al Inventario");
            }
        });
    
        
        btnEditarHabitacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaEnBlanco("Editar Habitación en el Inventario");
            }
        });
    
        
        btnEliminarHabitacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaEnBlanco("Eliminar Habitación del Inventario");
            }
        });
        
        
        opcionesAdmin.add(panelAdmin);
        
        opcionesAdmin.setVisible(true);
    
    }
    
    private void abrirJFrameOpcionesCliente() {
        JFrame opcionesCliente = new JFrame("Opciones del Cliente");
        opcionesCliente.setSize(400, 300);
        opcionesCliente.setLocationRelativeTo(null);
        
        
        JPanel panelCliente = new JPanel();
        panelCliente.setLayout(new GridLayout(6, 1, 10, 10)); 
        
        
        JButton btnBuscarHabitaciones = new JButton("Buscar Habitaciones Disponibles");
        JButton btnVerDetalles = new JButton("Ver Detalles de Habitación");
        JButton btnRealizarReserva = new JButton("Realizar una Reserva");
        JButton btnModificarReserva = new JButton("Modificar Reserva");
        JButton btnCancelarReserva = new JButton("Cancelar Reserva");
        JButton btnVerHistorial = new JButton("Ver Historial de Reservas");
        
       
        panelCliente.add(btnBuscarHabitaciones);
        panelCliente.add(btnVerDetalles);
        panelCliente.add(btnRealizarReserva);
        panelCliente.add(btnModificarReserva);
        panelCliente.add(btnCancelarReserva);
        panelCliente.add(btnVerHistorial);
        
        btnBuscarHabitaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaEnBlanco("Buscar Habitaciones Disponibles");
            }
        });
    
        
        btnVerDetalles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaEnBlanco("Ver Detalles de Habitación");
            }
        });
    
        
        btnRealizarReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaEnBlanco("Realizar una Reserva");
            }
        });
    
        
        btnModificarReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaEnBlanco("Modificar Reserva");
            }
        });
    
        
        btnCancelarReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaEnBlanco("Cancelar Reserva");
            }
        });
    
        
        btnVerHistorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaEnBlanco("Ver Historial de Reservas");
            }
        });

        
        opcionesCliente.add(panelCliente);
        
        opcionesCliente.setVisible(true);
    }

    private void abrirVentanaEnBlanco(String titulo) {
        JFrame ventana = new JFrame(titulo);
        ventana.setSize(400, 300);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    
}


class RegistroUsuario extends JFrame {
    private JTextField txtTipoIdentificacion, txtDocumentoIdentificacion, txtNombres, txtApellidos,
            txtCorreo, txtDireccionResidencia, txtCiudadResidencia, txtTelefono, txtContrasena,
            txtConfirmarContrasena;

    private Proyecto_Final parent;
    private JComboBox<String> comboBoxRol;

    public RegistroUsuario(Proyecto_Final parent) {
        this.parent = parent;

        setTitle("Registro de Usuario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(13, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        txtTipoIdentificacion = new JTextField(20);
        txtDocumentoIdentificacion = new JTextField(20);
        txtNombres = new JTextField(20);
        txtApellidos = new JTextField(20);
        txtCorreo = new JTextField(20);
        txtDireccionResidencia = new JTextField(20);
        txtCiudadResidencia = new JTextField(20);
        txtTelefono = new JTextField(20);
        txtContrasena = new JPasswordField(20);
        txtConfirmarContrasena = new JPasswordField(20);
        comboBoxRol = new JComboBox<>(new String[]{"Cliente", "Administrativo"});
        
        

        mainPanel.add(new JLabel("Tipo de Identificación:"));
        mainPanel.add(txtTipoIdentificacion);
        mainPanel.add(new JLabel("Documento de Identificación:"));
        mainPanel.add(txtDocumentoIdentificacion);
        mainPanel.add(new JLabel("Nombres:"));
        mainPanel.add(txtNombres);
        mainPanel.add(new JLabel("Apellidos:"));
        mainPanel.add(txtApellidos);
        mainPanel.add(new JLabel("Correo:"));
        mainPanel.add(txtCorreo);
        mainPanel.add(new JLabel("Dirección de Residencia:"));
        mainPanel.add(txtDireccionResidencia);
        mainPanel.add(new JLabel("Ciudad de Residencia:"));
        mainPanel.add(txtCiudadResidencia);
        mainPanel.add(new JLabel("Teléfono:"));
        mainPanel.add(txtTelefono);
        mainPanel.add(new JLabel("Contraseña:"));
        mainPanel.add(txtContrasena);
        mainPanel.add(new JLabel("Confirmar Contraseña:"));
        mainPanel.add(txtConfirmarContrasena);
        mainPanel.add(new JLabel("Rol:"));
        mainPanel.add(comboBoxRol);

        

        JButton btnRegistrarUsuario = new JButton("Registrar Usuario");
        btnRegistrarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });
        
        mainPanel.add(btnRegistrarUsuario);

        add(mainPanel);

        pack();
        setLocationRelativeTo(parent);
    }

    

    private void registrarUsuario() {
        String tipoIdentificacion = txtTipoIdentificacion.getText();
        String documentoIdentificacion = txtDocumentoIdentificacion.getText();
        String nombres = txtNombres.getText();
        String apellidos = txtApellidos.getText();
        String correo = txtCorreo.getText();
        String direccionResidencia = txtDireccionResidencia.getText();
        String ciudadResidencia = txtCiudadResidencia.getText();
        String telefono = txtTelefono.getText();
        String contrasena = txtContrasena.getText();
        String confirmarContrasena = txtConfirmarContrasena.getText();
        

        if (!contrasena.equals(confirmarContrasena)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Error de Registro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int asignar = obtenerRolSeleccionado();
        String rol;
        if(asignar == 1){
            rol = "Administrador";
        }else{
            rol = "Cliente";
        }
        

        Usuario usuario = new Usuario(tipoIdentificacion, documentoIdentificacion, nombres, apellidos, correo, direccionResidencia, ciudadResidencia, telefono, contrasena, rol);
        
        parent.getRegistro().add(usuario);

       

        JOptionPane.showMessageDialog(this, "Usuario registrado satisfactoriamente", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
        dispose(); 
        parent.setVisible(true);
    }

    private int obtenerRolSeleccionado() {
        
        return comboBoxRol.getSelectedIndex(); 
    }

}

class Usuario {
    private String tipoIdentificacion;
    private String documentoIdentificacion;
    private String nombres;
    private String apellidos;
    private String correo;
    private String direccionResidencia;
    private String ciudadResidencia;
    private String telefono;
    private String contrasena;
    private String rol;
    

    public Usuario(String tipoIdentificacion, String documentoIdentificacion, String nombres, String apellidos, String correo, String direccionResidencia, String ciudadResidencia, String telefono, String contrasena, String rol) {
        this.tipoIdentificacion = tipoIdentificacion;
        this.documentoIdentificacion = documentoIdentificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.direccionResidencia = direccionResidencia;
        this.ciudadResidencia = ciudadResidencia;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.rol = rol; 
    }

    
    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getDocumentoIdentificacion() {
        return documentoIdentificacion;
    }

    public void setDocumentoIdentificacion(String documentoIdentificacion) {
        this.documentoIdentificacion = documentoIdentificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public String getCiudadResidencia() {
        return ciudadResidencia;
    }

    public void setCiudadResidencia(String ciudadResidencia) {
        this.ciudadResidencia = ciudadResidencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }
    
    public void setRol(String rol) {
        this.rol = rol;
    }

}

