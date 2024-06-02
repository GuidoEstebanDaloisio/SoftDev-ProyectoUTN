package softdev;

import java.util.Scanner;

public class EntradaSalida {

    public static String iniciarSesionComo() {
        String tipoUsuario = "";

        while (!tipoUsuarioValido(tipoUsuario)) {
            if (tipoUsuario != "") {
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
            tipoUsuario = deInicialATipoDeUsuario(tipoUsuario);

            espaciarPantallas();

        }

        return tipoUsuario;

    }

    public static String[] inicioDeSesion(String tipoUsuario) {
        System.out.println("=================================");
        System.out.println("Inicio de sesion de " + tipoUsuario);
        System.out.println("=================================");

        return ingresarUsuarioYContraseña();
    }

    public static int leerEntrada() {
        System.out.printf("-");
        Scanner entrada = new Scanner(System.in);
        return entrada.nextInt();
    }

    public static void error(String mensaje) {
        System.out.println("###################################################");
        System.out.println("ERROR: " + mensaje);
        System.out.println("###################################################");
    }

    //-------------------------PARA METODOS DEL ADMINISTRADOR-------------------------
    public static String[] interfazCrearUsuario() {
        String tipoUsuario = "";

        while (!tipoUsuarioValido(tipoUsuario)) {
            espaciarPantallas();

            if (tipoUsuario != "") {
                System.out.println("          ------------------------");
                System.out.println("          TIPO DE USUARIO INVALIDO");
                System.out.println("          ------------------------");
            }

            System.out.println("=================================================");
            System.out.println("Ingrese los datos para registrar un nuevo usuario");
            System.out.println("=================================================");

            System.out.printf("Tipo de usuario: ");
            Scanner entrada = new Scanner(System.in);
            tipoUsuario = entrada.next();
            tipoUsuario = tipoUsuario.toUpperCase();
            tipoUsuario = deInicialATipoDeUsuario(tipoUsuario);

        }

        String nombreYContraseña[] = ingresarUsuarioYContraseña();

        String tipoNombreContraseña[] = new String[3];
        tipoNombreContraseña[0] = tipoUsuario;          //Guardo tipo
        tipoNombreContraseña[1] = nombreYContraseña[0]; //Nombre
        tipoNombreContraseña[2] = nombreYContraseña[1]; //y Contraseña

        return tipoNombreContraseña;

    }

    public static String[] ingresarDatosDelCliente() {
        System.out.println("============================================================");
        System.out.println("Ahora ingrese los siguientes datos para registrar al cliente");
        System.out.println("============================================================");
        
        System.out.printf("Direccion: ");
        Scanner entrada = new Scanner(System.in);
        String direccion = entrada.next();
        
        System.out.printf("Mail: ");
        String mail = entrada.next();
        
        System.out.printf("Telefono: ");
        String telefono = entrada.next();

        String direccionMailTelefono[] = {direccion, mail, telefono};
        
        return direccionMailTelefono;
    }
    
    public static void presentarListaDeClientes(){
        espaciarPantallas();
        System.out.println("------------------");
        System.out.println("Lista de clientes:");
    }
    //-------------------------PARA METODOS DEL ADMINISTRADOR-------------------------

    //----------------------EXCLUSIVO DEL PRIMER USUARIO----------------------
    public static String[] primerInicioDeSesion() {
        System.out.println("========================================================");
        System.out.println("Ingrese los datos para registrar el primer administrador");
        System.out.println("========================================================");

        return ingresarUsuarioYContraseña();
    }

    public static void bienvenida(String nombreUsuario) {
        System.out.println("==================================================================================");
        System.out.println("                    Bienvenido " + nombreUsuario + " al sistema de SoftDev");
        System.out.println("==================================================================================");
        System.out.println("Al ser el primer usuario en nuestro sistema te asignamos el rol de Administrador.");
        System.out.println("Esto significa que tienes el poder de crear y eliminar usuarios de nuestro sistema,");
        System.out.println("ademas de asignar desarrolladores a los proyectos que sean solicitados.");
        System.out.println("Dicho esto, que quieres hacer?");
        System.out.println("==================================================================================");
    }

    //----------------------EXCLUSIVO DEL PRIMER USUARIO----------------------
    //------------------------------METODOS DE USO INTERNO------------------------------
    private static boolean tipoUsuarioValido(String tipoUsuario) {
        return tipoUsuario.equals("CLIENTE") || tipoUsuario.equals("GERENTE") || tipoUsuario.equals("ADMINISTRADOR");
    }

    private static void espaciarPantallas() {
        System.out.println("*\n*\n*\n*\n*\n*\n*\n*\n");
    }

    private static String deInicialATipoDeUsuario(String letraInicial) {

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

    private static String[] ingresarUsuarioYContraseña() {
        System.out.printf("Usuario: ");
        Scanner entrada = new Scanner(System.in);
        String nombreUsuario = entrada.next();

        System.out.printf("Contrasenia: ");
        String contraseniaUsuario = entrada.next();

        String usuarioYContrasenia[] = {nombreUsuario, contraseniaUsuario};

        espaciarPantallas();
        return usuarioYContrasenia;
    }
    //------------------------------METODOS DE USO INTERNO------------------------------

}
