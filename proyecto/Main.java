import Controlador.ControladorHabitacion;
import Controlador.ControladorReserva;
import Controlador.ControladorUsuario;
import vista.ProyectoFinal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        
        ControladorUsuario controladorUsuario = new ControladorUsuario();
        ControladorHabitacion controladorHabitacion = new ControladorHabitacion();
        ControladorReserva controladorReservas = new ControladorReserva(controladorHabitacion);

        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProyectoFinal(controladorUsuario, controladorHabitacion,controladorReservas);
            }
        });
    }
}
