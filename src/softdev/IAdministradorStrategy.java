package softdev;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public interface IAdministradorStrategy extends Serializable{
    public Usuario crearUsuario();
    
    public String[] solicitarEliminarUsuario();
	
    public Desarrollador registrarDesarrollador(int ultimoIdDesarrollador);
    
    public String [] solicitarEliminarDesarrollador();
    
    public String[] solicitarAsignarDesarrollador();
    
    public String[] solicitarDesasignarDesarrollador();
    
    public LocalDate solicitarFechaInicioProyecto();

    public void mostrarClientes(ArrayList<Cliente> clientes);
    
    public void mostrarGerentes(ArrayList<Gerente> gerentes);
    
    public void mostrarAdministradores(ArrayList<Administrador> administradores);
    
    public void mostrarDesarrolladoresDisponibles(ArrayList<Desarrollador> desarrolladores);

    public void mostrarDesarrolladoresAsignados(ArrayList<Desarrollador> desarrolladores);
}
