package softdev;

import java.util.ArrayList;

public class FuncionMostrarAdministradores implements AccionUsuarioStrategy<Void>, MenuAdministrador {

    @Override
    public Void ejecutarAccion(Object... params) {
        ArrayList<Administrador> administradores = (ArrayList<Administrador>) params[0];

        if (administradores.isEmpty()) {
            System.out.println("**************************************************");
            System.out.println("EN ESTE MOMENTO NO HAY ADMINISTRADORES REGISTRADOS");
            System.out.println("**************************************************");
        } else {
            mostrarAdministradores(administradores);
        }

        return null; // No retorna ningún valor significativo (Void)
    }

    private void mostrarAdministradores(ArrayList<Administrador> administradores) {
        presentarListaDeAdministradores();

        for (Administrador administrador : administradores) {
            System.out.println("-------------------------");
            administrador.mostrarDatos();
        }
        System.out.println("-------------------------");
    }
}
