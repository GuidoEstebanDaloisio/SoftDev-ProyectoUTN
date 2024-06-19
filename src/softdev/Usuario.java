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

    protected abstract String elegirAccion();

    protected abstract String ejecutarAccion(int accionNum);

    public boolean compararIdYNombre(int id, String nombre){
        return this.id == id && this.nombre.equals(nombre);
    }
    
    public boolean compararNombreYContrasenia(String nombre, String contrasenia){
        return this.nombre.equals(nombre) && this.contrasenia.equals(contrasenia);
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


}
