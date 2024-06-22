package softdev;

import java.util.ArrayList;

public class FuncionMostrarProyectos implements AccionUsuarioStrategy, MenuGerente {

    @Override
    public Void ejecutarAccion(Object... params) {
        
        ArrayList<Proyecto> proyectos = (ArrayList<Proyecto>) params[0];
        
        if (proyectos.isEmpty()) {
            System.out.println("********************************************");
            System.out.println("EN ESTE MOMENTO NO HAY PROYECTOS REGISTRADOS");
            System.out.println("********************************************");
        } else {
            mostrarProyectos(proyectos);
        }
        return null;
    }
    
    private void mostrarProyectos(ArrayList<Proyecto> proyectos) {
        presentarListaDeProyectos();

        for (Proyecto proyecto : proyectos) {
            System.out.println("-------------------");
            proyecto.mostrarDatos();
        }
        System.out.println("-------------------");
    }   
    
}
