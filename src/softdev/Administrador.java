package softdev;

import java.util.ArrayList;
import static softdev.EntradaSalida.*;

public class Administrador extends Usuario {

    public Administrador(String nombre, String contrasenia) {
        super(nombre, contrasenia);
    }

    @Override
    public void mostrarAcciones() {
        System.out.println("(1)Crear usuario                        | (5)Ver clientes");
        System.out.println("(2)Eliminar usuario                     | (6)Ver gerentes");
        System.out.println("(3)Registrar desarrolador               | (7)Ver administradores");
        System.out.println("(4)Eliminar desarrolador                | (8)Ver desarrolladores dipobles");
        System.out.println("(5)Asignar desarrolador a un proyecto   | (9)Ver desarrolladores asignados");
        System.out.println("(6)Quitar desarrolador de un proyecto   | (10)Salir");
    }

    public Usuario crearUsuario(int ultimoIdCliente) {
        Usuario nuevoUsuario = null;

        String tipoNombreContraseña[] = interfazCrearUsuario();

        if (tipoNombreContraseña[0].equals("CLIENTE")) {
            String direccionMailTelefono[] = ingresarDatosDelCliente();

            nuevoUsuario = new Cliente(ultimoIdCliente + 1, direccionMailTelefono[0], direccionMailTelefono[1], Integer.parseInt(direccionMailTelefono[2]), tipoNombreContraseña[1], tipoNombreContraseña[2]);

            if (nuevoUsuario != null) {
                System.out.println("Cliente generado exitosamente");
            } else {
                System.out.println("Error al crear el cliente");
            }
        }

        return nuevoUsuario;
    }

    public void eliminarUsuario() {

    }

    public void registrarDesarrollador() {

    }

    public void eliminarDesarrollador() {

    }

    public void asignarDesarrollador() {

    }

    public void liberarDesarrollador() {

    }

    public void verClientes(ArrayList<Cliente> clientes) {

        presentarListaDeClientes();

        for (Cliente cliente : clientes) {
            System.out.println("-----------------------");
            System.out.println("ID: " + cliente.getId());
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Direccion: " + cliente.getDireccion());
            System.out.println("Correo Electronico: " + cliente.getMail());
            System.out.println("Telefono: " + cliente.getTelefono());
            System.out.println(); // Salto de línea entre cada cliente
        }
        System.out.println("-----------------------");

    }

    public void verGerentes() {

    }

    public void verAdministradores() {

    }

    public void verDesarrolladoresDisponbles() {

    }

    public void verDesarrolladoresAsignados() {

    }

}
