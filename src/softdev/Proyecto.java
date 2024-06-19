package softdev;

import java.time.LocalDateTime;
import java.util.ArrayList;
import static softdev.Constantes. *;

public class Proyecto {
   
    private Cliente clienteSolicitante;
    private String tipoDeEncargo;
    private ArrayList<Desarrollador> desarrolladores;
    private LocalDateTime fechaDeInicio;
    private LocalDateTime fechaDeFinalizacion;
    private double presupuesto;
    private String progreso;
    private boolean proyectoFinalizado;

    public Proyecto(Cliente clienteSolicitante, String tipoDeEncargo, double presupuesto) {
        this.clienteSolicitante = clienteSolicitante;
        this.tipoDeEncargo = tipoDeEncargo;
        this.presupuesto = presupuesto;
        this.progreso = ESPERANDO_APROVACION;
        this.proyectoFinalizado = false;
    }
    
   
   
}
