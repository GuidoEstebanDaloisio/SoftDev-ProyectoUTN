package softdev;

import java.time.LocalDate;
import java.util.ArrayList;

public class Administrador extends Usuario {

    private IAdministradorStrategy accionAdministrador;

    public Administrador(String nombre, String contraseña) {
        super(nombre, contraseña, 12, new MenuAdministrador());
        this.accionAdministrador = new FuncionAdministradorStrategy();
    }

    @Override
    public String elegirAccion() {
        System.out.println("==================================================================================");
        System.out.println("(1)Crear usuario                        | (7)Ver clientes");
        System.out.println("(2)Eliminar usuario                     | (8)Ver gerentes");
        System.out.println("(3)Registrar desarrollador              | (9)Ver administradores");
        System.out.println("(4)Eliminar desarrollador               | (10)Ver desarrolladores diponibles");
        System.out.println("(5)Asignar desarrollador a un proyecto  | (11)Ver desarrolladores asignados");
        System.out.println("(6)Quitar desarrollador de un proyecto  | (12)Salir");

        return enviarAcionElegida(menu.leerOpcionMenu(cantidadDeOpciones));
    }

    @Override
    public String enviarAcionElegida(int accionNum) {
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

    @Override
    public <R> R ejecutarAccion(String accion) {
        switch (accion) {
            case "NUEVO_USUARIO" -> {
                return (R) crearUsuario();
            }
            case "BORRAR_USUARIO" -> {
                return (R) solicitarEliminarUsuario();
            }
            case "BORRAR_DESARROLLADOR" -> {
                return (R) solicitarEliminarDesarrollador();
            }
            case "ASIGNAR_DESARROLLADOR" -> {
                return (R) solicitarAsignarDesarrollador();

            }
            case "DESASIGNAR_DESARROLLADOR" -> {
                return (R) solicitarDesasignarDesarrollador();

            }
            case "PEDIR_FECHA" -> {
                return (R) solicitarFechaInicioProyecto();
            }
            default -> {
                System.out.println("Se solicito una accion no disponible");
                break;
            }
        }
        return null;
    }

    @Override
    public <R> R ejecutarAccion(String accion, Object elemento) {
        switch (accion) {
            case "NUEVO_DESARROLLADOR" -> {
                return (R) registrarDesarrollador((int) elemento);
            }
            case "VER_CLIENTES" -> {
                mostrarClientes((ArrayList<Cliente>) elemento);
                break;
            }
            case "VER_GERENTES" -> {
                mostrarGerentes((ArrayList<Gerente>) elemento);
                break;
            }
            case "VER_ADMINISTRADORES" -> {
                mostrarAdministradores((ArrayList<Administrador>) elemento);
                break;
            }
            case "VER_DESARROLLADORES_DISPONIBLES" -> {
                mostrarDesarrolladoresDisponibles((ArrayList<Desarrollador>) elemento);
                break;
            }
            case "VER_DESARROLLADORES_ASIGNADOS" -> {
                mostrarDesarrolladoresAsignados((ArrayList<Desarrollador>) elemento);
                break;
            }
            default -> {
                System.out.println("Se solicito una accion no disponible");
                break;
            }
        }
        return null;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
    }

    public Usuario crearUsuario() {
        return accionAdministrador.crearUsuario((MenuAdministrador) menu);
    }

    public String[] solicitarEliminarUsuario() {
        return accionAdministrador.solicitarEliminarUsuario((MenuAdministrador) menu);
    }

    public Desarrollador registrarDesarrollador(int ultimoIdDesarrollador) {
        return accionAdministrador.registrarDesarrollador((MenuAdministrador) menu, ultimoIdDesarrollador);
    }

    public String solicitarEliminarDesarrollador() {
        return accionAdministrador.solicitarEliminarDesarrollador((MenuAdministrador) menu);
    }

    public String[] solicitarAsignarDesarrollador() {
        return accionAdministrador.solicitarAsignarDesarrollador((MenuAdministrador) menu);
    }

    public String[] solicitarDesasignarDesarrollador() {
        return accionAdministrador.solicitarDesasignarDesarrollador((MenuAdministrador) menu);
    }

    public LocalDate solicitarFechaInicioProyecto() {
        return accionAdministrador.solicitarFechaInicioProyecto((MenuAdministrador) menu);
    }

    public void mostrarClientes(ArrayList<Cliente> clientes) {
        accionAdministrador.mostrarClientes((MenuAdministrador) menu, clientes);
    }

    public void mostrarGerentes(ArrayList<Gerente> gerentes) {
        accionAdministrador.mostrarGerentes((MenuAdministrador) menu, gerentes);
    }

    public void mostrarAdministradores(ArrayList<Administrador> administradores) {
        accionAdministrador.mostrarAdministradores((MenuAdministrador) menu, administradores);
    }

    public void mostrarDesarrolladoresDisponibles(ArrayList<Desarrollador> desarrolladores) {
        accionAdministrador.mostrarDesarrolladoresDisponibles((MenuAdministrador) menu, desarrolladores);
    }

    public void mostrarDesarrolladoresAsignados(ArrayList<Desarrollador> desarrolladores) {
        accionAdministrador.mostrarDesarrolladoresAsignados((MenuAdministrador) menu, desarrolladores);
    }

}
