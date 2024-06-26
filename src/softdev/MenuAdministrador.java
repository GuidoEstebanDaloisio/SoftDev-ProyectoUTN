package softdev;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import static softdev.Constantes.*;

public interface MenuAdministrador extends Menu {

    default String[] ingresarDatosParaNuevoUsuario() {
        String contorno = "=================================================";
        String mensaje = "Ingrese los datos para registrar un nuevo usuario";

        System.out.println(contorno);
        System.out.println(mensaje);
        System.out.println(contorno);

        mostrarOpcionesValidas(TIPOS_USUARIO_VALIDOS);

        String tipoUsuario = elegirTipoDeUsuario(contorno, mensaje);
        String nombreYContraseña[] = interfazCrearUsuario(tipoUsuario);

        String tipoUsuarioNombreYContraseña[] = {tipoUsuario, nombreYContraseña[0], nombreYContraseña[1]};

        return tipoUsuarioNombreYContraseña;
    }

    default String[] ingresarDatosDeProyecto() {
        Scanner entrada = new Scanner(System.in);
        String contorno = "==============================";
        String mensaje = "Ingrese los datos del proyecto";

        System.out.println(contorno);
        System.out.println(mensaje);
        System.out.println(contorno);

        String id = ingresarId();

        System.out.printf("Titulo: ");
        String titulo = entrada.nextLine().toUpperCase();

        String idYTitulo[] = {id, titulo};

        return idYTitulo;
    }

    default String[] ingresarDatosDeDesarrolladorParaAsignar() {
        Scanner entrada = new Scanner(System.in);
        String contorno = "=====================================================";
        String mensaje = "Ingrese los datos del desarrollador que desea asignar";

        System.out.println(contorno);
        System.out.println(mensaje);
        System.out.println(contorno);

        String id = ingresarId();

        System.out.printf("Nombre: ");
        String nombre = entrada.nextLine().toUpperCase();

        String idYNombre[] = {id, nombre};

        return idYNombre;
    }

    default String[] ingresarDatosDeDesarrolladorParaDesasignar() {
        Scanner entrada = new Scanner(System.in);
        String contorno = "========================================================";
        String mensaje = "Ingrese los datos del desarrollador que desea desasignar";

        System.out.println(contorno);
        System.out.println(mensaje);
        System.out.println(contorno);

        String id = ingresarId();

        System.out.printf("Nombre: ");
        String nombre = entrada.nextLine().toUpperCase();

        String idYNombre[] = {id, nombre};
        espaciarPantallas();
        return idYNombre;
    }

    default LocalDate ingresarFechaDeInicioDeProyecto() {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaDeInicio = null;

        while (fechaDeInicio == null) {
            System.out.print("Ingrese la fecha de inicio del proyecto (formato: dd-MM-yyyy): ");
            String input = scanner.nextLine();

            try {
                fechaDeInicio = LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto. Por favor, intente de nuevo.");
            }
        }
        
        espaciarPantallas();
        return fechaDeInicio;
    }

    default String[] ingresarDatosParaBorrarUsuario() {
        Scanner entrada = new Scanner(System.in);
        String contorno = "==============================================";
        String mensaje = "Ingrese los datos del usuario que desea borrar";

        System.out.println(contorno);
        System.out.println(mensaje);
        System.out.println(contorno);

        String tipoUsuario = elegirTipoDeUsuario(contorno, mensaje);

        String id = ingresarId();

        System.out.printf("Nombre: ");
        String nombre = entrada.nextLine().toUpperCase();

        String tipoUsuarioIdYNombre[] = {tipoUsuario, id, nombre};
        espaciarPantallas();
        return tipoUsuarioIdYNombre;

    }

    default String[] ingresarDatosParaBorrarDesarrollador() {
        Scanner entrada = new Scanner(System.in);
        String contorno = "====================================================";
        String mensaje = "Ingrese los datos del desarrollador que desea borrar";

        System.out.println(contorno);
        System.out.println(mensaje);
        System.out.println(contorno);

        String id = ingresarId();

        System.out.printf("Nombre: ");
        String nombre = entrada.nextLine().toUpperCase();

        String idYNombre[] = {id, nombre.toUpperCase()};
        espaciarPantallas();
        return idYNombre;
    }

    default String[] ingresarDatosParaNuevoDesarrollador() {
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
                entrada.nextLine(); // Limpiar el buffer de entrada
            }
        } while (!telefonoValido);

        String[] direccionMailTelefono = {direccion.toUpperCase(), mail.toUpperCase(), telefono};

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
