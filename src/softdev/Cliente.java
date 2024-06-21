package softdev;

import java.util.ArrayList;

public class Cliente extends Usuario implements MenuCliente {

    private String direccion;
    private String mail;
    private int telefono;

    public Cliente(String direccion, String mail, int telefono, String nombre, String contraseña) {
        super(nombre, contraseña, 3);
        this.direccion = direccion;
        this.mail = mail;
        this.telefono = telefono;
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

    public Proyecto solicitarProyecto() {
        String tituloDescripcionMedioYPresupuesto[] = ingresarDatosParaNuevoProyecto();

        double presupuesto = Double.parseDouble(tituloDescripcionMedioYPresupuesto[3]);

        Proyecto nuevoProyecto = new Proyecto(tituloDescripcionMedioYPresupuesto[0], tituloDescripcionMedioYPresupuesto[1], tituloDescripcionMedioYPresupuesto[2], presupuesto);
        return nuevoProyecto;
    }

    public void mostrarEstadoDeProyectos(ArrayList<Proyecto> proyectos) {
        if (proyectos.isEmpty()) {
            System.out.println("*******************************************");
            System.out.println("EN ESTE MOMENTO NO HAY CLIENTES REGISTRADOS");
            System.out.println("*******************************************");
        } else {
            presentarListaDeProyectos();

            for (Proyecto proyecto : proyectos) {
                System.out.println("-------------------------------");
                proyecto.mostrarDatos();
            }
            System.out.println("-------------------------------");
        }
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
