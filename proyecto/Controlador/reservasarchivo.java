package Controlador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import modelo.reservas;

public class reservasarchivo {

    private static final String FILE_NAME = "reservas.dat";

    public static void guardarReservas(List<reservas> reservas) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(reservas);
            oos.close();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<reservas> cargarReservas() throws IOException, ClassNotFoundException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<reservas>) ois.readObject();
        }
    }

}
