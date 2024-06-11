package softdev;

public class Desarrollador {
    int id;
    String nombre;
    String habilidad;
    boolean disponible;

    public Desarrollador(int id, String nombre, String habilidad) {
        this.id = id;
        this.nombre = nombre;
        this.habilidad = habilidad;
        this.disponible = true;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHabilidad() {
        return habilidad;
    }
        

}