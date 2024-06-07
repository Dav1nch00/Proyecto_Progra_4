package vista;

import Controlador.ControladorUsuario;
import Controlador.ControladorReserva;
import Controlador.ControladorHabitacion;
import modelo.Habitacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaOpcionesCliente extends JFrame {

    private ControladorHabitacion roomManager;
    private ControladorReserva reservaManager;
    private String emailUsuario;
    private ControladorUsuario userManager;
    private Habitacion habitacion;

    public VentanaOpcionesCliente(ControladorUsuario ControladorUsuario,ControladorReserva ControladorReservas, ControladorHabitacion RoomManager, String mail) {
        this.userManager = ControladorUsuario;
        this.roomManager = RoomManager;
        this.reservaManager = ControladorReservas;
        this.emailUsuario = mail;
        initComponents();
    }

    private void initComponents() {
        setTitle("Reservas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnBuscarHabitacion = new JButton("Buscar Habitación");
        JButton btnVerReservas = new JButton("Ver Reservas");
        JButton btnCancelarReserva = new JButton("Cancelar Reserva");
        JButton btnHistorialReservas = new JButton("Historial de Reservas");
        


        btnVerReservas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VerReservas(emailUsuario, reservaManager).setVisible(true);
            }
        });

        btnBuscarHabitacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BuscarHabitacion(roomManager, reservaManager, emailUsuario).setVisible(true);
            }
        });

        btnHistorialReservas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Reservar(habitacion, roomManager, emailUsuario).setVisible(true);
            }
        });

        btnHistorialReservas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HistorialReservas(emailUsuario, reservaManager).setVisible(true);
            }
        });


        btnCancelarReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CancelarReserva(emailUsuario, reservaManager).setVisible(true);
            }
        });


        mainPanel.add(btnBuscarHabitacion);
        mainPanel.add(btnVerReservas);
        mainPanel.add(btnCancelarReserva);
        mainPanel.add(btnHistorialReservas);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
