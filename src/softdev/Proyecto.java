package softdev;

import java.time.LocalDateTime;
import java.util.ArrayList;
import static softdev.EstadosDeProyecto. *;

public class Proyecto {
   
    private Cliente clienteSolicitante;
    private ArrayList<Desarrollador> desarrolladores;
    private LocalDateTime fechaDeInicio;
    private LocalDateTime fechaDeFinalizacion;
    private double presupuesto;
    private String progreso;
    private boolean proyectoFinalizado;

    public Proyecto(Cliente clienteSolicitante, double presupuesto) {
        this.clienteSolicitante = clienteSolicitante;
        this.presupuesto = presupuesto;
        this.progreso = ESPERANDO_APROVACION;
        this.proyectoFinalizado = false;
    }
    
   
   
}
