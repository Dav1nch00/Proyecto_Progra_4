package vista;

import Controlador.RoomManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaOpcionesAdministrador extends JFrame {

    private RoomManager controladorHabitacion;

    public VentanaOpcionesAdministrador(RoomManager controladorHabitacion) {
        this.controladorHabitacion = controladorHabitacion;
        setTitle("Opciones del Administrador");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelAdmin = new JPanel();
        panelAdmin.setLayout(new GridLayout(6, 1, 10, 10));

        JButton btnAgregarHabitacion = new JButton("Agregar Habitación");
        JButton btnEditarHabitacion = new JButton("Editar Habitación");
        JButton btnEliminarHabitacion = new JButton("Eliminar Habitación");
        JButton btnVerificarDisponibilidad = new JButton("Verificar Disponibilidad");

        btnAgregarHabitacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AgregarHabitacionInterface(controladorHabitacion).setVisible(true);
            }
        });

        btnEditarHabitacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditarHabitacionInterface(controladorHabitacion).setVisible(true);
            }
        });

        btnEliminarHabitacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EliminarHabitacionInterface(controladorHabitacion).setVisible(true);
            }
        });

        btnVerificarDisponibilidad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VerificarDisponibilidadInterface(controladorHabitacion).setVisible(true);
            }
        });

        panelAdmin.add(btnAgregarHabitacion);
        panelAdmin.add(btnEditarHabitacion);
        panelAdmin.add(btnEliminarHabitacion);
        panelAdmin.add(btnVerificarDisponibilidad);

        add(panelAdmin);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }




}
