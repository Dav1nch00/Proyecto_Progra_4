package Controlador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import modelo.Habitacion;

public class ControladorArchivoHabitacion {

    private static final String archivo = "habitaciones.dat";

    public static void guardarHabitaciones(List<Habitacion> habitaciones) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(habitaciones);
            oos.close();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Habitacion> cargarHabitaciones() throws IOException, ClassNotFoundException {
        File file = new File(archivo);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Habitacion>) ois.readObject();
        }
    }

}
