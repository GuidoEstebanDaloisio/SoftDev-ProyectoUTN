package softdev;

import java.util.ArrayList;

public class Administrador extends Usuario implements MenuAdministrador {
    
    public Administrador(String nombre, String contrasenia) {
        super(nombre, contrasenia, 12);
    }

    
    
    @Override
    public String elegirAccion() {
        System.out.println("==================================================================================");
        System.out.println("(1)Crear usuario                        | (7)Ver clientes");
        System.out.println("(2)Eliminar usuario                     | (8)Ver gerentes");
        System.out.println("(3)Registrar desarrollador              | (9)Ver administradores");
        System.out.println("(4)Eliminar desarrolador                | (10)Ver desarrolladores diponibles");
        System.out.println("(5)Asignar desarrolador a un proyecto   | (11)Ver desarrolladores asignados");
        System.out.println("(6)Quitar desarrolador de un proyecto   | (12)Salir");

        return ejecutarAccion(leerOpcionMenu(cantidadDeOpciones));
    }

    @Override
    public String ejecutarAccion(int accionNum) {
        String opcion = null;
        switch (accionNum) {
            case 1 -> {
                opcion = "NUEVO_USUARIO";
            }
            case 2 -> {
                opcion = "BORRAR_USUARIO";
            }
            case 3 -> {
                opcion = "NUEVO_DESARROLLADOR";
            }
            case 4 -> {
                opcion = "BORRAR_DESARROLLADOR";
            }
            case 5 -> {
                opcion = "ASIGNAR_DESARROLLADOR";
            }
            case 6 -> {
                opcion = "DESASIGNAR_DESARROLLADOR";
            }
            case 7 -> {
                opcion = "VER_CLIENTES";
            }
            case 8 -> {
                opcion = "VER_GERENTES";
            }
            case 9 -> {
                opcion = "VER_ADMINISTRADORES";
            }
            case 10 -> {
                opcion = "VER_DESARROLLADORES_DISPONIBLES";
            }
            case 11 -> {
                opcion = "VER_DESARROLLADORES_ASIGNADOS";
            }
            case 12 -> {
                opcion = "SALIR";
            }
        }
        return opcion;
    }

    public Usuario crearUsuario() {
        Usuario nuevoUsuario = null;

        String tipoUsuarioNombreYContraseña[] = ingresarDatosParaNuevoUsuario();

        switch (tipoUsuarioNombreYContraseña[0]) {
            case "CLIENTE" -> {
                String direccionMailTelefono[] = ingresarDatosDelCliente();
                nuevoUsuario = new Cliente(direccionMailTelefono[0], direccionMailTelefono[1], Integer.parseInt(direccionMailTelefono[2]), tipoUsuarioNombreYContraseña[1], tipoUsuarioNombreYContraseña[2]);
            }
            case "GERENTE" ->
                nuevoUsuario = new Gerente(tipoUsuarioNombreYContraseña[1], tipoUsuarioNombreYContraseña[2]);
            case "ADMINISTRADOR" ->
                nuevoUsuario = new Administrador(tipoUsuarioNombreYContraseña[1], tipoUsuarioNombreYContraseña[2]);
            default -> {
            }
        }
        return nuevoUsuario;
    }

    public String[] solicitarEliminarUsuario() {
        String tipoUsuarioIdYNombre[] = ingresarDatosParaBorrarUsuario();
        
        return tipoUsuarioIdYNombre;
    }

    public Desarrollador registrarDesarrollador(int ultimoIdDesarrollador) {
        String nombreYHabilidad[] = ingresarDatosParaNuevoDesarrollador();

        Desarrollador nuevoDesarrollador = new Desarrollador(ultimoIdDesarrollador + 1, nombreYHabilidad[0], nombreYHabilidad[1]);

        return nuevoDesarrollador;
    }

    public String[] solicitarEliminarDesarrollador() {
        return ingresarDatosParaBorrarDesarrollador();
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
                System.out.println();
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
            presentarListaDeGerentes();

            for (Gerente gerente : gerentes) {
                System.out.println("------------------");
                System.out.println("Id: " + gerente.getId());
                System.out.println("Nombre: " + gerente.getNombre());
                System.out.println();
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
            presentarListaDeAdministradores();

            for (Administrador administrador : administradores) {
                System.out.println("-------------------------");
                System.out.println("Id: " + administrador.getId());
                System.out.println("Nombre: " + administrador.getNombre());
                System.out.println();
            }
            System.out.println("-------------------------");
        }
    }

    public void mostrarDesarrolladoresDisponbles(ArrayList<Desarrollador> desarrolladores) {
        if (desarrolladores.isEmpty()) {
            System.out.println("**************************************************");
            System.out.println("EN ESTE MOMENTO NO HAY DESARROLLADORES DISPONIBLES");
            System.out.println("**************************************************");
        } else {
            presentarListaDeDesarrolladoresDisponibles();

            for (Desarrollador desarrollador : desarrolladores) {
                System.out.println("-------------------------------------");
                System.out.println("Id: " + desarrollador.getId());
                System.out.println("Nombre: " + desarrollador.getNombre());
                System.out.println("Habilidad: " + desarrollador.getHabilidad());
                System.out.println();
            }
            System.out.println("-------------------------------------");
        }
    }

    public void mostrarDesarrolladoresAsignados(ArrayList<Desarrollador> desarrolladores) {
        if (desarrolladores.isEmpty()) {
            System.out.println("************************************************");
            System.out.println("EN ESTE MOMENTO NO HAY DESARROLLADORES ASIGNADOS");
            System.out.println("************************************************");
        } else {
            presentarListaDeDesarrolladoresAsignados();

            for (Desarrollador desarrollador : desarrolladores) {
                System.out.println("-----------------------------------");
                System.out.println("Id: " + desarrollador.getId());
                System.out.println("Nombre: " + desarrollador.getNombre());
                System.out.println("Habilidad: " + desarrollador.getHabilidad());
                System.out.println();
            }
            System.out.println("-----------------------------------");
        }
    }
}
