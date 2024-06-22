package softdev;

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

    default void presentarListaDeProyectos() {
        espaciarPantallas();
        System.out.println("-------------------");
        System.out.println("Lista de proyectos:");
    }
}
