package softdev;

import java.util.ArrayList;

public class FuncionMostrarGerentes implements AccionUsuarioStrategy<Void>, MenuAdministrador {

    @Override
    public Void ejecutarAccion(Object... params) {
        ArrayList<Gerente> gerentes = (ArrayList<Gerente>) params[0];

        if (gerentes.isEmpty()) {
            System.out.println("*******************************************");
            System.out.println("EN ESTE MOMENTO NO HAY GERENTES REGISTRADOS");
            System.out.println("*******************************************");
        } else {
            mostrarGerentes(gerentes);
        }

        return null; // No retorna ningún valor significativo (Void)
    }

    private void mostrarGerentes(ArrayList<Gerente> gerentes) {
        presentarListaDeGerentes();

        for (Gerente gerente : gerentes) {
            System.out.println("------------------");
            gerente.mostrarDatos();
        }
        System.out.println("------------------");
    }

}
