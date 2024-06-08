package softdev;

public class Cliente extends Usuario {

    private int id;
    private String direccion;
    private String mail;
    private int telefono;

    public Cliente(int id, String direccion, String mail, int telefono, String nombre, String contrasenia) {
        super(nombre, contrasenia, 3);
        this.id = id;
        this.direccion = direccion;
        this.mail = mail;
        this.telefono = telefono;
    }

    @Override
    public int elegirAccion() {
        System.out.println("(1)Solicitar proyecto");
        System.out.println("(2)Consultar por proyecto");
        System.out.println("(3)Salir");
        return 0;
    }

    public void solicitarProyecto() {

    }

    public void consultarProyecto() {

    }

    public int getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getMail() {
        return mail;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

}
