package softdev;

import java.util.ArrayList;

public class AdministradorStrategy implements IUsuarioStrategy, MenuAdministrador {

    @Override
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
        }
        return nuevoUsuario;
    }

    @Override
    public String[] solicitarEliminarUsuario() {
        String tipoUsuarioIdYNombre[] = ingresarDatosParaBorrarUsuario();

        return tipoUsuarioIdYNombre;
    }

    @Override
    public Desarrollador registrarDesarrollador(int ultimoIdDesarrollador) {
        String nombreYHabilidad[] = ingresarDatosParaNuevoDesarrollador();

        Desarrollador nuevoDesarrollador = new Desarrollador(ultimoIdDesarrollador + 1, nombreYHabilidad[0], nombreYHabilidad[1]);

        return nuevoDesarrollador;
    }

    @Override
    public String[] solicitarEliminarDesarrollador() {
        return ingresarDatosParaBorrarDesarrollador();
    }

    @Override
    public void asignarDesarrollador() {

    }

    @Override
    public void liberarDesarrollador() {

    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
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
