package softdev;

import java.util.ArrayList;

public class FuncionMostrarDesarrolladoresDisponbles implements AccionUsuarioStrategy<Void>, MenuAdministrador {

    @Override
    public Void ejecutarAccion(Object... params) {
        ArrayList<Desarrollador> desarrolladores = (ArrayList<Desarrollador>) params[0];

        if (desarrolladores.isEmpty()) {
            System.out.println("**************************************************");
            System.out.println("EN ESTE MOMENTO NO HAY DESARROLLADORES DISPONIBLES");
            System.out.println("**************************************************");
        } else {
            mostrarDesarrolladores(desarrolladores);
        }

        return null; // No retorna ningún valor significativo (Void)
    }

    private void mostrarDesarrolladores(ArrayList<Desarrollador> desarrolladores) {
        presentarListaDeDesarrolladoresDisponibles();

        for (Desarrollador desarrollador : desarrolladores) {
            System.out.println("-------------------------------------");
            desarrollador.mostrarDatos();
        }
        System.out.println("-------------------------------------");
    }

}
