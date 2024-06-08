package softdev;

public abstract class Usuario {

    protected String nombre;
    protected String contrasenia;
    protected int cantidadDeOpciones;
    protected String tipoUsuario;

    public Usuario(String nombre, String contrasenia, int cantidadDeOpciones, String tipoUsuario) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.cantidadDeOpciones = cantidadDeOpciones;
        this.tipoUsuario = tipoUsuario;
    }



    public abstract String elegirAccion();

    public abstract String ejecutarAccion(int accionNum);

    public String getNombre() {
        return nombre;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }   
}
