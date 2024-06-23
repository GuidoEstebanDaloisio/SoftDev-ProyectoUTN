package softdev;

import java.util.List;
import java.util.Scanner;
import static softdev.Constantes.*;

public interface MenuGerente extends Menu {

    default String[] ingresarDatosDeProyectoAAprobar() {
        Scanner entrada = new Scanner(System.in);
        String contorno = "=================================================";
        String mensaje = "Ingrese los datos del proyecto que quiere aprobar";

        System.out.println(contorno);
        System.out.println(mensaje);
        System.out.println(contorno);

        String id = ingresarId();

        System.out.printf("Titulo: ");
        String titulo = entrada.nextLine();

        String IdYTitulo[] = {id, titulo.toUpperCase()};

        espaciarPantallas();
        return IdYTitulo;
    }

    default String[] ingresarDatosDeProyectoARechazar() {
        Scanner entrada = new Scanner(System.in);
        String contorno = "=================================================";
        String mensaje = "Ingrese los datos del proyecto que quiere rechazar";

        System.out.println(contorno);
        System.out.println(mensaje);
        System.out.println(contorno);

        String id = ingresarId();

        System.out.printf("Titulo: ");
        String titulo = entrada.nextLine();

        String IdYTitulo[] = {id, titulo.toUpperCase()};

        espaciarPantallas();
        return IdYTitulo;
    }

    default String ingresarNuevoEstadoDeProyecto() {
        Scanner entrada = new Scanner(System.in);
        String contorno = "====================================================";
        String mensaje = "Ingrese el estado en el que se encuentra el proyecto";

        System.out.println(contorno);
        System.out.println(mensaje);
        System.out.println(contorno);

        mostrarOpcionesValidaseEnVertical(ESTADOS_PROYECTO);

        return ingresarEstado();

    }

    private String ingresarEstado() {
        Scanner entrada = new Scanner(System.in);

        boolean opcionValida = false;
        String nuevoEstado = null;

        do {

            System.out.printf("-");
            int opcionEstado = entrada.nextInt();
            int numOpcion = 0;

            for (String elemento : ESTADOS_PROYECTO) {
                numOpcion++;
                if (numOpcion == opcionEstado) {
                    nuevoEstado = elemento;
                    opcionValida = true;
                }
            }
            if (!opcionValida) {
                numOpcion = 0;
                System.out.println(ERROR_ESTADO_PROYECTO_INVALIDO);
            }
        } while (!opcionValida);

        return nuevoEstado;
    }

    default void mostrarOpcionesValidaseEnVertical(List<String> elementos) {
        System.out.printf("Las opciones validas son:");
        int numOpcion = 0;

        for (String elemento : elementos) {
            numOpcion++;
            System.out.printf(numOpcion + ")");
            System.out.println(" " + elemento);
        }
        System.out.println("\n"); // Salto de línea al final
    }

    default String[] ingresarDatosDeProyecto() {
        Scanner entrada = new Scanner(System.in);
        String contorno = "==============================";
        String mensaje = "Ingrese los datos del proyecto";

        System.out.println(contorno);
        System.out.println(mensaje);
        System.out.println(contorno);

        String id = ingresarId();

        System.out.printf("Titulo: ");
        String titulo = entrada.next().toUpperCase();

        String idYTitulo[] = {id, titulo};

        return idYTitulo;
    }
    
    
    default void presentarListaDeProyectos() {
        espaciarPantallas();
        System.out.println("-------------------");
        System.out.println("Lista de proyectos:");
    }
}
