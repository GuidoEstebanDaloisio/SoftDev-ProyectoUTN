package softdev;

import java.util.Scanner;

public interface MenuAdministrador extends Menu {

    default String[] ingresarDatosParaNuevoUsuario() {
        String contorno = "=================================================";
        String mensaje = "Ingrese los datos para registrar un nuevo usuario";

        System.out.println(contorno);
        System.out.println(mensaje);
        System.out.println(contorno);

        String tipoUsuario = elegirTipoDeUsuario(contorno, mensaje);
        String nombreYContraseña[] = interfazCrearUsuario(tipoUsuario);

        String tipoUsuarioNombreYContraseña[] = {tipoUsuario, nombreYContraseña[0], nombreYContraseña[1]};

        return tipoUsuarioNombreYContraseña;
    }

    default String[] ingresarDatosParaBorrarUsuario() {
        Scanner entrada = new Scanner(System.in);
        String contorno = "==============================================";
        String mensaje = "Ingrese los datos del usuario que desea borrar";

        System.out.println(contorno);
        System.out.println(mensaje);
        System.out.println(contorno);

        String tipoUsuario = elegirTipoDeUsuario(contorno, mensaje);

        System.out.printf("Id: ");
        String id = entrada.next();

        System.out.printf("Nombre: ");
        String nombre = entrada.next().toUpperCase();

        String tipoUsuarioIdYNombre[] = {tipoUsuario, id, nombre};
        espaciarPantallas();
        return tipoUsuarioIdYNombre;

    }

    default String[] ingresarDatosParaNuevoDesarrollador() {
        Scanner entrada = new Scanner(System.in);

        System.out.println("=======================================================");
        System.out.println("Ingrese los datos para registrar un nuevo desarrollador");
        System.out.println("=======================================================");

        System.out.printf("Nombre: ");
        String nombre = entrada.next();

        System.out.printf("Habilidad: ");
        String habilidad = entrada.next();

        String nombreYHabilidad[] = {nombre, habilidad};

        espaciarPantallas();
        return nombreYHabilidad;
    }

    default String elegirTipoDeUsuario(String contorno, String mensaje) {
        String tipoUsuario = "";

        while (!tipoUsuarioValido(tipoUsuario)) {

            if (!tipoUsuario.equals("")) {
                espaciarPantallas();
                System.out.println("          ------------------------");
                System.out.println("          TIPO DE USUARIO INVALIDO");
                System.out.println("          ------------------------");

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

    default void presentarListaDeDesarrolladoresDisponibles() {
        espaciarPantallas();
        System.out.println("-------------------------------------");
        System.out.println("Lista de desarrolladores disponibles:");
    }

    default void presentarListaDeDesarrolladoresAsignados() {
        espaciarPantallas();
        System.out.println("-----------------------------------");
        System.out.println("Lista de desarrolladores asignados:");
    }

}
