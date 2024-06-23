package softdev;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import static softdev.Constantes. *;


public interface Menu {

    default String ingresarId(){
        int id = 0;
        boolean idValido = false;
        Scanner entrada = new Scanner(System.in);

        while (!idValido) {
            try {
                System.out.printf("Id: ");
                id = Integer.parseInt(entrada.nextLine());
                idValido = true;
            } catch (NumberFormatException e) {
                System.out.println(ERROR_ID_INVALIDO);
            }
        }
        return String.valueOf(id);
    }
    
    default void mostrarOpcionesValidas(List<String> elementos) {
        System.out.printf("Las opciones validas son:");

        // Imprimir encabezados
        for (String elemento : elementos) {
            System.out.printf(" %s", elemento);
        }
        System.out.println("\n"); // Salto de línea al final
    }
    
    default void mostrarOpcionesValidaseEnVertical(List<String> elementos) {
        System.out.printf("Las opciones validas son:\n");
        int numOpcion = 0;

        for (String elemento : elementos) {
            numOpcion++;
            System.out.printf(numOpcion + ")");
            System.out.println(" " + elemento);
        }
        System.out.println("\n"); // Salto de línea al final
    }
   
    private boolean tipoUsuarioValido(String tipoUsuario) {
        return TIPOS_USUARIO_VALIDOS.contains(tipoUsuario);
    }

    default String elegirTipoDeUsuario(String contorno, String mensaje) {
        String tipoUsuario = "";

        while (!tipoUsuarioValido(tipoUsuario)) {

            if (!tipoUsuario.equals("")) {
                espaciarPantallas();
                System.out.println(ERROR_USUARIO_INVALIDO);

                //Se repite el primer mensaje para que el usuario sepa que debe poner
                System.out.println(contorno);
                System.out.println(mensaje);
                System.out.println(contorno);

            }

            System.out.printf("Tipo de usuario: ");
            Scanner entrada = new Scanner(System.in);
            tipoUsuario = entrada.next().toUpperCase();

        }
        return tipoUsuario;
    }
    
    default boolean opcionValida(int canatidadOpciones, int opcionNum) {
        return 1 <= opcionNum && opcionNum <= canatidadOpciones;
    }

    default void espaciarPantallas() {
        System.out.println("*\n*\n*\n*\n*\n*\n*\n*\n");
    }

    default String[] ingresarUsuarioYContraseña() {
        System.out.printf("Nombre de usuario: ");
        Scanner entrada = new Scanner(System.in);
        String nombreUsuario = entrada.next().toUpperCase();

        System.out.printf("Contrasenia: ");
        String contraseñaUsuario = entrada.next();

        String[] usuarioYContraseña = {nombreUsuario, contraseñaUsuario};
        return usuarioYContraseña;
    }

    default int leerOpcionMenu(int cantidadOpciones) {
        int opcion = -1;
        Scanner entrada = new Scanner(System.in);
        do {
            System.out.printf("-");
            try {
                opcion = entrada.nextInt();
                if (!opcionValida(cantidadOpciones, opcion)) {
                    System.out.println(ERROR_OPCION_INVALIDA);
                }
            } catch (InputMismatchException e) {
                System.out.println(ERROR_OPCION_INVALIDA_CARACTER);
                entrada.next(); // Descarta la entrada inválida
            }
        } while (!opcionValida(cantidadOpciones, opcion));
        espaciarPantallas();
        return opcion;
    }

}
