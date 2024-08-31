package softdev;

import java.util.Scanner;
import static softdev.Constantes.*;

public class MenuSolicitante extends Menu {
    public String[] ingresarDatosParaNuevoProyecto() {
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
                presupuesto = entrada.nextLine();
                // Intentar parsear como double
                Double.parseDouble(presupuesto);
                presupuestoValido = true;
            } catch (NumberFormatException e) {
                System.out.println(ERROR_PRESUPUESTO_INVALIDO);
            }
        } while (!presupuestoValido);

        String tituloDescripcionMedioYPresupuesto[] = {titulo.toUpperCase(), descripcion, medioDeSolicitud.toUpperCase(), presupuesto};

        espaciarPantallas();
        return tituloDescripcionMedioYPresupuesto;
    }

    private String ingresarMedioDeSolicitud() {
        String mensajeParaEntrada = "Medio por el que hace la solicitud";
        
        mostrarOpcionesValidasEnVertical(MEDIOS_DE_SOLICITUD);
        
        return ingresarOpcion(MEDIOS_DE_SOLICITUD, mensajeParaEntrada);
    }

    public void presentarListaDeProyectos() {
        espaciarPantallas();
        System.out.println("-------------------------------");
        System.out.println("Lista de proyectos del cliente:");
    }
}
