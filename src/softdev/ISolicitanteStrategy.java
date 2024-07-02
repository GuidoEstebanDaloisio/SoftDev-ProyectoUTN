package softdev;

import java.io.Serializable;
import java.util.ArrayList;

public interface ISolicitanteStrategy extends Serializable {

    public Proyecto solicitarNuevoProyecto();

    public void mostrarDatosDeProyectosDelUsuario(ArrayList<Proyecto> proyectos);
}
