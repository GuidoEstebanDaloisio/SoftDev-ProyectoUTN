package softdev;

import java.io.Serializable;
import java.util.ArrayList;

public interface ISolicitanteStrategy extends Serializable {

    public Proyecto solicitarNuevoProyecto(MenuSolicitante menu);

    public void mostrarDatosDeProyectosDelUsuario(MenuSolicitante menu, ArrayList<Proyecto> proyectos);
}
