package softdev;

import java.util.ArrayList;

public class FuncionMostrarDesarrolladoresAsignados implements AccionUsuarioStrategy<Void>, MenuAdministrador {

    @Override
    public Void ejecutarAccion(Object... params) {
        ArrayList<Desarrollador> desarrolladores = (ArrayList<Desarrollador>) params[0];

        if (desarrolladores.isEmpty()) {
            System.out.println("************************************************");
            System.out.println("EN ESTE MOMENTO NO HAY DESARROLLADORES ASIGNADOS");
            System.out.println("************************************************");
        } else {
            mostrarDesarrolladores(desarrolladores);
        }

        return null; // No retorna ningún valor significativo (Void)
    }

    private void mostrarDesarrolladores(ArrayList<Desarrollador> desarrolladores) {
        presentarListaDeDesarrolladoresAsignados();

        for (Desarrollador desarrollador : desarrolladores) {
            System.out.println("-------------------------------------");
            desarrollador.mostrarDatos();
        }
        System.out.println("-------------------------------------");
    }
    
}
