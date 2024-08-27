package softdev;

import java.time.LocalDate;
import java.util.ArrayList;

public class FuncionSupervisorStrategy implements ISupervisorStrategy {

    @Override
    public void mostrarProyectos(MenuSupervisor menu, ArrayList<Proyecto> proyectos) {
        if (proyectos.isEmpty()) {
            System.out.println("********************************************");
            System.out.println("EN ESTE MOMENTO NO HAY PROYECTOS REGISTRADOS");
            System.out.println("********************************************");
        } else {
            mostrarDatos(menu, proyectos);
        }
    }

    @Override
    public String solicitarAprobarProyecto(MenuSupervisor menu) {
        String id = menu.ingresarDatosDeProyectoAAprobar();

        return id;
    }

    @Override
    public String solicitarRechazarProyecto(MenuSupervisor menu) {
        String id = menu.ingresarDatosDeProyectoARechazar();

        return id;
    }

    @Override
    public String solicitarFinalizarProyecto(MenuSupervisor menu) {
        String id = menu.ingresarDatosDeProyectoAFinalizar();

        return id;
    }

    @Override
    public LocalDate ingresarFechaFinDeProyecto(MenuSupervisor menu) {
        return menu.ingresarFechaFin();
    }

    @Override
    public String[] actualizarEstadoDelProyecto(MenuSupervisor menu) {

        String id = menu.ingresarDatosDeProyecto();
        String nuevoEstado = menu.ingresarNuevoEstadoDeProyecto();

        String nuevoEstadoYId[] = {nuevoEstado, id};
        return nuevoEstadoYId;
    }

    private void mostrarDatos(MenuSupervisor menu, ArrayList<Proyecto> proyectos) {
        menu.presentarListaDeProyectos();

        for (Proyecto proyecto : proyectos) {
            System.out.println("-------------------");
            proyecto.mostrarDatos();
        }
        System.out.println("-------------------");
    }
}
