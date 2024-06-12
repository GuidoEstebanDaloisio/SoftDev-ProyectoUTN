package softdev;

public abstract class Usuario {

    protected int id;
    protected String nombre;
    protected String contrasenia;
    protected int cantidadDeOpciones;

    public Usuario(String nombre, String contrasenia, int cantidadDeOpciones) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.cantidadDeOpciones = cantidadDeOpciones;
    }

    public abstract String elegirAccion();

    public abstract String ejecutarAccion(int accionNum);

    public String getNombre() {
        return nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


}
