package softdev;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import static softdev.Constantes.*;

public class MenuAdministrador extends Menu {

    public String[] ingresarDatosParaNuevoUsuario() {
        System.out.println("=================================================");
        System.out.println("Ingrese los datos para registrar un nuevo usuario");
        System.out.println("=================================================");

        String mensaje = "Tipo de usuario";

        mostrarOpcionesValidasEnVertical(TIPOS_USUARIO_VALIDOS);
        String tipoUsuario = ingresarOpcion(TIPOS_USUARIO_VALIDOS, mensaje);

        String nombreYContraseña[] = interfazCrearUsuario(tipoUsuario);

        String tipoUsuarioNombreYContraseña[] = {tipoUsuario, nombreYContraseña[0], nombreYContraseña[1]};

        return tipoUsuarioNombreYContraseña;
    }

    public String ingresarDatosDeProyecto() {
        String contorno = "==============================";
        String mensaje = "Ingrese los datos del proyecto";

        System.out.println(contorno);
        System.out.println(mensaje);
        System.out.println(contorno);

        return ingresarId();
    }

    public String ingresarDatosDeDesarrolladorParaAsignar() {
        String contorno = "=====================================================";
        String mensaje = "Ingrese los datos del desarrollador que desea asignar";

        System.out.println(contorno);
        System.out.println(mensaje);
        System.out.println(contorno);

        return ingresarId();
    }

    public String ingresarDatosDeDesarrolladorParaDesasignar() {
        String contorno = "========================================================";
        String mensaje = "Ingrese los datos del desarrollador que desea desasignar";

        System.out.println(contorno);
        System.out.println(mensaje);
        System.out.println(contorno);

        return ingresarId();
    }

    public LocalDate ingresarFechaDeInicioDeProyecto() {
        return ingresarFechaDe("inicio del proyecto");
    }

    public String[] ingresarDatosParaBorrarUsuario() {
        System.out.println("==============================================");
        System.out.println("Ingrese los datos del usuario que desea borrar");
        System.out.println("==============================================");

        String mensaje = "Tipo de usuario";

        mostrarOpcionesValidasEnVertical(TIPOS_USUARIO_VALIDOS);
        String tipoUsuario = ingresarOpcion(TIPOS_USUARIO_VALIDOS, mensaje);

        String id = ingresarId();

        String tipoUsuarioEId[] = {tipoUsuario, id};
        espaciarPantallas();
        return tipoUsuarioEId;

    }

    public String ingresarDatosParaBorrarDesarrollador() {
        String contorno = "====================================================";
        String mensaje = "Ingrese los datos del desarrollador que desea borrar";

        System.out.println(contorno);
        System.out.println(mensaje);
        System.out.println(contorno);

        return ingresarId();
    }

    public String[] ingresarDatosParaNuevoDesarrollador() {
        Scanner entrada = new Scanner(System.in);

        System.out.println("=======================================================");
        System.out.println("Ingrese los datos para registrar un nuevo desarrollador");
        System.out.println("=======================================================");

        System.out.printf("Nombre: ");
        String nombre = entrada.nextLine();

        System.out.printf("Habilidad: ");
        String habilidad = entrada.nextLine();

        String nombreYHabilidad[] = {nombre.toUpperCase(), habilidad.toUpperCase()};

        espaciarPantallas();
        return nombreYHabilidad;
    }

    public String[] interfazCrearUsuario(String tipoUsuario) {
        String[] nombreYContraseña = ingresarUsuarioYContraseña();

        if (!tipoUsuario.equals("CLIENTE")) {
            espaciarPantallas();
        }

        return nombreYContraseña;
    }

    public String[] ingresarDatosDelCliente() {

        System.out.printf("Direccion: ");
        Scanner entrada = new Scanner(System.in);
        String direccion = entrada.nextLine();

        System.out.printf("Mail: ");
        String mail = entrada.nextLine();

        String telefono = "";
        boolean telefonoValido = false;

        do {
            try {
                System.out.printf("Telefono: ");
                telefono = entrada.nextLine();
                Long.parseLong(telefono); // Intentar convertir el teléfono a Long para validar que sea numérico
                telefonoValido = true;
            } catch (NumberFormatException e) {
                System.out.println(ERROR_TELEFONO_INVALIDO);
            }
        } while (!telefonoValido);

        String[] direccionMailTelefono = {direccion.toUpperCase(), mail.toUpperCase(), telefono};

        espaciarPantallas();

        return direccionMailTelefono;
    }

    public void presentarListaDeClientes() {
        espaciarPantallas();
        System.out.println("------------------");
        System.out.println("Lista de clientes:");
    }

    public void presentarListaDeGerentes() {
        espaciarPantallas();
        System.out.println("------------------");
        System.out.println("Lista de gerentes:");
    }

    public void presentarListaDeAdministradores() {
        espaciarPantallas();
        System.out.println("-------------------------");
        System.out.println("Lista de administradores:");
    }

    public void presentarListaDeDesarrolladoresDisponibles() {
        espaciarPantallas();
        System.out.println("-------------------------------------");
        System.out.println("Lista de desarrolladores disponibles:");
    }

    public void presentarListaDeDesarrolladoresAsignados() {
        espaciarPantallas();
        System.out.println("-----------------------------------");
        System.out.println("Lista de desarrolladores asignados:");
    }

}
