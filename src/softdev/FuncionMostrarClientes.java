package softdev;

import java.util.ArrayList;

public class FuncionMostrarClientes implements AccionUsuarioStrategy<Void>, MenuAdministrador {

    @Override
    public Void ejecutarAccion(Object... params) {
        ArrayList<Cliente> clientes = (ArrayList<Cliente>) params[0];

        if (clientes.isEmpty()) {
            System.out.println("*******************************************");
            System.out.println("EN ESTE MOMENTO NO HAY CLIENTES REGISTRADOS");
            System.out.println("*******************************************");
        } else {
            mostrarClientes(clientes);
        }

        return null; // No retorna ningún valor significativo (Void)
    }

    private void mostrarClientes(ArrayList<Cliente> clientes) {
        presentarListaDeClientes();

        for (Cliente cliente : clientes) {
            System.out.println("------------------");
            cliente.mostrarDatos();
        }
        System.out.println("------------------");
    }
}
