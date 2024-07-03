package softdev;

import java.time.LocalDate;
import java.util.ArrayList;

public class FuncionAdministradorStrategy implements IAdministradorStrategy, MenuAdministrador {

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
        String tipoUsuarioEId[] = ingresarDatosParaBorrarUsuario();
        return tipoUsuarioEId;
    }

    @Override
    public Desarrollador registrarDesarrollador(int ultimoIdDesarrollador) {

        String nombreYHabilidad[] = ingresarDatosParaNuevoDesarrollador();

        Desarrollador nuevoDesarrollador = new Desarrollador(ultimoIdDesarrollador + 1, nombreYHabilidad[0], nombreYHabilidad[1]);

        return nuevoDesarrollador;
    }

    @Override
    public String solicitarEliminarDesarrollador() {
        return ingresarDatosParaBorrarDesarrollador();
    }

    @Override
    public String[] solicitarAsignarDesarrollador() {
        String idProyecto = ingresarDatosDeProyecto();
        String idDesarrollador = ingresarDatosDeDesarrolladorParaAsignar();

        String idProyectoEIdDesarrollador[] = {idProyecto, idDesarrollador};

        return idProyectoEIdDesarrollador;
    }

    @Override
    public String[] solicitarDesasignarDesarrollador() {
        String idProyecto= ingresarDatosDeProyecto();
        String idDesarrollador = ingresarDatosDeDesarrolladorParaDesasignar();

        String idProyectoEIdDesarrollador[] = {idProyecto, idDesarrollador};

        return idProyectoEIdDesarrollador;
    }

    @Override
    public LocalDate solicitarFechaInicioProyecto() {
        LocalDate fechaDeInicio = ingresarFechaDeInicioDeProyecto();
        return fechaDeInicio;
    }

    @Override
    public void mostrarClientes(ArrayList<Cliente> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("*******************************************");
            System.out.println("EN ESTE MOMENTO NO HAY CLIENTES REGISTRADOS");
            System.out.println("*******************************************");
        } else {
            mostrarDatosClientes(clientes);
        }
    }

    private void mostrarDatosClientes(ArrayList<Cliente> clientes) {
        presentarListaDeClientes();

        for (Cliente cliente : clientes) {
            System.out.println("------------------");
            cliente.mostrarDatos();
        }
        System.out.println("------------------");
    }

    @Override
    public void mostrarGerentes(ArrayList<Gerente> gerentes) {
        if (gerentes.isEmpty()) {
            System.out.println("*******************************************");
            System.out.println("EN ESTE MOMENTO NO HAY GERENTES REGISTRADOS");
            System.out.println("*******************************************");
        } else {
            mostrarDatosGerentes(gerentes);
        }
    }

    private void mostrarDatosGerentes(ArrayList<Gerente> gerentes) {
        presentarListaDeGerentes();

        for (Gerente gerente : gerentes) {
            System.out.println("------------------");
            gerente.mostrarDatos();
        }
        System.out.println("------------------");
    }

    @Override
    public void mostrarAdministradores(ArrayList<Administrador> administradores) {
        if (administradores.isEmpty()) {
            System.out.println("**************************************************");
            System.out.println("EN ESTE MOMENTO NO HAY ADMINISTRADORES REGISTRADOS");
            System.out.println("**************************************************");
        } else {
            mostrarDatosAdministradores(administradores);
        }
    }

    private void mostrarDatosAdministradores(ArrayList<Administrador> administradores) {
        presentarListaDeAdministradores();

        for (Administrador administrador : administradores) {
            System.out.println("-------------------------");
            administrador.mostrarDatos();
        }
        System.out.println("-------------------------");
    }

    @Override
    public void mostrarDesarrolladoresDisponibles(ArrayList<Desarrollador> desarrolladores) {
        if (desarrolladores.isEmpty()) {
            System.out.println("**************************************************");
            System.out.println("EN ESTE MOMENTO NO HAY DESARROLLADORES DISPONIBLES");
            System.out.println("**************************************************");
        } else {
            mostrarDatosDesarrolladores(desarrolladores);
        }
    }

    private void mostrarDatosDesarrolladores(ArrayList<Desarrollador> desarrolladores) {
        presentarListaDeDesarrolladoresAsignados();

        for (Desarrollador desarrollador : desarrolladores) {
            System.out.println("-------------------------------------");
            desarrollador.mostrarDatos();
        }
        System.out.println("-------------------------------------");
    }

    @Override
    public void mostrarDesarrolladoresAsignados(ArrayList<Desarrollador> desarrolladores) {
        if (desarrolladores.isEmpty()) {
            System.out.println("************************************************");
            System.out.println("EN ESTE MOMENTO NO HAY DESARROLLADORES ASIGNADOS");
            System.out.println("************************************************");
        } else {
            mostrarDatosDesarrolladores(desarrolladores);
        }
    }

}
