package softdev;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import static softdev.Constantes.*;

public class MenuSupervisor extends Menu{
    public String ingresarDatosDeProyectoAAprobar() {
        String contorno = "=================================================";
        String mensaje = "Ingrese los datos del proyecto que quiere aprobar";

        System.out.println(contorno);
        System.out.println(mensaje);
        System.out.println(contorno);

        return ingresarId();
    }

    public String ingresarDatosDeProyectoARechazar() {
        String contorno = "=================================================";
        String mensaje = "Ingrese los datos del proyecto que quiere rechazar";

        System.out.println(contorno);
        System.out.println(mensaje);
        System.out.println(contorno);

        return ingresarId();
    }

    public String ingresarDatosDeProyectoAFinalizar() {
        String contorno = "===================================================";
        String mensaje = "Ingrese los datos del proyecto que quiere finalizar";

        System.out.println(contorno);
        System.out.println(mensaje);
        System.out.println(contorno);

        
        return ingresarId();
        }
    
    public LocalDate ingresarFechaFin(){
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

    public String ingresarNuevoEstadoDeProyecto() {
        String mensajeParaEntrada = "Nuevo estado";
        
        mostrarOpcionesValidasEnVertical(ESTADOS_PROYECTO);
        
        return ingresarOpcion(ESTADOS_PROYECTO, mensajeParaEntrada);
    }

    public String ingresarDatosDeProyecto() {
        String contorno = "==============================";
        String mensaje = "Ingrese los datos del proyecto";

        System.out.println(contorno);
        System.out.println(mensaje);
        System.out.println(contorno);

        return ingresarId();
    }

    public void presentarListaDeProyectos() {
        espaciarPantallas();
        System.out.println("-------------------");
        System.out.println("Lista de proyectos:");
    }
}
