package softdev;

import java.util.ArrayList;

public class FuncionSolicitanteStrategy implements ISolicitanteStrategy{

    @Override
    public Proyecto solicitarNuevoProyecto(MenuSolicitante menu) {
        String tituloDescripcionMedioYPresupuesto[] = menu.ingresarDatosParaNuevoProyecto();

        double presupuesto = Double.parseDouble(tituloDescripcionMedioYPresupuesto[3]);

        Proyecto nuevoProyecto = new Proyecto(tituloDescripcionMedioYPresupuesto[0], tituloDescripcionMedioYPresupuesto[1], tituloDescripcionMedioYPresupuesto[2], presupuesto);
        return nuevoProyecto;
    }

    @Override
    public void mostrarDatosDeProyectosDelUsuario(MenuSolicitante menu, ArrayList<Proyecto> proyectos) {
        if (proyectos.isEmpty()) {
            System.out.println("****************************************");
            System.out.println("EN ESTE MOMENTO USTED NO POSEE PROYECTOS");
            System.out.println("****************************************");
        } else {
            mostrarProyectos(menu, proyectos);
        }
    }
    
     private void mostrarProyectos(MenuSolicitante menu, ArrayList<Proyecto> proyectos) {
        menu.presentarListaDeProyectos();

        for (Proyecto proyecto : proyectos) {
            System.out.println("-------------------------------");
            proyecto.mostrarDatos();
        }
        System.out.println("-------------------------------");
    }  

}
