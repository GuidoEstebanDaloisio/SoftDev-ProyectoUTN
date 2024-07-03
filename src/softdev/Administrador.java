package softdev;

import java.time.LocalDate;
import java.util.ArrayList;

public class Administrador extends Usuario implements MenuAdministrador {
    
    private IAdministradorStrategy accionAdministrador;
            
    
    public Administrador(String nombre, String contraseña) {
        super(nombre, contraseña, 12);
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
        return accionAdministrador.crearUsuario();
    }
    
    public String[] solicitarEliminarUsuario(){
        return accionAdministrador.solicitarEliminarUsuario();
    }
 
    public Desarrollador registrarDesarrollador(int ultimoIdDesarrollador) {
        return accionAdministrador.registrarDesarrollador(ultimoIdDesarrollador);
    }
    
    public String  solicitarEliminarDesarrollador(){
        return accionAdministrador.solicitarEliminarDesarrollador();
    }
    
    public String[] solicitarAsignarDesarrollador(){
       return accionAdministrador.solicitarAsignarDesarrollador();
    }
    
    public String[] solicitarDesasignarDesarrollador(){
       return accionAdministrador.solicitarDesasignarDesarrollador();
    }
    
    public LocalDate solicitarFechaInicioProyecto(){
        return accionAdministrador.solicitarFechaInicioProyecto();
    } 

    public void mostrarClientes(ArrayList<Cliente> clientes){
        accionAdministrador.mostrarClientes(clientes);
    }
    
    public void mostrarGerentes(ArrayList<Gerente> gerentes){
        accionAdministrador.mostrarGerentes(gerentes);
    }
    
    public void mostrarAdministradores(ArrayList<Administrador> administradores){
        accionAdministrador.mostrarAdministradores(administradores);
    }
    
    public void mostrarDesarrolladoresDisponibles(ArrayList<Desarrollador> desarrolladores){
        accionAdministrador.mostrarDesarrolladoresDisponibles(desarrolladores);
    }    

    public void mostrarDesarrolladoresAsignados(ArrayList<Desarrollador> desarrolladores){
         accionAdministrador.mostrarDesarrolladoresAsignados(desarrolladores);
    }  
}
