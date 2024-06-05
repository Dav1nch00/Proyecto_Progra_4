package Controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import modelo.reservas;

import java.time.LocalDateTime;

public class controladorReservas {

    private List<reservas> reservas;

    public controladorReservas(){
        try {
            reservas = reservasarchivo.cargarReservas();
        } catch (IOException | ClassNotFoundException e) {
            reservas = new ArrayList<>();
        }
    }

    public List<reservas> obtenerReservas() {
        return reservas;
    }

    public void agregarReservas(reservas Reservas){
        reservas.add(Reservas);
        guardarReservas();
    }

    


    private void guardarReservas() {
        try {
            reservasarchivo.guardarReservas(reservas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
