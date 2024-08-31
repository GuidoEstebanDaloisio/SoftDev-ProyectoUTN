package softdev;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import static softdev.Constantes.*;

public class Menu implements Serializable {

    protected String ingresarId() {
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

    protected void mostrarOpcionesValidasEnVertical(List<String> elementos) {
        System.out.printf("Las opciones validas son:\n");
        int numOpcion = 0;

        for (String elemento : elementos) {
            numOpcion++;
            System.out.printf(numOpcion + ")");
            System.out.println(" " + elemento);
        }
        System.out.println("\n"); // Salto de línea al final
    }

    protected String ingresarOpcion(List<String> opcionesValidas, String mensajeParaEntrada) {
        Scanner entrada = new Scanner(System.in);

        String opcionNombre = null;
        int opcionNum = 0;

        do {
            System.out.printf(mensajeParaEntrada+": ");
            opcionNum = entrada.nextInt();

            opcionNombre = obtenerNombreDeLaOpcion(opcionesValidas, opcionNum);

        } while (opcionNombre == null);

        return opcionNombre;
    }
    
    private String obtenerNombreDeLaOpcion(List<String> opcionesValidas, int opcionNum) {
        int i = 0;
        String opcionNombre = null;

        for (String opcion : opcionesValidas) {
            i++;
            if (i == opcionNum) {
                opcionNombre = opcion;
            }
        }
        if (opcionNombre == null) {
            System.out.println(ERROR_OPCION_INVALIDA);
        }
        return opcionNombre;
    }

    private boolean tipoUsuarioValido(String tipoUsuario) {
        return TIPOS_USUARIO_VALIDOS.contains(tipoUsuario);
    }

    protected String elegirTipoDeUsuario(String contorno, String mensaje) {
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
            tipoUsuario = entrada.nextLine().toUpperCase();

        }
        return tipoUsuario;
    }

    private boolean esOpcionValida(int canatidadOpciones, int opcionNum) {
        return 1 <= opcionNum && opcionNum <= canatidadOpciones;
    }

    protected void espaciarPantallas() {
        System.out.println("*\n*\n*\n*\n*\n*\n*\n*\n");
    }

    protected String[] ingresarUsuarioYContraseña() {
        System.out.printf("Nombre de usuario: ");
        Scanner entrada = new Scanner(System.in);
        String nombreUsuario = entrada.nextLine().toUpperCase();

        System.out.printf("Contrasenia: ");
        String contraseñaUsuario = entrada.nextLine();

        String[] usuarioYContraseña = {nombreUsuario, contraseñaUsuario};
        return usuarioYContraseña;
    }

    public int leerOpcionMenu(int cantidadOpciones) {
        int opcion = -1;
        Scanner entrada = new Scanner(System.in);
        do {
            System.out.printf("-");
            try {
                opcion = entrada.nextInt();
                if (!esOpcionValida(cantidadOpciones, opcion)) {
                    System.out.println(ERROR_OPCION_INVALIDA);
                }
            } catch (InputMismatchException e) {
                System.out.println(ERROR_OPCION_INVALIDA_CARACTER);
                entrada.nextLine(); // Descarta la entrada inválida
            }
        } while (!esOpcionValida(cantidadOpciones, opcion));
        espaciarPantallas();
        return opcion;
    }

}
