package softdev;

import java.time.LocalDate;
import java.util.ArrayList;

public class FuncionSupervisorStrategy implements ISupervisorStrategy, MenuGerente {

    @Override
    public void mostrarProyectos(ArrayList<Proyecto> proyectos) {
        if (proyectos.isEmpty()) {
            System.out.println("********************************************");
            System.out.println("EN ESTE MOMENTO NO HAY PROYECTOS REGISTRADOS");
            System.out.println("********************************************");
        } else {
            mostrarDatos(proyectos);
        }
    }

    @Override
    public String solicitarAprobarProyecto() {
        String id = ingresarDatosDeProyectoAAprobar();

        return id;
    }

    @Override
    public String solicitarRechazarProyecto() {
        String id = ingresarDatosDeProyectoARechazar();

        return id;
    }

    @Override
    public String solicitarFinalizarProyecto() {
        String id = ingresarDatosDeProyectoAFinalizar();

        return id;
    }

    @Override
    public LocalDate ingresarFechaFinDeProyecto() {
        return ingresarFechaFin();
    }

    @Override
    public String[] nuevoEstadoDelProyecto() {

        String id = ingresarDatosDeProyecto();
        String nuevoEstado = ingresarNuevoEstadoDeProyecto();

        String nuevoEstadoYId[] = {nuevoEstado, id};
        return nuevoEstadoYId;
    }

        private void mostrarDatos(ArrayList<Proyecto> proyectos) {
        presentarListaDeProyectos();

        for (Proyecto proyecto : proyectos) {
            System.out.println("-------------------");
            proyecto.mostrarDatos();
        }
        System.out.println("-------------------");
    }
}
