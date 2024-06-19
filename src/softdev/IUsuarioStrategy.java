package softdev;

import java.util.ArrayList;

public interface IUsuarioStrategy {

    public Usuario crearUsuario();

    public String[] solicitarEliminarUsuario();

    public Desarrollador registrarDesarrollador(int ultimoIdDesarrollador);

    public String[] solicitarEliminarDesarrollador();

    public void asignarDesarrollador();

    public void liberarDesarrollador();

    public void mostrarClientes(ArrayList<Cliente> clientes);

    public void mostrarGerentes(ArrayList<Gerente> gerentes);

    public void mostrarAdministradores(ArrayList<Administrador> administradores);

    public void mostrarDesarrolladoresDisponbles(ArrayList<Desarrollador> desarrolladores);

    public void mostrarDesarrolladoresAsignados(ArrayList<Desarrollador> desarrolladores);
}
