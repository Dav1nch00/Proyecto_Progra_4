package modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class reservas implements Serializable {

    private String id_reserva;
    private String cliente;
    private String habitacion;
    private int personas;
    private LocalDateTime fechaInicioDisponibilidad;
    private LocalDateTime fechaFinDisponibilidad;

    public reservas(String id_reserva, String cliente, String habitacion, int personas, LocalDateTime fechaInicioDisponibilidad, LocalDateTime fechaFinDisponibilidad) {
        this.id_reserva = id_reserva;
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.personas = personas;
        this.fechaInicioDisponibilidad = fechaInicioDisponibilidad;
        this.fechaFinDisponibilidad = fechaFinDisponibilidad;
    }
    

    // Getters y setters

    public String getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(String id_reserva) {
        this.id_reserva = id_reserva;
    }

    public String getcliente() {
        return cliente;
    }

    public void setcliente(String cliente) {
        this.cliente = cliente;
    }

    public String gethabitacion() {
        return habitacion;
    }

    public void sethabitacion(String habitacion) {
        this.habitacion = habitacion;
    }

    public int getpersonas() {
        return personas;
    }

    public void setpersonas(int personas) {
        this.personas = personas;
    }

    public LocalDateTime getFechaInicioDisponibilidad() {
        return fechaInicioDisponibilidad;
    }

    public void setFechaInicioDisponibilidad(LocalDateTime fechaInicioDisponibilidad) {
        this.fechaInicioDisponibilidad = fechaInicioDisponibilidad;
    }

    public LocalDateTime getFechaFinDisponibilidad() {
        return fechaFinDisponibilidad;
    }

    public void setFechaFinDisponibilidad(LocalDateTime fechaFinDisponibilidad) {
        this.fechaFinDisponibilidad = fechaFinDisponibilidad;
    }

    
}