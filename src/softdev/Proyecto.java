package softdev;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static softdev.Constantes.*;

public class Proyecto implements Serializable {

    private String titulo;
    private String descripcion;
    private Cliente clienteSolicitante;
    private String medioDeEncargo;
    private ArrayList<Desarrollador> desarrolladores;
    private LocalDateTime fechaDeInicio;
    private LocalDateTime fechaDeFinalizacion;
    private double presupuesto;
    private String progreso;
    private boolean proyectoFinalizado;

    public Proyecto(String titulo, String descripcion, String medioDeEncargo, double presupuesto) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.medioDeEncargo = medioDeEncargo;
        this.presupuesto = presupuesto;
        this.progreso = ESPERANDO_APROVACION;
        this.proyectoFinalizado = false;
    }

    public void setClienteSolicitante(Cliente clienteSolicitante) {
        this.clienteSolicitante = clienteSolicitante;
    }

    public boolean compararClientes(Cliente cliente) {
        return clienteSolicitante.equals(cliente);
    }

    public void mostrarDatos() {
        System.out.println("Titulo: " + titulo);
        System.out.println("Descripcion: " + descripcion);
        System.out.println("Cliente: " + clienteSolicitante.getNombre());
        System.out.println("Medio de encargo: " + medioDeEncargo);
        System.out.println("Presupuesto: " + presupuesto);
        System.out.println("Progreso: " + progreso);

        if (progreso != ESPERANDO_APROVACION && progreso != RECHAZADO) {
            if (!desarrolladores.isEmpty()) {
                mostrarListaDeDesarrolladores();
            }
                System.out.println("Fecha inicio: " + fechaDeInicio);
                System.out.println("Fecha fin: " + fechaDeFinalizacion);
        }
    }

    private void mostrarListaDeDesarrolladores() {
        System.out.println("Desarrolladores: ");
        for (Desarrollador desarrollador : desarrolladores) {
            System.out.println("                -" + desarrollador.getNombre());
        }

    }

}
