package softdev;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import static softdev.Constantes.*;

public interface MenuGerente extends Menu {

    default String[] ingresarDatosDeProyectoAAprobar() {
        String contorno = "=================================================";
        String mensaje = "Ingrese los datos del proyecto que quiere aprobar";

        System.out.println(contorno);
        System.out.println(mensaje);
        System.out.println(contorno);

        return ingresarIdYTitulo();
    }

    default String[] ingresarDatosDeProyectoARechazar() {
        String contorno = "=================================================";
        String mensaje = "Ingrese los datos del proyecto que quiere rechazar";

        System.out.println(contorno);
        System.out.println(mensaje);
        System.out.println(contorno);

        return ingresarIdYTitulo();
    }

    default String[] ingresarDatosDeProyectoAFinalizar() {
        String contorno = "===================================================";
        String mensaje = "Ingrese los datos del proyecto que quiere finalizar";

        System.out.println(contorno);
        System.out.println(mensaje);
        System.out.println(contorno);

        String idYTitulo[] = ingresarIdYTitulo();
        
        return idYTitulo;
        }
    
    default LocalDate ingresarFechaFin(){
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaDeFin = null;

        while (fechaDeFin == null) {
            System.out.print("Ingrese la fecha de finalizacion del proyecto (formato: dd-MM-yyyy): ");
            String input = scanner.nextLine();

            try {
                fechaDeFin = LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto. Por favor, intente de nuevo.");
            }
        }
        return fechaDeFin;
    }

    default String ingresarNuevoEstadoDeProyecto() {
        String contorno = "====================================================";
        String mensaje = "Ingrese el estado en el que se encuentra el proyecto";

        System.out.println(contorno);
        System.out.println(mensaje);
        System.out.println(contorno);

        mostrarOpcionesValidaseEnVertical(ESTADOS_PROYECTO);

        return ingresarEstado();

    }

    default String[] ingresarDatosDeProyecto() {
        String contorno = "==============================";
        String mensaje = "Ingrese los datos del proyecto";

        System.out.println(contorno);
        System.out.println(mensaje);
        System.out.println(contorno);

        return ingresarIdYTitulo();
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

    default String[] ingresarIdYTitulo() {
        Scanner entrada = new Scanner(System.in);
        String id = ingresarId();

        System.out.printf("Titulo: ");
        String titulo = entrada.next().toUpperCase();

        String idYTitulo[] = {id, titulo};

        espaciarPantallas();
        return idYTitulo;
    }

    default void presentarListaDeProyectos() {
        espaciarPantallas();
        System.out.println("-------------------");
        System.out.println("Lista de proyectos:");
    }
}
