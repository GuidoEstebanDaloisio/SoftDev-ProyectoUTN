package softdev;

import java.util.Arrays;
import java.util.List;
import static softdev.Constantes.*;

public class ServicioDeValidacion {

        public static final List<String> ESTADOS_PROYECTO_SEGUNDA_FASE = Arrays.asList(EN_DESARROLLO, PRUEBAS, IMPLEMENTACION, MANTENIMIENTO, CIERRE);

    
    public boolean validarActualizacionDelProyecto(String progreso){
        return !ESTADOS_PROYECTO_PRIMERA_FASE.contains(progreso) || !progreso.equals(FINALIZADO);
    }
    
    public boolean validarFinalizacionDelProyecto(String progreso){
        
        return ESTADOS_PROYECTO_SEGUNDA_FASE.contains(progreso);
    }
}
