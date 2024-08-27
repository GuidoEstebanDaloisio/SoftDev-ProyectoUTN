package softdev;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Usuario implements Serializable {

    protected int id;
    protected String nombre;
    protected String contraseña;
    protected int cantidadDeOpciones;
    protected Menu menu;

    public Usuario(String nombre, String contraseña, int cantidadDeOpciones, Menu menu) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.cantidadDeOpciones = cantidadDeOpciones;
        this.menu = menu;
    }

    public abstract String elegirAccion();

    public abstract String enviarAcionElegida(int accionNum);
    
    public abstract <R> R ejecutarAccion(String accion);
    
    public abstract <R> R ejecutarAccion(String accion, Object objeto);

    public abstract void mostrarDatos();

    public boolean compararId(int id) {
        return this.id == id ;
    }

    public boolean compararNombreYContraseña(String nombre, String contraseña) {
        return this.nombre.equals(nombre) && this.contraseña.equals(contraseña);
    }

    protected String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
