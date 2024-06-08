package softdev;

import java.util.ArrayList;
import java.util.Map;

public class Sistema implements MenuInicio {

    private ArrayList<Cliente> clientes;
    private ArrayList<Gerente> gerentes;
    private ArrayList<Administrador> administradores;
    private ArrayList<Desarrollador> desarrolladores;

    private String usuarioActual;
    private String contraseñaDeUsuarioActual;
    private int ultimoIdCliente = 0;
    private int ultimoIdDesarrollador;

    public Sistema() {
        // Inicializamos los ArrayLists
        clientes = new ArrayList<>();
        gerentes = new ArrayList<>();
        administradores = new ArrayList<>();
        desarrolladores = new ArrayList<>();

    }

    public void iniciar() {
        boolean salir = false;

        
        
        
        
        if (administradores.isEmpty()) {
            //Primero creamos el primer usuario que va a ser un Administrador
            String primerUsuarioYContraseña[];
            primerUsuarioYContraseña = primerInicioDeSesion();

            crearPrimerUsuario(primerUsuarioYContraseña[0], primerUsuarioYContraseña[1]);

            //Ahora que el primer administrador esta en el sistema:
            //Obtenemos al primer administrador 
            Administrador primerAdmin = administradores.get(0);

            //Le damos la bienvenida
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

    private void crearPrimerUsuario(String primerNombreUsuario, String primeraContrasenia) {
        Administrador primerUsuario = new Administrador(primerNombreUsuario, primeraContrasenia);

        administradores.add(primerUsuario);

        usuarioActual = primerNombreUsuario;
        contraseñaDeUsuarioActual = primeraContrasenia;
    }

    private boolean ejecutarAccion(String opcion, Usuario usuario) {
        boolean salir = false;

        switch (opcion) {
            case "NUEVO_USUARIO": {
                String tipoUsuario = ((Administrador) usuario).seleccionarTipoUsuario();
                Usuario nuevoUsuario = ((Administrador) usuario).crearUsuario(ultimoIdCliente, tipoUsuario);
                guardarUsuario(nuevoUsuario, tipoUsuario);
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
                ((Administrador) usuario).verClientes(clientes);
                break;
            }
            case "VER_GERENTES": {
                ((Administrador) usuario).verGerentes(gerentes);
                break;
            }
            case "VER_ADMINISTRADORES": {
                ((Administrador) usuario).verAdministradores(administradores);

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

    private void guardarUsuario(Usuario usuario, String tipoUsuario) {
        if (tipoUsuario.equals("CLIENTE")) {
            clientes.add((Cliente) usuario);
        } else if (tipoUsuario.equals("GERENTE")) {
            gerentes.add((Gerente) usuario);
        } else if (tipoUsuario.equals("ADMINISTRADOR")) {
            administradores.add((Administrador) usuario);
        }
    }

    public void setUltimoIdCliente(int ultimoIdCliente) {
        this.ultimoIdCliente = ultimoIdCliente;
    }

    public void setUltimoIdDesarrollador(int ultimoIdDesarrollador) {
        this.ultimoIdDesarrollador = ultimoIdDesarrollador;
    }

}
