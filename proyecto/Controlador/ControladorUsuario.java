package Controlador;

import modelo.GestorArchivoUsuarios;
import modelo.Usuario;
import vista.ProyectoFinal;
import vista.RegistroUsuario;
import vista.VentanaInicioSesion;
import Controlador.RoomManager;

import java.util.List;

public class ControladorUsuario {
    private List<Usuario> usuarios;
    private ProyectoFinal proyectoFinal;
    private RoomManager controladorHabitacion;
    private controladorReservas controladorReservas;

    public ControladorUsuario() {
        this.usuarios = GestorArchivoUsuarios.cargarUsuarios();
    }

    public void setProyectoFinal(ProyectoFinal proyectoFinal) {
        this.proyectoFinal = proyectoFinal;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void mostrarVentanaRegistro() {
        new RegistroUsuario(proyectoFinal, new ControladorRegistro(this)).setVisible(true);
    }

    public void iniciarSesion(RoomManager controladorHabitacion, controladorReservas controladorReservas) {
        new VentanaInicioSesion(new ControladorInicioSesion(usuarios,controladorHabitacion,controladorReservas)).setVisible(true);
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        GestorArchivoUsuarios.guardarUsuarios(usuarios);
    }
}
