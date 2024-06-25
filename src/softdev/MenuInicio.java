package softdev;

import static softdev.Constantes.TIPOS_USUARIO_VALIDOS;

public interface MenuInicio extends Menu {

    default String iniciarSesionComo() {
        String contorno = "==============================================";
        String mensaje = "Como que tipo de usuario desea iniciar sesion?";

        System.out.println(contorno);
        System.out.println(mensaje);
        System.out.println(contorno);

        mostrarOpcionesValidas(TIPOS_USUARIO_VALIDOS);

        String tipoUsuario = elegirTipoDeUsuario(contorno, mensaje);
        espaciarPantallas();

        return tipoUsuario;

    }

    default String[] inicioDeSesion(String tipoUsuario) {
        System.out.println("=================================");
        System.out.println("Inicio de sesion de " + tipoUsuario);
        System.out.println("=================================");

        return ingresarUsuarioYContraseña();
    }

    default void saludoDespedida() {
        System.out.println("===================================");
        System.out.println("Gracias por usar el sistema SoftDev");
        System.out.println("         Vuelva pronto!");
        System.out.println("===================================");
    }

    default void bienvenida(String nombreUsuario) {
        espaciarPantallas();
        System.out.println("====================================================================================");
        System.out.println("                    Bienvenido/a " + nombreUsuario + " al sistema de SoftDev");
    }

    //----------------------EXCLUSIVO DEL PRIMER USUARIO----------------------
    default String[] primerInicioDeSesion() {
        System.out.println("========================================================");
        System.out.println("Ingrese los datos para registrar el primer administrador");
        System.out.println("========================================================");

        return ingresarUsuarioYContraseña();
    }

    default void bienvenidaPrimerUsuario(String nombreUsuario) {
        bienvenida(nombreUsuario);
        System.out.println("====================================================================================");
        System.out.println("Al ser el primer usuario en nuestro sistema te asignamos el rol de Administrador.");
        System.out.println("Esto significa que tienes el poder de crear y eliminar usuarios de nuestro sistema,");
        System.out.println("ademas de asignar desarrolladores a los proyectos que sean solicitados.");
        System.out.println("Dicho esto, que quieres hacer?");
    }

    //----------------------EXCLUSIVO DEL PRIMER USUARIO----------------------
}
