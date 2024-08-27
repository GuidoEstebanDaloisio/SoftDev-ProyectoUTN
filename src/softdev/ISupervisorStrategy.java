package softdev;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public interface ISupervisorStrategy extends Serializable{
    public void mostrarProyectos(MenuSupervisor menu, ArrayList<Proyecto> proyectos);

    public String solicitarAprobarProyecto(MenuSupervisor menu);

    public String solicitarRechazarProyecto(MenuSupervisor menu);

    public String solicitarFinalizarProyecto(MenuSupervisor menu);
    
    public LocalDate ingresarFechaFinDeProyecto(MenuSupervisor menu);
    
    public String[] actualizarEstadoDelProyecto(MenuSupervisor menu);
}
