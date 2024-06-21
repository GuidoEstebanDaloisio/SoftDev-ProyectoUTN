package softdev;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Usuario implements Serializable{

    protected int id;
    protected String nombre;
    protected String contraseña;
    protected int cantidadDeOpciones;
    protected IUsuarioStrategy accionDeUsuario;

    public Usuario(String nombre, String contraseña, int cantidadDeOpciones) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.cantidadDeOpciones = cantidadDeOpciones;
    }

    protected void setAccionDeUsuario(IUsuarioStrategy accionesDeUsuario) {
        this.accionDeUsuario = accionesDeUsuario;
    }

    public abstract String elegirAccion();

    public abstract String ejecutarAccion(int accionNum);

    public boolean compararIdYNombre(int id, String nombre) {
        return this.id == id && this.nombre.equals(nombre);
    }

    public boolean compararNombreYContraseña(String nombre, String contraseña) {
        return this.nombre.equals(nombre) && this.contraseña.equals(contraseña);
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
    public Usuario crearUsuario() {
        return accionDeUsuario.crearUsuario();
    }

    public String[] solicitarEliminarUsuario() {
        return accionDeUsuario.solicitarEliminarUsuario();
    }

    public Desarrollador registrarDesarrollador(int ultimoIdDesarrollador) {
        return accionDeUsuario.registrarDesarrollador(ultimoIdDesarrollador);
    }

    public String[] solicitarEliminarDesarrollador() {
        return accionDeUsuario.solicitarEliminarDesarrollador();
    }

    public void asignarDesarrollador() {
        accionDeUsuario.asignarDesarrollador();
    }

    public void liberarDesarrollador() {
        accionDeUsuario.liberarDesarrollador();
    }

    public void mostrarClientes(ArrayList<Cliente> clientes) {
        accionDeUsuario.mostrarClientes(clientes);
    }

    public void mostrarGerentes(ArrayList<Gerente> gerentes) {
        accionDeUsuario.mostrarGerentes(gerentes);
    }

    public void mostrarAdministradores(ArrayList<Administrador> administradores) {
        accionDeUsuario.mostrarAdministradores(administradores);
    }

    public void mostrarDesarrolladoresDisponbles(ArrayList<Desarrollador> desarrolladores) {
        accionDeUsuario.mostrarDesarrolladoresDisponbles(desarrolladores);
    }

    public void mostrarDesarrolladoresAsignados(ArrayList<Desarrollador> desarrolladores) {
        accionDeUsuario.mostrarDesarrolladoresAsignados(desarrolladores);
    }

}
