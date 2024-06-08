package softdev;

import java.util.InputMismatchException;
import java.util.Scanner;

public interface Menu {

    default boolean tipoUsuarioValido(String tipoUsuario) {
        return tipoUsuario.equals("CLIENTE") || tipoUsuario.equals("GERENTE") || tipoUsuario.equals("ADMINISTRADOR");
    }

    default boolean opcionValida(int canatidadOpciones, int opcionNum) {
        return 1 <= opcionNum && opcionNum <= canatidadOpciones;
    }

    default void espaciarPantallas() {
        System.out.println("*\n*\n*\n*\n*\n*\n*\n*\n");
    }

    default String deLetraInicialATipoDeUsuario(String letraInicial) {
        String tipoDeUsuario = letraInicial;
        switch (letraInicial) {
            case "C" ->
                tipoDeUsuario = "Cliente";
            case "G" ->
                tipoDeUsuario = "Gerente";
            case "A" ->
                tipoDeUsuario = "Administrador";
        }
        return tipoDeUsuario;
    }

    default String[] ingresarUsuarioYContraseña() {
        System.out.printf("Nombre de usuario: ");
        Scanner entrada = new Scanner(System.in);
        String nombreUsuario = entrada.next();

        System.out.printf("Contrasenia: ");
        String contraseniaUsuario = entrada.next();

        String[] usuarioYContrasenia = {nombreUsuario, contraseniaUsuario};

        return usuarioYContrasenia;
    }

    default int leerOpcionMenu(int cantidadOpciones) {
        int opcion = -1;
        Scanner entrada = new Scanner(System.in);
        do {
            System.out.printf("-");
            try {
                opcion = entrada.nextInt();
                if (!opcionValida(cantidadOpciones, opcion)) {
                    System.out.println("     -----------------------------------------------");
                    System.out.printf("     Opcion no valida, ingrse un numero entre 1 y %d\n", cantidadOpciones);
                    System.out.println("     -----------------------------------------------\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("     -------------------------------------------");
                System.out.println("     Entrada no valida, debe ingresar un numero.");
                System.out.println("     -------------------------------------------\n");
                entrada.next(); // Descarta la entrada inválida
            }
        } while (!opcionValida(cantidadOpciones, opcion));
        espaciarPantallas();
        return opcion;
    }

}
