package softdev;

import java.util.ArrayList;

public class Sistema implements MenuInicio {

    private ArrayList<Desarrollador> desarrolladores;

    private ArrayList<Usuario> usuarios;

    private ArrayList<Proyecto> proyectos;

    private String usuarioActual;

    public Sistema() {
        // Inicializamos los ArrayLists
        usuarios = new ArrayList<>();
        desarrolladores = new ArrayList<>();
        proyectos = new ArrayList<>();

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
                Usuario nuevoUsuario = ((Administrador) usuario).crearUsuario();
                int id = obtenerUltimoIdUsuario(nuevoUsuario.getClass().getSimpleName()) + 1;
                nuevoUsuario.setId(id);
                guardarUsuario(nuevoUsuario);
                break;
            }
            case "BORRAR_USUARIO": {
                String tipoUsuarioIdYNombre[] = ((Administrador) usuario).solicitarEliminarUsuario();
                borrarUsuario(tipoUsuarioIdYNombre[0], tipoUsuarioIdYNombre[1], tipoUsuarioIdYNombre[2]);
                break;
            }
            case "NUEVO_DESARROLLADOR": {
                Desarrollador nuevoDesarrollador = ((Administrador) usuario).registrarDesarrollador(obtenerUltimoIdDesarrollador());
                guardarDesarrollador(nuevoDesarrollador);
                break;
            }
            case "BORRAR_DESARROLLADOR": {
                String desarrolladorIdYNombre[] = ((Administrador) usuario).solicitarEliminarDesarrollador();
                borrarDesarrollador(desarrolladorIdYNombre[0], desarrolladorIdYNombre[1]);
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
                ((Administrador) usuario).mostrarDesarrolladoresDisponbles(obtenerDesarrolladoresDisponibles());
                break;
            }
            case "VER_DESARROLLADORES_ASIGNADOS": {
                ((Administrador) usuario).mostrarDesarrolladoresAsignados(obtenerDesarrolladoresAsignados());
                break;
            }
            case "SALIR": {
                saludoDespedida();
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

    private int obtenerUltimoIdUsuario(String tipoUsuario) {
        int maxId = 0;
        for (Usuario usuario : usuarios) {
            if (usuario.getClass().getSimpleName().equals(tipoUsuario)) {
                int id = usuario.getId();
                if (id > maxId) {
                    maxId = id;
                }
            }
        }
        return maxId;
    }

    private int obtenerUltimoIdDesarrollador() {
        int maxId = 0;
        for (Desarrollador desarrollador : desarrolladores) {
            int id = desarrollador.getId();
            if (id > maxId) {
                maxId = id;
            }
        }
        return maxId;
    }

    private void guardarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    private void guardarDesarrollador(Desarrollador desarrollador) {
        desarrolladores.add(desarrollador);
    }

    private void borrarUsuario(String tipo, String idRecibida, String nombre) {
        int id = Integer.parseInt(idRecibida);

        // Buscar el usuario en la lista de usuarios
        for (Usuario usuario : usuarios) {
            if (usuario.compararIdYNombre(id, nombre) && usuario.getClass().getSimpleName().toUpperCase().equals(tipo)) {
                usuarios.remove(usuario); // Eliminar el usuario encontrado
                System.out.println("El usuario " + usuario.getClass().getSimpleName() + " " + nombre + " fue borrado exitosamente.");
                return;
            }
        }
        System.out.println("No se encontro ningun usuario con los datos especificados.");
    }

    private void borrarDesarrollador(String idRecibida, String nombre) {
        int id = Integer.parseInt(idRecibida);

        // Buscar el desarrollador en la lista de desarrolladores
        for (Desarrollador desarrollador : desarrolladores) {

            if (desarrollador.compararIdYNombre(id, nombre)) {
                desarrolladores.remove(desarrollador); // Eliminar el desarrollador encontrado
                System.out.println("El desarrollador " + nombre + " fue borrado exitosamente.");
                return;
            }
        }
        System.out.println("No se encontró ningún desarrollador con los datos especificados.");
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

    private ArrayList<Desarrollador> obtenerDesarrolladoresDisponibles() {
        ArrayList<Desarrollador> desarrolladoresDisponibles = new ArrayList<>();
        for (Desarrollador desarrollador : desarrolladores) {
            if (desarrollador.isDisponible()) {
                desarrolladoresDisponibles.add(desarrollador);
            }
        }
        return desarrolladoresDisponibles;
    }

    private ArrayList<Desarrollador> obtenerDesarrolladoresAsignados() {
        ArrayList<Desarrollador> desarrolladoresAsignados = new ArrayList<>();
        for (Desarrollador desarrollador : desarrolladores) {
            if (!desarrollador.isDisponible()) {
                desarrolladoresAsignados.add(desarrollador);
            }
        }
        return desarrolladoresAsignados;
    }
}
