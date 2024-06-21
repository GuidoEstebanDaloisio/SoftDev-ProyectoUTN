package softdev;

import java.util.ArrayList;

public class Administrador extends Usuario implements MenuAdministrador {
    
    private AccionUsuarioStrategy accionCrearUsuario;
    private AccionUsuarioStrategy accionSolicitarEliminarUsuario;
    private AccionUsuarioStrategy accionRegistrarDesarrollador;
    private AccionUsuarioStrategy accionSolicitarEliminarDesarrollador;
    private AccionUsuarioStrategy accionMostrarClientes;
    private AccionUsuarioStrategy accionMostrarGerentes;
    private AccionUsuarioStrategy accionMostrarAdministradores;
    private AccionUsuarioStrategy accionMostrarDesarrolladoresDisponibles;
    private AccionUsuarioStrategy accionMostrarDesarrolladoresAsignados;
            
    
    public Administrador(String nombre, String contraseña) {
        super(nombre, contraseña, 12);
        this.accionCrearUsuario = new FuncionCrearUsuario();
        this.accionSolicitarEliminarUsuario = new FuncionSolicitarEliminarUsuario();
        this.accionRegistrarDesarrollador = new FuncionRegistrarDesarrollador();
        this.accionSolicitarEliminarDesarrollador = new FuncionSolicitarEliminarDesarrollador();
        this.accionMostrarClientes = new FuncionMostrarClientes();
        this.accionMostrarGerentes = new FuncionMostrarGerentes();
        this.accionMostrarDesarrolladoresDisponibles = new FuncionMostrarDesarrolladoresDisponbles();
        this.accionMostrarDesarrolladoresAsignados = new FuncionMostrarDesarrolladoresAsignados();
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
    
    @Override
    public void mostrarDatos() {
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
    }
    
    public Usuario crearUsuario(){
        return (Usuario) accionCrearUsuario.ejecutarAccion();
    }
    
    public String[] solicitarEliminarUsuario(){
        return (String[]) accionSolicitarEliminarUsuario.ejecutarAccion();
    }
 
    public Desarrollador registrarDesarrollador(int ultimoIdDesarrollador) {
        return (Desarrollador) accionRegistrarDesarrollador.ejecutarAccion(ultimoIdDesarrollador);
    }
    
    public String [] solicitarEliminarDesarrollador(){
        return (String[]) accionSolicitarEliminarDesarrollador.ejecutarAccion();
    }

    public void mostrarClientes(ArrayList<Cliente> clientes){
        accionMostrarClientes.ejecutarAccion(clientes);
    }
    
    public void mostrarGerentes(ArrayList<Gerente> gerentes){
        accionMostrarGerentes.ejecutarAccion(gerentes);
    }
    
    public void mostrarAdministradores(ArrayList<Administrador> administradores){
        accionMostrarAdministradores.ejecutarAccion(administradores);
    }
    
    public void mostrarDesarrolladoresDisponibles(ArrayList<Desarrollador> desarrolladores){
        accionMostrarDesarrolladoresDisponibles.ejecutarAccion(desarrolladores);
    }    

    public void mostrarDesarrolladoresAsignados(ArrayList<Desarrollador> desarrolladores){
        accionMostrarDesarrolladoresAsignados.ejecutarAccion(desarrolladores);
    }  
}
