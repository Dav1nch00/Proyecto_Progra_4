package vista;

import Controlador.RoomManager;
import modelo.Habitacion;
import modelo.reservas;
import Controlador.controladorReservas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BuscarHabitacionesInterface extends JDialog {
    private JTextField txtFechaInicio;
    private JTextField txtFechaFin;
    private JTextField txtPersonas;
    private JTextArea txtAreaHabitaciones;
    private RoomManager roomManager;
    private controladorReservas controladorReservas;
package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controlador.ControladorReserva;
import Controlador.ControladorHabitacion;
import modelo.Habitacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BuscarHabitacion extends JFrame {

    private ControladorHabitacion roomManager;
    private String emailUsuario;

    public BuscarHabitacion(ControladorHabitacion roomManager, ControladorReserva reservaManager, String emailUsuario) {
        this.roomManager = roomManager;
        this.emailUsuario = emailUsuario;
        initComponents();
    }

    
    private JTextField txtFechaInicio;
    private JTextField txtFechaFin;
    private JTextField txtNumeroHuespedes;
    private JComboBox<String> comboTipoHabitacion;
    private JPanel resultadoPanel;

    private void initComponents() {
        setTitle("Buscar Habitaciones");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);

        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(15, 10, 10, 10));

        
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        JLabel lblFechaInicio = new JLabel("Fecha de Inicio (YYYY-MM-DD HH:MM):");
        txtFechaInicio = new JTextField();

        JLabel lblFechaFin = new JLabel("Fecha de Fin (YYYY-MM-DD HH:MM):");
        txtFechaFin = new JTextField();

        JLabel lblNumeroHuespedes = new JLabel("Número de Huéspedes:");
        txtNumeroHuespedes = new JTextField();

        JLabel lblTipoHabitacion = new JLabel("Tipo de Habitación:");
        comboTipoHabitacion = new JComboBox<>(new String[]{"Sencilla", "Multiple"});

        inputPanel.add(lblFechaInicio);
        inputPanel.add(txtFechaInicio);
        inputPanel.add(lblFechaFin);
        inputPanel.add(txtFechaFin);
        inputPanel.add(lblNumeroHuespedes);
        inputPanel.add(txtNumeroHuespedes);
        inputPanel.add(lblTipoHabitacion);
        inputPanel.add(comboTipoHabitacion);

        mainPanel.add(inputPanel);

        
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarHabitaciones();
            }
        });

        mainPanel.add(btnBuscar);

        
        resultadoPanel = new JPanel();
        resultadoPanel.setLayout(new BoxLayout(resultadoPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(resultadoPanel);
        
        scrollPane.setPreferredSize(new Dimension(300, 100));

        mainPanel.add(scrollPane);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }

    private void buscarHabitaciones() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            LocalDateTime fechaInicio = LocalDateTime.parse(txtFechaInicio.getText().trim(), formatter);
            LocalDateTime fechaFin = LocalDateTime.parse(txtFechaFin.getText().trim(), formatter);
            int numeroHuespedes = Integer.parseInt(txtNumeroHuespedes.getText().trim());
            String tipoHabitacion = (String) comboTipoHabitacion.getSelectedItem();

            List<Habitacion> habitacionesDisponibles = roomManager.buscarHabitaciones(fechaInicio, fechaFin, numeroHuespedes, tipoHabitacion);

            resultadoPanel.removeAll(); 
            if (habitacionesDisponibles.isEmpty()) {
                resultadoPanel.add(new JLabel("No se encontraron habitaciones disponibles."));
            } else {
                for (Habitacion habitacion : habitacionesDisponibles) {
                    if (!habitacion.isReservada()) { 
                        JPanel habitacionPanel = new JPanel();
                        habitacionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                        habitacionPanel.add(new JLabel(habitacion.getId()));

                        JButton btnDetalles = new JButton("Ver Detalles");
                        btnDetalles.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                new DetalleHabitacion(habitacion, roomManager, emailUsuario).setVisible(true);
                            }
                        });

                        habitacionPanel.add(btnDetalles);
                        resultadoPanel.add(habitacionPanel);
                    }
                }
            }
            resultadoPanel.revalidate();
            resultadoPanel.repaint();
        } catch (Exception e) {
            resultadoPanel.removeAll();
            resultadoPanel.add(new JLabel("Error en la búsqueda: " + e.getMessage()));
            resultadoPanel.revalidate();
            resultadoPanel.repaint();
        }
    }
}
