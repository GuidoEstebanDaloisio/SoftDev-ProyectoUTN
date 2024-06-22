package softdev;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static softdev.Constantes.*;

public class Proyecto implements Serializable {

    private int id;
    private String titulo;
    private String descripcion;
    private Cliente clienteSolicitante;
    private String medioDeEncargo;
    private ArrayList<Desarrollador> desarrolladores;
    private LocalDate fechaDeInicio;
    private LocalDate fechaDeFinalizacion;


    private double presupuesto;
    private String progreso;
    private boolean proyectoFinalizado;

    public Proyecto(String titulo, String descripcion, String medioDeEncargo, double presupuesto) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.medioDeEncargo = medioDeEncargo;
        this.presupuesto = presupuesto;
        this.progreso = ESPERANDO_APROVACION;
        this.proyectoFinalizado = false;
        this.desarrolladores = new ArrayList<>();
        this.proyectoFinalizado = false;

    }

    public void setClienteSolicitante(Cliente clienteSolicitante) {
        this.clienteSolicitante = clienteSolicitante;
    }

    public  void asignarDesarrollador(Desarrollador desarrollador){
        desarrolladores.add(desarrollador);
    }
    
   
    public void setId(int id) {
        this.id = id;
    }
   
    public void setFechaDeInicio(LocalDate fechaDeInicio) {
        this.fechaDeInicio = fechaDeInicio;
    }

    public void setFechaDeFinalizacion(LocalDate fechaDeFinalizacion) {
        this.fechaDeFinalizacion = fechaDeFinalizacion;
    }
    
    public boolean hayDesarrolladores() {
    return !desarrolladores.isEmpty();
}

    public int getId() {
        return id;
    }  
    
    public String getTitulo() {
        return titulo;
    }
    
    public boolean comprobarSiEstaEsperandoAprobacion(){
        return progreso.equals(ESPERANDO_APROVACION);
    }
   
    public boolean comprobarSiFueRechazado(){
        return progreso.equals(RECHAZADO);
    }
    
    public void setProgreso(String progreso) {
        this.progreso = progreso;
    }

    public boolean compararClientes(Cliente cliente) {
        return clienteSolicitante.equals(cliente);
    }
    
    public boolean compararTitulos(String titulo){
        return this.titulo.equals(titulo);
    }
    
    public boolean compararId(int id){
        return this.id == id;
    }

    public void mostrarDatos() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        System.out.println("Id: "+id);
        System.out.println("Titulo: " + titulo);
        System.out.println("Descripcion: " + descripcion);
        System.out.println("Cliente: " + clienteSolicitante.getNombre());
        System.out.println("Medio de encargo: " + medioDeEncargo);
        System.out.println("Presupuesto: $" + presupuesto);
        System.out.println("Progreso: " + progreso);

        if (!progreso.equals(ESPERANDO_APROVACION)  && !progreso.equals(RECHAZADO)) {
            if (!desarrolladores.isEmpty()) {
                mostrarListaDeDesarrolladores();
                System.out.println("Fecha inicio: " + fechaDeInicio.format(formatter));
                if(proyectoFinalizado){
                   System.out.println("Fecha fin: " + fechaDeFinalizacion.format(formatter));                    
                }

            }
                
        }
    }

    private void mostrarListaDeDesarrolladores() {
        System.out.println("Desarrolladores: ");
        for (Desarrollador desarrollador : desarrolladores) {
            System.out.println("                -" + desarrollador.getNombre());
        }
    }

}
