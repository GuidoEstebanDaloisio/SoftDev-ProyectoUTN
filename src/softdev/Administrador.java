package softdev;

import java.util.ArrayList;

public class Administrador extends Usuario implements MenuAdministrador {

    public Administrador(String nombre, String contrasenia) {
        super(nombre, contrasenia, 12, Administrador.class.getSimpleName());
    }

    @Override
    public String elegirAccion() {
        System.out.println("==================================================================================");
        System.out.println("(1)Crear usuario                        | (7)Ver clientes");
        System.out.println("(2)Eliminar usuario                     | (8)Ver gerentes");
        System.out.println("(3)Registrar desarrolador               | (9)Ver administradores");
        System.out.println("(4)Eliminar desarrolador                | (10)Ver desarrolladores dipobles");
        System.out.println("(5)Asignar desarrolador a un proyecto   | (11)Ver desarrolladores asignados");
        System.out.println("(6)Quitar desarrolador de un proyecto   | (12)Salir");

        return ejecutarAccion(leerOpcionMenu(cantidadDeOpciones));
    }

    @Override
    public String ejecutarAccion(int accionNum) {
        String opcion = null;
        switch (accionNum) {
            case 1: {
                opcion = "NUEVO_USUARIO";
                break;
            }
            case 2: {
                opcion = "BORRAR_USUARIO";
                break;
            }
            case 3: {
                opcion = "NUEVO_DESARROLLADOR";
                break;
            }
            case 4: {
                opcion = "BORRAR_DESARROLLADOR";
                break;
            }
            case 5: {
                opcion = "ASIGNAR_DESARROLLADOR";
                break;
            }
            case 6: {
                opcion = "DESASIGNAR_DESARROLLADOR";
                break;
            }
            case 7: {
                opcion = "VER_CLIENTES";
                break;
            }
            case 8: {
                opcion = "VER_GERENTES";
                break;
            }
            case 9: {
                opcion = "VER_ADMINISTRADORES";
                break;
            }
            case 10: {
                opcion = "VER_DESARROLLADORES_DISPONIBLES";
                break;
            }
            case 11: {
                opcion = "VER_DESARROLLADORES_ASIGNADOS";
                break;
            }
            case 12: {
                opcion = "SALIR";
                break;
            }
        }
        return opcion;
    }


    public Usuario crearUsuario(int ultimoIdCliente) {
        Usuario nuevoUsuario = null;

        String tipoUsuario = elegirTipoDeUsuario();
        
        String nombreYContraseña[] = interfazCrearUsuario(tipoUsuario);

        switch (tipoUsuario) {
            case "CLIENTE" -> {
                String direccionMailTelefono[] = ingresarDatosDelCliente();
                nuevoUsuario = new Cliente(ultimoIdCliente + 1, direccionMailTelefono[0], direccionMailTelefono[1], Integer.parseInt(direccionMailTelefono[2]), nombreYContraseña[0], nombreYContraseña[1]);
            }
            case "GERENTE" ->
                nuevoUsuario = new Gerente(nombreYContraseña[0], nombreYContraseña[1]);
            case "ADMINISTRADOR" ->
                nuevoUsuario = new Administrador(nombreYContraseña[0], nombreYContraseña[1]);
            default -> {
            }
        }
        return nuevoUsuario;
    }

    public void eliminarUsuario() {

    }

    public void registrarDesarrollador() {

    }

    public void eliminarDesarrollador() {

    }

    public void asignarDesarrollador() {

    }

    public void liberarDesarrollador() {

    }

    public void mostrarClientes(ArrayList<Cliente> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("*******************************************");
            System.out.println("EN ESTE MOMENTO NO HAY CLIENTES REGISTRADOS");
            System.out.println("*******************************************");
        } else {
            presentarListaDeClientes();

            for (Cliente cliente : clientes) {
                System.out.println("------------------");
                System.out.println("ID: " + cliente.getId());
                System.out.println("Nombre: " + cliente.getNombre());
                System.out.println("Direccion: " + cliente.getDireccion());
                System.out.println("Correo Electronico: " + cliente.getMail());
                System.out.println("Telefono: " + cliente.getTelefono());
                System.out.println(); // Salto de línea entre cada cliente
            }
            System.out.println("------------------");
        }

    }

    public void mostrarGerentes(ArrayList<Gerente> gerentes) {
         if (gerentes.isEmpty()) {
            System.out.println("*******************************************");
            System.out.println("EN ESTE MOMENTO NO HAY GERENTES REGISTRADOS");
            System.out.println("*******************************************");
        } else {
            presentarListaDeClientes();

            for (Gerente gerente : gerentes) {
                System.out.println("------------------");
                System.out.println("Nombre: " + gerente.getNombre());
                System.out.println(); // Salto de línea entre cada cliente
            }
            System.out.println("------------------");
        }
    }

    public void mostrarAdministradores(ArrayList<Administrador> administradores) {
        if (administradores.isEmpty()) {
            System.out.println("**************************************************");
            System.out.println("EN ESTE MOMENTO NO HAY ADMINISTRADORES REGISTRADOS");
            System.out.println("**************************************************");
        } else {
            presentarListaDeClientes();

            for (Administrador administrador : administradores) {
                System.out.println("-------------------------");
                System.out.println("Nombre: " + administrador.getNombre());
                System.out.println(); // Salto de línea entre cada cliente
            }
            System.out.println("-------------------------");
        }
    }

    public void mostrarDesarrolladoresDisponbles() {

    }

    public void mostrarDesarrolladoresAsignados() {

    }
}
