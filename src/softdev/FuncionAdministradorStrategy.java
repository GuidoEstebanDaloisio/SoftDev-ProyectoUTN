package softdev;

import java.time.LocalDate;
import java.util.ArrayList;

public class FuncionAdministradorStrategy implements IAdministradorStrategy{

    @Override
    public Usuario crearUsuario(MenuAdministrador menu) {
        Usuario nuevoUsuario = null;

        String tipoUsuarioNombreYContraseña[] = menu.ingresarDatosParaNuevoUsuario();

        switch (tipoUsuarioNombreYContraseña[0]) {
            case "CLIENTE" -> {
                String direccionMailTelefono[] = menu.ingresarDatosDelCliente();
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
    public String[] solicitarEliminarUsuario(MenuAdministrador menu) {
        String tipoUsuarioEId[] = menu.ingresarDatosParaBorrarUsuario();
        return tipoUsuarioEId;
    }

    @Override
    public Desarrollador registrarDesarrollador(MenuAdministrador menu, int ultimoIdDesarrollador) {

        String nombreYHabilidad[] = menu.ingresarDatosParaNuevoDesarrollador();

        Desarrollador nuevoDesarrollador = new Desarrollador(ultimoIdDesarrollador + 1, nombreYHabilidad[0], nombreYHabilidad[1]);

        return nuevoDesarrollador;
    }

    @Override
    public String solicitarEliminarDesarrollador(MenuAdministrador menu) {
        return menu.ingresarDatosParaBorrarDesarrollador();
    }

    @Override
    public String[] solicitarAsignarDesarrollador(MenuAdministrador menu) {
        String idProyecto = menu.ingresarDatosDeProyecto();
        String idDesarrollador = menu.ingresarDatosDeDesarrolladorParaAsignar();

        String idProyectoEIdDesarrollador[] = {idProyecto, idDesarrollador};

        return idProyectoEIdDesarrollador;
    }

    @Override
    public String[] solicitarDesasignarDesarrollador(MenuAdministrador menu) {
        String idProyecto= menu.ingresarDatosDeProyecto();
        String idDesarrollador = menu.ingresarDatosDeDesarrolladorParaDesasignar();

        String idProyectoEIdDesarrollador[] = {idProyecto, idDesarrollador};

        return idProyectoEIdDesarrollador;
    }

    @Override
    public LocalDate solicitarFechaInicioProyecto(MenuAdministrador menu) {
        LocalDate fechaDeInicio = menu.ingresarFechaDeInicioDeProyecto();
        return fechaDeInicio;
    }

    @Override
    public void mostrarClientes(MenuAdministrador menu, ArrayList<Cliente> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("*******************************************");
            System.out.println("EN ESTE MOMENTO NO HAY CLIENTES REGISTRADOS");
            System.out.println("*******************************************");
        } else {
            mostrarDatosClientes(menu, clientes);
        }
    }

    private void mostrarDatosClientes(MenuAdministrador menu, ArrayList<Cliente> clientes) {
        menu.presentarListaDeClientes();

        for (Cliente cliente : clientes) {
            System.out.println("------------------");
            cliente.mostrarDatos();
        }
        System.out.println("------------------");
    }

    @Override
    public void mostrarGerentes(MenuAdministrador menu, ArrayList<Gerente> gerentes) {
        if (gerentes.isEmpty()) {
            System.out.println("*******************************************");
            System.out.println("EN ESTE MOMENTO NO HAY GERENTES REGISTRADOS");
            System.out.println("*******************************************");
        } else {
            mostrarDatosGerentes(menu, gerentes);
        }
    }

    private void mostrarDatosGerentes(MenuAdministrador menu, ArrayList<Gerente> gerentes) {
        menu.presentarListaDeGerentes();

        for (Gerente gerente : gerentes) {
            System.out.println("------------------");
            gerente.mostrarDatos();
        }
        System.out.println("------------------");
    }

    @Override
    public void mostrarAdministradores(MenuAdministrador menu, ArrayList<Administrador> administradores) {
        if (administradores.isEmpty()) {
            System.out.println("**************************************************");
            System.out.println("EN ESTE MOMENTO NO HAY ADMINISTRADORES REGISTRADOS");
            System.out.println("**************************************************");
        } else {
            mostrarDatosAdministradores(menu, administradores);
        }
    }

    private void mostrarDatosAdministradores(MenuAdministrador menu, ArrayList<Administrador> administradores) {
        menu.presentarListaDeAdministradores();

        for (Administrador administrador : administradores) {
            System.out.println("-------------------------");
            administrador.mostrarDatos();
        }
        System.out.println("-------------------------");
    }

    @Override
    public void mostrarDesarrolladoresDisponibles(MenuAdministrador menu, ArrayList<Desarrollador> desarrolladores) {
        if (desarrolladores.isEmpty()) {
            System.out.println("**************************************************");
            System.out.println("EN ESTE MOMENTO NO HAY DESARROLLADORES DISPONIBLES");
            System.out.println("**************************************************");
        } else {
            mostrarDatosDesarrolladores(menu, desarrolladores);
        }
    }

        @Override
    public void mostrarDesarrolladoresAsignados(MenuAdministrador menu, ArrayList<Desarrollador> desarrolladores) {
        if (desarrolladores.isEmpty()) {
            System.out.println("************************************************");
            System.out.println("EN ESTE MOMENTO NO HAY DESARROLLADORES ASIGNADOS");
            System.out.println("************************************************");
        } else {
            mostrarDatosDesarrolladores(menu, desarrolladores);
        }
    }
    
    private void mostrarDatosDesarrolladores(MenuAdministrador menu, ArrayList<Desarrollador> desarrolladores) {
        menu.presentarListaDeDesarrolladoresAsignados();

        for (Desarrollador desarrollador : desarrolladores) {
            System.out.println("-------------------------------------");
            desarrollador.mostrarDatos();
        }
        System.out.println("-------------------------------------");
    }



}
