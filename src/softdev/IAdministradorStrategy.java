package softdev;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public interface IAdministradorStrategy extends Serializable{
    public Usuario crearUsuario(MenuAdministrador menu);
    
    public String[] solicitarEliminarUsuario(MenuAdministrador menu);
	
    public Desarrollador registrarDesarrollador(MenuAdministrador menu, int ultimoIdDesarrollador);
    
    public String  solicitarEliminarDesarrollador(MenuAdministrador menu);
    
    public String[] solicitarAsignarDesarrollador(MenuAdministrador menu);
    
    public String[] solicitarDesasignarDesarrollador(MenuAdministrador menu);
    
    public LocalDate solicitarFechaInicioProyecto(MenuAdministrador menu);

    public void mostrarClientes(MenuAdministrador menu, ArrayList<Cliente> clientes);
    
    public void mostrarGerentes(MenuAdministrador menu, ArrayList<Gerente> gerentes);
    
    public void mostrarAdministradores(MenuAdministrador menu, ArrayList<Administrador> administradores);
    
    public void mostrarDesarrolladoresDisponibles(MenuAdministrador menu, ArrayList<Desarrollador> desarrolladores);

    public void mostrarDesarrolladoresAsignados(MenuAdministrador menu, ArrayList<Desarrollador> desarrolladores);
}
