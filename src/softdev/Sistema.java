package softdev;

import java.util.ArrayList;
import java.util.Map;

public class Sistema implements MenuInicio {

    private ArrayList<Cliente> clientes;
    private ArrayList<Gerente> gerentes;
    private ArrayList<Administrador> administradores;
    private ArrayList<Desarrollador> desarrolladores;

    private ArrayList<Usuario> usuarios;

    private String usuarioActual;
    private int ultimoIdCliente = 0;
    private int ultimoIdDesarrollador;

    public Sistema() {
        // Inicializamos los ArrayLists

        usuarios = new ArrayList<>();

        clientes = new ArrayList<>();
        gerentes = new ArrayList<>();
        administradores = new ArrayList<>();
        desarrolladores = new ArrayList<>();

    }

    public void iniciar() {
        boolean salir = false;

        if (usuarios.isEmpty()) {
            //Primero creamos el primer usuario que va a ser un Administrador
            String primerUsuarioYContraseña[];
            primerUsuarioYContraseña = primerInicioDeSesion();

            crearPrimerUsuario(primerUsuarioYContraseña[0], primerUsuarioYContraseña[1]);

            Administrador primerAdmin = (Administrador) usuarios.get(0);

            bienvenida(usuarioActual);

            while (!salir) {

                String entrada = primerAdmin.elegirAccion();

                salir = ejecutarAccion(entrada, primerAdmin);

            }

        } else {
            String tipoDeUsuarioQueInicioSesion = iniciarSesionComo();

            String usuarioYContraseña[] = inicioDeSesion(tipoDeUsuarioQueInicioSesion);

        }
    }

    private boolean ejecutarAccion(String opcion, Usuario usuario) {
        boolean salir = false;

        switch (opcion) {
            case "NUEVO_USUARIO": {
                Usuario nuevoUsuario = ((Administrador) usuario).crearUsuario(ultimoIdCliente);
                guardarUsuario(nuevoUsuario);
                break;
            }
            case "BORRAR_USUARIO": {
                salir = true;
                break;
            }
            case "NUEVO_DESARROLLADOR": {
                salir = true;
                break;
            }
            case "BORRAR_DESARROLLADOR": {
                salir = true;
                break;
            }
            case "ASIGNAR_DESARROLLADOR": {
                salir = true;
                break;
            }
            case "DESASIGNAR_DESARROLLADOR": {
                salir = true;
                break;
            }
            case "VER_CLIENTES": {

                ((Administrador) usuario).mostrarClientes(obtenerUsuariosPorTipo(Cliente.class.getSimpleName()));
                break;
            }
            case "VER_GERENTES": {
                ((Administrador) usuario).mostrarGerentes(obtenerUsuariosPorTipo(Gerente.class.getSimpleName()));
                break;
            }
            case "VER_ADMINISTRADORES": {
                ((Administrador) usuario).mostrarAdministradores(obtenerUsuariosPorTipo(Administrador.class.getSimpleName()));

                break;
            }
            case "VER_DESARROLLADORES_DISPONIBLES": {
                salir = true;
                break;
            }
            case "VER_DESARROLLADORES_ASIGNADOS": {
                salir = true;
                break;
            }
            case "SALIR": {
                salir = true;
                break;
            }
        }
        return salir;
    }

    private void crearPrimerUsuario(String primerNombreUsuario, String primeraContrasenia) {
        Administrador primerUsuario = new Administrador(primerNombreUsuario, primeraContrasenia);

        usuarios.add(primerUsuario);

        usuarioActual = primerNombreUsuario;
    }

    private void guardarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    private void setUltimoIdCliente(int ultimoIdCliente) {
        this.ultimoIdCliente = ultimoIdCliente;
    }

    private void setUltimoIdDesarrollador(int ultimoIdDesarrollador) {
        this.ultimoIdDesarrollador = ultimoIdDesarrollador;
    }

     private <T extends Usuario> ArrayList<T> obtenerUsuariosPorTipo(String tipoUsuario) {
        ArrayList<T> usuariosFiltrados = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario.getClass().getSimpleName().equals(tipoUsuario)) {
                usuariosFiltrados.add((T) usuario);
            }
        }
        return usuariosFiltrados;
    }
}
