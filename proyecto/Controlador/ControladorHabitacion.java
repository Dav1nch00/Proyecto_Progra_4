package Controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import modelo.Habitacion;

import java.time.LocalDateTime;

public class ControladorHabitacion {

    private List<Habitacion> habitaciones;

    public ControladorHabitacion(){
        try {
            habitaciones = ControladorArchivoHabitacion.cargarHabitaciones();
        } catch (IOException | ClassNotFoundException e) {
            habitaciones = new ArrayList<>();
        }
    }

    public List<Habitacion> obtenerHabitaciones() {
        return habitaciones;
    }

    public void agregarHabitacion(Habitacion habitacion){
        habitaciones.add(habitacion);
        guardarHabitaciones();
    }

    public void eliminarHabitacion(String id){
        habitaciones.removeIf(habitacion -> habitacion.getId().equals(id));
        guardarHabitaciones();
    }

    public void editarHabitacion(Habitacion habitacionEditada){
        for (int i = 0; i < habitaciones.size(); i++) {
            if (habitaciones.get(i).getId().equals(habitacionEditada.getId())) {
                habitaciones.set(i, habitacionEditada);
                break;
            }
        }
        guardarHabitaciones();
    }


    void actualizarDisponibilidad(String idHabitacion, boolean reservada) {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getId().equals(idHabitacion)) {
                habitacion.setReservada(reservada);
                break;
            }
        }
        guardarHabitaciones();
    }

    public Habitacion buscarHabitacion(String id) {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getId().equals(id)) {
                return habitacion;
            }
        }
        return null;
    }

    public List<Habitacion> buscarHabitaciones(LocalDateTime fechaInicio, LocalDateTime fechaFin, int numeroHuespedes, String tipo) {
        List<Habitacion> resultados = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getTipo().equalsIgnoreCase(tipo) && habitacion.getCapacidad() >= numeroHuespedes &&
                habitacion.getFechaInicioDisponibilidad().isBefore(fechaFin) &&
                habitacion.getFechaFinDisponibilidad().isAfter(fechaInicio) &&
                !habitacion.isReservada()) { 
                resultados.add(habitacion);
            }
        }
        return resultados;
    }

    private void guardarHabitaciones() {
        try {
            ControladorArchivoHabitacion.guardarHabitaciones(habitaciones);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
