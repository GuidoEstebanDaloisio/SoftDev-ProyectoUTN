package softdev;

import java.util.Scanner;
import static softdev.Constantes.*;

public interface MenuCliente extends Menu {

    default String[] ingresarDatosParaNuevoProyecto() {
        Scanner entrada = new Scanner(System.in);
        String contorno = "============================================================";
        String mensaje = "Ingrese los datos para llenar la solicitud de nuevo proyecto";

        System.out.println(contorno);
        System.out.println(mensaje);
        System.out.println(contorno);

        String medioDeSolicitud = ingresarMedioDeSolicitud();

        System.out.printf("Titulo: ");
        String titulo = entrada.nextLine();

        System.out.printf("Descripcion: ");
        String descripcion = entrada.nextLine();

        String presupuesto = "";
        boolean presupuestoValido = false;
        do {
            try {
                System.out.printf("Presupuesto: $");
                presupuesto = entrada.next();
                // Intentar parsear como double
                Double.parseDouble(presupuesto);
                presupuestoValido = true;
            } catch (NumberFormatException e) {
                System.out.println(ERROR_PRESUPUESTO_INVALIDO);
                entrada.nextLine();
            }
        } while (!presupuestoValido);

        String tituloDescripcionMedioYPresupuesto[] = {titulo.toUpperCase(), descripcion, medioDeSolicitud.toUpperCase(), presupuesto};

        espaciarPantallas();
        return tituloDescripcionMedioYPresupuesto;
    }

    private String ingresarMedioDeSolicitud() {
        Scanner entrada = new Scanner(System.in);
        String medio;
        do {
            mostrarOpcionesValidas(MEDIOS_DE_SOLICITUD);
            System.out.printf("Medio por el que hace la solicitud: ");
            medio = entrada.next();
        } while (!MEDIOS_DE_SOLICITUD.contains(medio.toUpperCase()));

        return medio;
    }

    default void presentarListaDeProyectos() {
        espaciarPantallas();
        System.out.println("-------------------------------");
        System.out.println("Lista de proyectos del cliente:");
    }
}
