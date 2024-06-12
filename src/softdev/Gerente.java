package softdev;

public class Gerente extends Usuario {
   
    public Gerente( String nombre, String contrasenia) {
        super(nombre, contrasenia, 4);
    }

    @Override
    public String elegirAccion() {
        System.out.println("(1)Ver proyectos solicitados");
        System.out.println("(2)Aprobar proyecto");
        System.out.println("(3)Rechazar proyecto");
        System.out.println("(4)Salir");
        return null;
    }

    @Override
    public String ejecutarAccion(int accionNum) {
        return null;
    }

    public void verProyectos() {

    }

    public void aprobarProyecto() {

    }

    public void rechazarProyecto() {

    }
}
