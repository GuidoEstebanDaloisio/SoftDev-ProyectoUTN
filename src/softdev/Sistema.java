package softdev;

import java.util.ArrayList;
import static softdev.EntradaSalida.*;

public class Sistema {

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

                // Llamamos al método mostrarAcciones
                primerAdmin.mostrarAcciones();
                int entrada = leerEntrada();

                
                
                //Y ejecutamos la accion solicitada a la vez que consultamos si eligieron la opcion de salir
                salir = accionesDeUsuario(primerAdmin, entrada);
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

 private boolean accionesDeUsuario(Usuario usuario, int accionNum) {
        boolean salir = false;
        if (usuario instanceof Cliente) {
            switch (accionNum) {
                case 3:
                    salir = true;
                    break;
            }
        } else if (usuario instanceof Gerente) {
            switch (accionNum) {
                case 4:
                    salir = true;
                    break;
            }
        } else if (usuario instanceof Administrador) {
            switch (accionNum) {
                case 1: {
                    Usuario nuevoUsuario = ((Administrador) usuario).crearUsuario(ultimoIdCliente);
                    ultimoIdCliente++;
                    guardarUsuario(nuevoUsuario);
                    break;
                }
                case 5: {
                    ((Administrador) usuario).verClientes(clientes);
                    break;
                }
                case 10: {
                    salir = true;
                    break;
                }
                // Otras acciones para Administrador
            }
        } else {
            error("Tipo de usuario inválido");
        }
        return salir;
    }

    private void guardarUsuario(Usuario usuario) {
        if (usuario instanceof Cliente) {
            clientes.add((Cliente) usuario);
        } else if (usuario instanceof Gerente) {
            gerentes.add((Gerente) usuario);
        } else if (usuario instanceof Administrador) {
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
