package softdev;

import java.util.Scanner;

public interface MenuInicio extends Menu {

    default String iniciarSesionComo() {
        String tipoUsuario = "";

        while (!tipoUsuarioValido(tipoUsuario)) {
            if (!tipoUsuario.equals("")) {
                System.out.println("          ------------------------");
                System.out.println("          TIPO DE USUARIO INVALIDO");
                System.out.println("          ------------------------");
            }

            System.out.println("===============================================");
            System.out.println("¿Como que tipo de usuario desea iniciar sesion?");
            System.out.println("===============================================");
            System.out.println("(C) Cliente");
            System.out.println("(G) Gerente");
            System.out.println("(A) Administrador");

            System.out.printf("-");
            Scanner entrada = new Scanner(System.in);
            tipoUsuario = entrada.next();
            tipoUsuario = tipoUsuario.toUpperCase();
            tipoUsuario = deLetraInicialATipoDeUsuario(tipoUsuario);

            espaciarPantallas();

        }

        return tipoUsuario;

    }

    default String[] inicioDeSesion(String tipoUsuario) {
        System.out.println("=================================");
        System.out.println("Inicio de sesion de " + tipoUsuario);
        System.out.println("=================================");

        return ingresarUsuarioYContraseña();
    }
    
    
    //----------------------EXCLUSIVO DEL PRIMER USUARIO----------------------
    default String[] primerInicioDeSesion() {
        System.out.println("========================================================");
        System.out.println("Ingrese los datos para registrar el primer administrador");
        System.out.println("========================================================");

        return ingresarUsuarioYContraseña();
    }

    default void bienvenida(String nombreUsuario) {
        System.out.println("==================================================================================");
        System.out.println("                    Bienvenido " + nombreUsuario + " al sistema de SoftDev");
        System.out.println("==================================================================================");
        System.out.println("Al ser el primer usuario en nuestro sistema te asignamos el rol de Administrador.");
        System.out.println("Esto significa que tienes el poder de crear y eliminar usuarios de nuestro sistema,");
        System.out.println("ademas de asignar desarrolladores a los proyectos que sean solicitados.");
        System.out.println("Dicho esto, que quieres hacer?");
    }

    //----------------------EXCLUSIVO DEL PRIMER USUARIO----------------------
}


