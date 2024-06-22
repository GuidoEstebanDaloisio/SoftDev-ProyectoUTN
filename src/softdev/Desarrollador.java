package softdev;

import java.io.Serializable;

public class Desarrollador implements Serializable {

    private int id;
    private String nombre;
    private String habilidad;
    private boolean disponible;

    public Desarrollador(int id, String nombre, String habilidad) {
        this.id = id;
        this.nombre = nombre;
        this.habilidad = habilidad;
        this.disponible = true;
    }

    void mostrarDatos() {
        System.out.println("Id: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Habilidad: " + habilidad);
    }

    public boolean compararIdYNombre(int id, String nombre) {
        return this.id == id && this.nombre.equals(nombre);
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public String getNombre() {
        return nombre;
    }
}
