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
        return ingresarFechaDe("finalizacion del proyecto");
    }

    public String ingresarNuevoEstadoDeProyecto() {
        String mensajeParaEntrada = "Nuevo estado";
        
        mostrarOpcionesValidasEnVertical(ESTADOS_PROYECTO_SEGUNDA_FASE);
        
        return ingresarOpcion(ESTADOS_PROYECTO_SEGUNDA_FASE, mensajeParaEntrada);
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
