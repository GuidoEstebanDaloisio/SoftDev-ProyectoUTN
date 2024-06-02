package softdev;

public class Desarrollador {
    int id;
    String nombre;
    String habilidad;
    boolean disponible = true;

    public Desarrollador(int id, String nombre, String habilidad) {
        this.id = id;
        this.nombre = nombre;
        this.habilidad = habilidad;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
        

}