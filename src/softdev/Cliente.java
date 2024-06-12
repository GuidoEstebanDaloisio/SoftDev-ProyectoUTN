package softdev;

public class Cliente extends Usuario {

    private String direccion;
    private String mail;
    private int telefono;

    public Cliente(String direccion, String mail, int telefono, String nombre, String contrasenia) {
        super(nombre, contrasenia, 3);
        this.direccion = direccion;
        this.mail = mail;
        this.telefono = telefono;
    }

    @Override
    public String elegirAccion() {
        System.out.println("(1)Solicitar proyecto");
        System.out.println("(2)Consultar por proyecto");
        System.out.println("(3)Salir");
        return null;
    }

    @Override
    public String ejecutarAccion(int accionNum) {
        return null;
    }

    public void solicitarProyecto() {

    }

    public void consultarProyecto() {

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
}
