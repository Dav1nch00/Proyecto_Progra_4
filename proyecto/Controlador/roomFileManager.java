package Controlador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import modelo.Habitacion;

public class roomFileManager {

    private static final String FILE_NAME = "habitaciones.dat";

    public static void guardarHabitaciones(List<Habitacion> habitaciones) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(habitaciones);
            oos.close();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Habitacion> cargarHabitaciones() throws IOException, ClassNotFoundException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Habitacion>) ois.readObject();
        }
    }

}
