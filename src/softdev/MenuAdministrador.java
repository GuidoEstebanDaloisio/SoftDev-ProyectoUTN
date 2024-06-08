package softdev;

import java.util.Scanner;

public interface MenuAdministrador extends Menu {

    default String elegirTipoDeUsuario() {
        String tipoUsuario = "";

        while (!tipoUsuarioValido(tipoUsuario)) {

            if (!tipoUsuario.equals("")) {
                espaciarPantallas();
                System.out.println("          ------------------------");
                System.out.println("          TIPO DE USUARIO INVALIDO");
                System.out.println("          ------------------------");
            }

            System.out.println("=================================================");
            System.out.println("Ingrese los datos para registrar un nuevo usuario");
            System.out.println("=================================================");

            System.out.printf("Tipo de usuario: ");
            Scanner entrada = new Scanner(System.in);
            tipoUsuario = entrada.next().toUpperCase();
            tipoUsuario = deLetraInicialATipoDeUsuario(tipoUsuario);

        }
        return tipoUsuario;
    }

    default String[] interfazCrearUsuario(String tipoUsuario) {
        String[] nombreYContraseña = ingresarUsuarioYContraseña();

        if (!tipoUsuario.equals("CLIENTE")) {
            espaciarPantallas();
        }

        return nombreYContraseña;
    }

    default String[] ingresarDatosDelCliente() {

        System.out.printf("Direccion: ");
        Scanner entrada = new Scanner(System.in);
        String direccion = entrada.next();

        System.out.printf("Mail: ");
        String mail = entrada.next();

        System.out.printf("Telefono: ");
        String telefono = entrada.next();

        String[] direccionMailTelefono = {direccion, mail, telefono};

        espaciarPantallas();

        return direccionMailTelefono;
    }

    default void presentarListaDeClientes() {
        espaciarPantallas();
        System.out.println("------------------");
        System.out.println("Lista de clientes:");
    }
    
    default void presentarListaDeGerentes() {
        espaciarPantallas();
        System.out.println("------------------");
        System.out.println("Lista de gerentes:");
    }
    
    default void presentarListaDeAdministradores() {
        espaciarPantallas();
        System.out.println("-------------------------");
        System.out.println("Lista de administradores:");
    }
}
