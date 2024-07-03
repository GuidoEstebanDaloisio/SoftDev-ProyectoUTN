package softdev;

import java.time.LocalDate;
import java.util.ArrayList;

public class Gerente extends Usuario implements MenuGerente {

    private ISupervisorStrategy accionSupervisor;

    public Gerente(String nombre, String contraseña) {
        super(nombre, contraseña, 6);
        this.accionSupervisor = new FuncionSupervisorStrategy();
    }

    @Override
    public String elegirAccion() {
        System.out.println("=================================");
        System.out.println("(1)Ver proyectos solicitados");
        System.out.println("(2)Aprobar proyecto");
        System.out.println("(3)Rechazar proyecto");
        System.out.println("(4)Finalizar proyecto");
        System.out.println("(5)Actualizar estado del proyecto");
        System.out.println("(6)Salir");
        return ejecutarAccion(leerOpcionMenu(cantidadDeOpciones));
    }

    @Override
    public String ejecutarAccion(int accionNum) {
        String opcion = null;
        switch (accionNum) {
            case 1 -> {
                opcion = "VER_PROYECTOS";
            }
            case 2 -> {
                opcion = "APROBAR_PROYECTO";
            }
            case 3 -> {
                opcion = "RECHAZAR_PROYECTO";
            }
            case 4 -> {
                opcion = "FINALIZAR_PROYECTO";
            }
            case 5 -> {
                opcion = "ACTUALIZAR_PROGRESO_PROYECTO";
            }
            case 6 -> {
                opcion = "SALIR";
            }
        }
        return opcion;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
    }

    public void mostrarProyectos(ArrayList<Proyecto> proyectos) {
        accionSupervisor.mostrarProyectos(proyectos);
    }

    public String solicitarAprobarProyecto() {
        return accionSupervisor.solicitarAprobarProyecto();
    }

    public String solicitarRechazarProyecto() {
        return accionSupervisor.solicitarRechazarProyecto();
    }

    public String solicitarFinalizarProyecto(){
        return accionSupervisor.solicitarFinalizarProyecto();
    }
    
    public LocalDate ingresarFechaFinDeProyecto(){
        return accionSupervisor.ingresarFechaFinDeProyecto();
    }
    
    public String[] nuevoEstadoDelProyecto() {
        return accionSupervisor.nuevoEstadoDelProyecto();
    }
}
