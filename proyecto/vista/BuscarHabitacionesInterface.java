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

    public BuscarHabitacionesInterface(controladorReservas controladorReservas, RoomManager roomManager) {
        this.roomManager = roomManager;
        this.controladorReservas = controladorReservas;
        setTitle("Buscar habitaciones disponibles");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel lblFechaInicio = new JLabel("Fecha de inicio:");
        txtFechaInicio = new JTextField();
        JLabel lblFechaFin = new JLabel("Fecha de fin:");
        txtFechaFin = new JTextField();
        JLabel lblPersonas = new JLabel("Número de personas:");
        txtPersonas = new JTextField();

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarHabitacionesDisponibles();
            }
        });

        txtAreaHabitaciones = new JTextArea();
        txtAreaHabitaciones.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtAreaHabitaciones);

        panel.add(lblFechaInicio);
        panel.add(txtFechaInicio);
        panel.add(lblFechaFin);
        panel.add(txtFechaFin);
        panel.add(lblPersonas);
        panel.add(txtPersonas);
        panel.add(new JLabel());
        panel.add(btnBuscar);
        panel.add(new JLabel());
        panel.add(scrollPane);

        add(panel);
    }

    private void buscarHabitacionesDisponibles() {
        String fechaInicio = txtFechaInicio.getText();
        String fechaFin = txtFechaFin.getText();
        int personas = Integer.parseInt(txtPersonas.getText());

        List<Habitacion> todasLasHabitaciones = roomManager.obtenerHabitaciones();
        List<Habitacion> habitacionesDisponibles = filtrarHabitacionesDisponibles(todasLasHabitaciones, fechaInicio, fechaFin, personas);

        // Limpiar el área de texto antes de mostrar las nuevas habitaciones
        txtAreaHabitaciones.setText("");

        // Mostrar las habitaciones disponibles en el área de texto
        for (Habitacion habitacion : habitacionesDisponibles) {
            txtAreaHabitaciones.append("ID: " + habitacion.getId() + "\n");
            txtAreaHabitaciones.append("Tipo: " + habitacion.getTipo() + "\n");
            txtAreaHabitaciones.append("Capacidad: " + habitacion.getCapacidad() + "\n");
            txtAreaHabitaciones.append("Precio por Noche: " + habitacion.getPrecioPorNoche() + "\n");
            txtAreaHabitaciones.append("Comodidades: " + habitacion.getComodidades() + "\n\n");
        }
    }

    private List<Habitacion> filtrarHabitacionesDisponibles(List<Habitacion> habitaciones, String fechaInicio, String fechaFin, int personas) {
    List<Habitacion> habitacionesDisponibles = new ArrayList<>();

    // Convertir las fechas de inicio y fin a objetos LocalDate
    LocalDate fechaInicioReserva = LocalDate.parse(fechaInicio);
    LocalDate fechaFinReserva = LocalDate.parse(fechaFin);

    // Iterar sobre todas las habitaciones
    for (Habitacion habitacion : habitaciones) {
        // Verificar la capacidad de la habitación
        if (habitacion.getCapacidad() >= personas) {
            // Verificar si la habitación está disponible en las fechas especificadas
            if (habitacionEstaDisponible(habitacion, fechaInicioReserva, fechaFinReserva)) {
                habitacionesDisponibles.add(habitacion);
            }
        }
    }

    return habitacionesDisponibles;
}

private boolean habitacionEstaDisponible(Habitacion habitacion, LocalDate fechaInicioReserva, LocalDate fechaFinReserva) {
    
    return true;
}

}

