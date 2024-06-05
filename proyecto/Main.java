import Controlador.RoomManager;
import Controlador.ControladorUsuario;
import Controlador.controladorReservas;
import vista.ProyectoFinal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Crear instancia del controlador de usuarios y habitaciones
        ControladorUsuario controladorUsuario = new ControladorUsuario();
        RoomManager controladorHabitacion = new RoomManager();
        controladorReservas controladorReservas = new controladorReservas();

        // Iniciar la aplicación mostrando la ventana principal
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProyectoFinal(controladorUsuario, controladorHabitacion,controladorReservas);
            }
        });
    }
}
