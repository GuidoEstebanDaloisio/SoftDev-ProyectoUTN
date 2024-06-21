package softdev;

import java.util.ArrayList;

public class Cliente extends Usuario implements MenuCliente {
    
    private AccionUsuarioStrategy accionSolicitarProyecto;
    private AccionUsuarioStrategy accionMostrarDatosDeProyectosDelUsuario;

    private String direccion;
    private String mail;
    private int telefono;

    public Cliente(String direccion, String mail, int telefono, String nombre, String contraseña) {
        super(nombre, contraseña, 3);
        this.direccion = direccion;
        this.mail = mail;
        this.telefono = telefono;
        this.accionSolicitarProyecto = new FuncionSolicitarProyecto();
        this.accionMostrarDatosDeProyectosDelUsuario = new FuncionMostrarDatosDeProyectosDelUsuario();
    }

    @Override
    public String elegirAccion() {
        System.out.println("=================================");
        System.out.println("(1)Solicitar proyecto");
        System.out.println("(2)Consultar por proyecto");
        System.out.println("(3)Salir");
        return ejecutarAccion(leerOpcionMenu(cantidadDeOpciones));
    }

    @Override
    public String ejecutarAccion(int accionNum) {
        String opcion = null;
        switch (accionNum) {
            case 1 -> {
                opcion = "SOLICITAR_PROYECTO";
            }
            case 2 -> {
                opcion = "CONSULTAR_PROYECTO";
            }
            case 3 -> {
                opcion = "SALIR";
            }
        }
        return opcion;
    }
    
    @Override
    public void mostrarDatos() {
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Direccion: " + direccion);
        System.out.println("Correo Electronico: " + mail);
        System.out.println("Telefono: " + telefono);
    }

    public Proyecto solicitarProyecto() {
        return (Proyecto) accionSolicitarProyecto.ejecutarAccion();
    }

    public void mostrarDatosDeProyectosDelUsuario(ArrayList<Proyecto> proyectos) {
        accionMostrarDatosDeProyectosDelUsuario.ejecutarAccion(proyectos);
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
