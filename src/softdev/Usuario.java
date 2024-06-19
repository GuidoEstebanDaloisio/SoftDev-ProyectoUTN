package softdev;

import java.util.ArrayList;

public abstract class Usuario {

    protected int id;
    protected String nombre;
    protected String contrasenia;
    protected int cantidadDeOpciones;
    protected IUsuarioStrategy accionDeUsuario;

    public Usuario(String nombre, String contrasenia, int cantidadDeOpciones) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.cantidadDeOpciones = cantidadDeOpciones;
    }

    protected void setAccionDeUsuario(IUsuarioStrategy accionesDeUsuario) {
        this.accionDeUsuario = accionesDeUsuario;
    }
    
    protected abstract String elegirAccion();

    protected abstract String ejecutarAccion(int accionNum);

    public boolean compararIdYNombre(int id, String nombre){
        return this.id == id && this.nombre.equals(nombre);
    }
    
    public boolean compararNombreYContrasenia(String nombre, String contrasenia){
        return this.nombre.equals(nombre) && this.contrasenia.equals(contrasenia);
    }
    
    protected String getNombre() {
        return nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected int getId() {
        return id;
    }
    
    
    //METODOS ADMINISTRADOR
    
    public Usuario crearUsuario(){
        return accionDeUsuario.crearUsuario();
    }

    public String[] solicitarEliminarUsuario(){
        return accionDeUsuario.solicitarEliminarUsuario();
    }

    public Desarrollador registrarDesarrollador(int ultimoIdDesarrollador){
        return accionDeUsuario.registrarDesarrollador(ultimoIdDesarrollador);
    }

    public String[] solicitarEliminarDesarrollador(){
        return accionDeUsuario.solicitarEliminarDesarrollador();
    }

    public void asignarDesarrollador(){
        accionDeUsuario.asignarDesarrollador();
    }

    public void liberarDesarrollador(){
        accionDeUsuario.liberarDesarrollador();
    }

    public void mostrarClientes(ArrayList<Cliente> clientes){
        accionDeUsuario.mostrarClientes(clientes);
    }

    public void mostrarGerentes(ArrayList<Gerente> gerentes){
        accionDeUsuario.mostrarGerentes(gerentes);
    }

    public void mostrarAdministradores(ArrayList<Administrador> administradores){
        accionDeUsuario.mostrarAdministradores(administradores);
    }

    public void mostrarDesarrolladoresDisponbles(ArrayList<Desarrollador> desarrolladores){
        accionDeUsuario.mostrarDesarrolladoresDisponbles(desarrolladores);
    }

    public void mostrarDesarrolladoresAsignados(ArrayList<Desarrollador> desarrolladores){
        accionDeUsuario.mostrarDesarrolladoresAsignados(desarrolladores);
    }


}
