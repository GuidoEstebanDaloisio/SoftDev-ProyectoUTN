package softdev;

public abstract class Usuario {

    protected String nombre;
    protected String contrasenia;

    public Usuario(String nombre, String contrasenia) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
    }

    public abstract void mostrarAcciones();

}
