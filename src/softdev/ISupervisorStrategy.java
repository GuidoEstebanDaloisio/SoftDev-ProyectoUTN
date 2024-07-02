package softdev;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public interface ISupervisorStrategy extends Serializable{
    public void mostrarProyectos(ArrayList<Proyecto> proyectos);

    public String[] solicitarAprobarProyecto();

    public String[] solicitarRechazarProyecto();

    public String[] solicitarFinalizarProyecto();
    
    public LocalDate ingresarFechaFinDeProyecto();
    
    public String[] nuevoEstadoDelProyecto();
}
