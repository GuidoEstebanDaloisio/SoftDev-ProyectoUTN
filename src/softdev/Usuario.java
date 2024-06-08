package softdev;

public abstract class Usuario {

    protected String nombre;
    protected String contrasenia;
    protected int cantidadDeOpciones;

    public Usuario(String nombre, String contrasenia, int cantidadDeOpciones) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.cantidadDeOpciones = cantidadDeOpciones;
    }



    public abstract int elegirAccion();

}
