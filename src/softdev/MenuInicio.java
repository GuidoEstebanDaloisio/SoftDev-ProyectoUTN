package softdev;


public class MenuInicio extends Menu{

    public String[] inicioDeSesion() {
        System.out.println("================");
        System.out.println("Inicio de sesion");
        System.out.println("================");

        return ingresarUsuarioYContraseña();
    }

    public void saludoDespedida() {
        System.out.println("===================================");
        System.out.println("Gracias por usar el sistema SoftDev");
        System.out.println("         Vuelva pronto!");
        System.out.println("===================================");
    }

    public void bienvenida(String nombreUsuario) {
        espaciarPantallas();
        System.out.println("====================================================================================");
        System.out.println("                    Bienvenido/a " + nombreUsuario + " al sistema de SoftDev");
    }

    //----------------------EXCLUSIVO DEL PRIMER USUARIO----------------------
    public String[] primerInicioDeSesion() {
        System.out.println("========================================================");
        System.out.println("Ingrese los datos para registrar el primer administrador");
        System.out.println("========================================================");

        return ingresarUsuarioYContraseña();
    }

    public void saludarPrimerUsuario(String nombreUsuario) {
        bienvenida(nombreUsuario);
        System.out.println("====================================================================================");
        System.out.println("Al ser el primer usuario en nuestro sistema te asignamos el rol de Administrador.");
        System.out.println("Esto significa que tienes el poder de crear y eliminar usuarios de nuestro sistema,");
        System.out.println("ademas de asignar desarrolladores a los proyectos que sean solicitados.");
        System.out.println("Dicho esto, que quieres hacer?");
    }

    //----------------------EXCLUSIVO DEL PRIMER USUARIO----------------------
}
