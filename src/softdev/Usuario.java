package softdev;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Usuario implements Serializable {

    protected int id;
    protected String nombre;
    protected String contraseña;
    protected int cantidadDeOpciones;

    public Usuario(String nombre, String contraseña, int cantidadDeOpciones) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.cantidadDeOpciones = cantidadDeOpciones;
    }

    public abstract String elegirAccion();

    public abstract String ejecutarAccion(int accionNum);

    public abstract void mostrarDatos();

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

    public int getId() {
        return id;
    }

    void verProyectos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
