package softdev;

import java.io.*;
import java.util.ArrayList;

public class Sistema implements MenuInicio, Serializable {

    private ArrayList<Desarrollador> desarrolladores;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Proyecto> proyectos;
    private String usuarioActual;

    private static final String USUARIOS_FILE = "usuarios.bin";
    private static final String DESARROLLADORES_FILE = "desarrolladores.bin";
    private static final String PROYECTOS_FILE = "proyectos.bin";

    public Sistema() {
        // Inicializamos los ArrayLists
        usuarios = new ArrayList<>();
        desarrolladores = new ArrayList<>();
        proyectos = new ArrayList<>();

        // Verificar y crear los archivos si no existen
        verificarYCrearArchivo(USUARIOS_FILE);
        verificarYCrearArchivo(DESARROLLADORES_FILE);
        verificarYCrearArchivo(PROYECTOS_FILE);
    }

    public void iniciar() {
        boolean salir = false;

        cargarDatos();

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

            guardarDatos();

        } else {

            String tipoDeUsuarioQueInicioSesion;
            String usuarioYContraseña[];
            Usuario usuarioLogueado;

            do {
                tipoDeUsuarioQueInicioSesion = iniciarSesionComo();
                usuarioYContraseña = inicioDeSesion(tipoDeUsuarioQueInicioSesion);
                usuarioLogueado = loguearUsuario(usuarioYContraseña[0], usuarioYContraseña[1], tipoDeUsuarioQueInicioSesion);
            } while (usuarioLogueado == null);
            
            while (!salir) {
                String entrada = usuarioLogueado.elegirAccion();
                salir = ejecutarAccion(entrada, usuarioLogueado);
            }

            guardarDatos();
        }
    }

    private void verificarYCrearArchivo(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                System.out.println("Archivo " + nombreArchivo + " creado correctamente.");
            } catch (IOException e) {
                System.out.println("Error al crear el archivo " + nombreArchivo + ": " + e.getMessage());
            }
        }
    }

    private void guardarDatos() {
        try {
            guardarListaEnArchivo(USUARIOS_FILE, usuarios);
            guardarListaEnArchivo(DESARROLLADORES_FILE, desarrolladores);
            guardarListaEnArchivo(PROYECTOS_FILE, proyectos);
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    private void guardarListaEnArchivo(String fileName, ArrayList<?> lista) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            for (Object obj : lista) {
                out.writeObject(obj);
            }
        }
    }

    private void cargarDatos() {
        try {
            usuarios = cargarListaDeArchivo(USUARIOS_FILE);
            desarrolladores = cargarListaDeArchivo(DESARROLLADORES_FILE);
            proyectos = cargarListaDeArchivo(PROYECTOS_FILE);
            System.out.println("Datos cargados correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    private <T> ArrayList<T> cargarListaDeArchivo(String nombreArchivo) throws IOException, ClassNotFoundException {
        ArrayList<T> lista = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            Object obj;
            while ((obj = in.readObject()) != null) {
                lista.add((T) obj);
            }
        } catch (EOFException e) {
            // End of file reached, do nothing
        }
        return lista;
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

    private Usuario loguearUsuario(String nombre, String contraseña, String tipo) {

        for (Usuario usuario : usuarios) {
            if (usuario.compararNombreYContraseña(nombre, contraseña) && usuario.getClass().getSimpleName().toUpperCase().equals(tipo)) {
                return usuario;
            }
        }
        System.out.println("No se encontro ningun " + tipo + " con ese nombre y contrasenia");
        System.out.println("          Por favor ingrese un usuario valido");

        return null;
    }

    private void crearPrimerUsuario(String primerNombreUsuario, String primeraContraseña) {
        Administrador primerUsuario = new Administrador(primerNombreUsuario, primeraContraseña);

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
        boolean usuarioEncontrado = false;

        for (Usuario usuario : usuarios) {
            if (usuario.compararIdYNombre(id, nombre) && usuario.getClass().getSimpleName().toUpperCase().equals(tipo)) {
                usuarios.remove(usuario);
                System.out.println("El usuario " + usuario.getClass().getSimpleName() + " " + nombre + " fue borrado exitosamente.");
                usuarioEncontrado = true;
            }
        }
        if (usuarioEncontrado == false) {
            System.out.println("No se encontro ningun usuario con los datos especificados.");
        }

    }

    private void borrarDesarrollador(String idRecibida, String nombre) {
        int id = Integer.parseInt(idRecibida);
        boolean desarrolladorEncontrado = false;

        for (Desarrollador desarrollador : desarrolladores) {

            if (desarrollador.compararIdYNombre(id, nombre)) {
                desarrolladores.remove(desarrollador);
                System.out.println("El desarrollador " + nombre + " fue borrado exitosamente.");
                desarrolladorEncontrado = true;
            }
        }
        if (desarrolladorEncontrado == false) {
            System.out.println("No se encontró ningún desarrollador con los datos especificados.");
        }

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
