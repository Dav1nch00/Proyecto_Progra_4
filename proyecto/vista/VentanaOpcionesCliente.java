package vista;

import Controlador.controladorReservas;
import Controlador.RoomManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaOpcionesCliente extends JFrame {

    private controladorReservas controladorReservas;
    private RoomManager roomManager;

    public VentanaOpcionesCliente(controladorReservas ControladorReservas, RoomManager RoomManager) {
        this.roomManager = RoomManager;
        this.controladorReservas = ControladorReservas;
        setTitle("Opciones del Cliente");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelCliente = new JPanel();
        panelCliente.setLayout(new GridLayout(6, 1, 10, 10));

        JButton btnBuscarHabitaciones = new JButton("Buscar Habitaciones Disponibles");
        JButton btnverReserva = new JButton("ver Reserva");
        JButton btnVerHistorial = new JButton("Ver Historial de Reservas");

        panelCliente.add(btnBuscarHabitaciones);
        panelCliente.add(btnverReserva);
        panelCliente.add(btnVerHistorial);

        btnBuscarHabitaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BuscarHabitacionesInterface(ControladorReservas, RoomManager).setVisible(true);
            }
        });

        btnverReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaEnBlanco("Modificar Reserva");
            }
        });

        btnVerHistorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaEnBlanco("Ver Historial de Reservas");
            }
        });

        add(panelCliente);
    }

    private void abrirVentanaEnBlanco(String titulo) {
        JFrame ventana = new JFrame(titulo);
        ventana.setSize(400, 300);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}
