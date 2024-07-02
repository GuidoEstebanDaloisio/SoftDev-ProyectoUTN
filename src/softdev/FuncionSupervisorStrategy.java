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
    public String[] solicitarAprobarProyecto() {
        String IdYTitulo[] = ingresarDatosDeProyectoAAprobar();

        return IdYTitulo;
    }

    @Override
    public String[] solicitarRechazarProyecto() {
        String IdYTitulo[] = ingresarDatosDeProyectoARechazar();

        return IdYTitulo;
    }

    @Override
    public String[] solicitarFinalizarProyecto() {
        String idYTitulo[] = ingresarDatosDeProyectoAFinalizar();

        return idYTitulo;
    }

    @Override
    public LocalDate ingresarFechaFinDeProyecto() {
        return ingresarFechaFin();
    }

    @Override
    public String[] nuevoEstadoDelProyecto() {

        String idYTitulo[] = ingresarDatosDeProyecto();
        String nuevoEstado = ingresarNuevoEstadoDeProyecto();

        String nuevoEstadoIdYTitulo[] = {nuevoEstado, idYTitulo[0], idYTitulo[1]};
        return nuevoEstadoIdYTitulo;
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
