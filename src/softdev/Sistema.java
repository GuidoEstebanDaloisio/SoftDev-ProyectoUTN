package softdev;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import static softdev.Constantes.*;

public class Sistema implements MenuInicio, Serializable {

    private ArrayList<Desarrollador> desarrolladores;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Proyecto> proyectos;

    private Usuario usuarioActual;

    private static final String USUARIOS_FILE = "usuarios.bin";
    private static final String DESARROLLADORES_FILE = "desarrolladores.bin";
    private static final String PROYECTOS_FILE = "proyectos.bin";

    public Sistema() {
        usuarioActual = null;
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

            usuarioActual = primerAdmin;
            bienvenidaPrimerUsuario(primerUsuarioYContraseña[0]);
            while (!salir) {
                String entrada = primerAdmin.elegirAccion();
                salir = ejecutarAccion(entrada);
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

            usuarioActual = usuarioLogueado;
            bienvenida(usuarioYContraseña[0]);
            while (!salir) {
                String entrada = usuarioLogueado.elegirAccion();
                salir = ejecutarAccion(entrada);
            }

            guardarDatos();
        }
        usuarioActual = null;
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
            System.out.println("*\n*\n*\n*\n");
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

    private boolean ejecutarAccion(String opcion) {
        boolean salir = false;

        switch (opcion) {
            case "NUEVO_USUARIO": {
                Usuario nuevoUsuario = ((Administrador) usuarioActual).crearUsuario();
                int id = obtenerUltimoIdUsuario(nuevoUsuario.getClass().getSimpleName()) + 1;
                nuevoUsuario.setId(id);
                guardarUsuario(nuevoUsuario);
                break;
            }
            case "BORRAR_USUARIO": {
                String tipoUsuarioIdYNombre[] = ((Administrador) usuarioActual).solicitarEliminarUsuario();
                borrarUsuario(tipoUsuarioIdYNombre[0], tipoUsuarioIdYNombre[1], tipoUsuarioIdYNombre[2]);
                break;
            }
            case "NUEVO_DESARROLLADOR": {
                Desarrollador nuevoDesarrollador = ((Administrador) usuarioActual).registrarDesarrollador(obtenerUltimoIdDesarrollador());
                guardarDesarrollador(nuevoDesarrollador);
                break;
            }
            case "BORRAR_DESARROLLADOR": {
                String desarrolladorIdYNombre[] = ((Administrador) usuarioActual).solicitarEliminarDesarrollador();
                borrarDesarrollador(desarrolladorIdYNombre[0], desarrolladorIdYNombre[1]);
                break;
            }
            case "ASIGNAR_DESARROLLADOR": {
                String idYTituloProyectoEIdYNombreDesarrollador[] = ((Administrador) usuarioActual).solicitarAsignarDesarrollador();
                boolean administradorValido = validarAdministradorSolicitado(obtenerDesarrolladoresDisponibles(), idYTituloProyectoEIdYNombreDesarrollador[2], idYTituloProyectoEIdYNombreDesarrollador[3]);
                boolean proyectoValido = validarProyectoSolicitadoParaAdministrarDesarrolladores(idYTituloProyectoEIdYNombreDesarrollador[0], idYTituloProyectoEIdYNombreDesarrollador[1]);

                if (administradorValido && proyectoValido) {
                    asignarDesarrollador(idYTituloProyectoEIdYNombreDesarrollador);
                } else if (!administradorValido && !proyectoValido) {
                    System.out.println("Los datos proporcionados no corresponden a un proyecto ni desarrollador disponible");
                } else if (!administradorValido) {
                    System.out.println("El administrador seleccionado no se encuentra disponible");
                } else if (!proyectoValido) {
                    System.out.println("El proyecto seleccionado no se encuentra disponible");
                }
                break;
            }
            case "DESASIGNAR_DESARROLLADOR": {
                String idYTituloProyectoEIdYNombreDesarrollador[] = ((Administrador) usuarioActual).solicitarDesasignarDesarrollador();
                boolean administradorValido = validarAdministradorSolicitado(obtenerDesarrolladoresAsignados(), idYTituloProyectoEIdYNombreDesarrollador[2], idYTituloProyectoEIdYNombreDesarrollador[3]);
                boolean proyectoValido = validarProyectoSolicitadoParaAdministrarDesarrolladores(idYTituloProyectoEIdYNombreDesarrollador[0], idYTituloProyectoEIdYNombreDesarrollador[1]);

                if (administradorValido && proyectoValido) {
                    desasignarDesarrollador(idYTituloProyectoEIdYNombreDesarrollador);
                } else if (!administradorValido && !proyectoValido) {
                    System.out.println("Los datos proporcionados no corresponden a un proyecto ni desarrollador disponible");
                } else if (!administradorValido) {
                    System.out.println("El administrador seleccionado no se encuentra disponible");
                } else if (!proyectoValido) {
                    System.out.println("El proyecto seleccionado no se encuentra disponible");
                }

                break;
            }
            case "VER_CLIENTES": {
                ((Administrador) usuarioActual).mostrarClientes(obtenerUsuariosPorTipo(Cliente.class.getSimpleName()));
                break;
            }
            case "VER_GERENTES": {
                ((Administrador) usuarioActual).mostrarGerentes(obtenerUsuariosPorTipo(Gerente.class.getSimpleName()));
                break;
            }
            case "VER_ADMINISTRADORES": {
                ((Administrador) usuarioActual).mostrarAdministradores(obtenerUsuariosPorTipo(Administrador.class.getSimpleName()));

                break;
            }
            case "VER_DESARROLLADORES_DISPONIBLES": {
                ((Administrador) usuarioActual).mostrarDesarrolladoresDisponibles(obtenerDesarrolladoresDisponibles());
                break;
            }
            case "VER_DESARROLLADORES_ASIGNADOS": {
                ((Administrador) usuarioActual).mostrarDesarrolladoresAsignados(obtenerDesarrolladoresAsignados());
                break;
            }
            case "NUEVO_PROYECTO": {
                Proyecto nuevoProyecto = ((Cliente) usuarioActual).solicitarNuevoProyecto();
                nuevoProyecto.setClienteSolicitante((Cliente) usuarioActual);
                nuevoProyecto.setId(obtenerUltimoIdProyecto() + 1);
                guardarProyecto(nuevoProyecto);
                break;
            }
            case "CONSULTAR_PROYECTO": {
                ((Cliente) usuarioActual).mostrarDatosDeProyectosDelUsuario(obtenerProyectosDelUsuario((Cliente) usuarioActual));
                break;
            }
            case "VER_PROYECTOS": {
                ((Gerente) usuarioActual).mostrarProyectos(proyectos);
                break;
            }
            case "APROBAR_PROYECTO": {
                String IdYTitulo[] = ((Gerente) usuarioActual).solicitarAprobarProyecto();

                if (validarProyectoSolicitadoParaDeterminarAprobacion(IdYTitulo[0], IdYTitulo[1], "APROBAR")) {
                    System.out.println("Proyecto aprobado");
                } else {
                    System.out.println("Los datos proporcionados no corresponden a un proyecto disponible");
                }
                break;
            }
            case "RECHAZAR_PROYECTO": {
                String IdYTitulo[] = ((Gerente) usuarioActual).solicitarRechazarProyecto();

                if (validarProyectoSolicitadoParaDeterminarAprobacion(IdYTitulo[0], IdYTitulo[1], "RECHAZAR")) {
                    System.out.println("Proyecto rechazado");
                } else {
                    System.out.println("Los datos proporcionados no corresponden a un proyecto disponible");
                }
                break;
            }
            case "FINALIZAR_PROYECTO": {
                String IdYTitulo[] = ((Gerente) usuarioActual).solicitarFinalizarProyecto();

                if (validarProyectoSolicitadoParaFinalizar(IdYTitulo[0], IdYTitulo[1])) {

                    System.out.println("Proyecto Finalizado");
                } else {
                    System.out.println("Los datos proporcionados no corresponden a un proyecto disponible");
                }
                break;

            }
            case "ACTUALIZAR_PROGRESO_PROYECTO": {
                String nuevoEstadoIdYTitulo[] = ((Gerente) usuarioActual).nuevoEstadoDelProyecto();

                cambiarEstadoDeProyecto(obtenerProyecto(nuevoEstadoIdYTitulo[1], nuevoEstadoIdYTitulo[2]), nuevoEstadoIdYTitulo[0]);
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
    }

    private void cambiarEstadoDeProyecto(Proyecto proyecto, String nuevoEstado) {
        if (proyecto.comprobarSiEstaDisponibleParaActualizarProgreso()) {
            try {
                proyecto.setProgreso(nuevoEstado);
                System.out.println("El estado del proyecto se ha cambiado a: " + nuevoEstado);
            } catch (Exception e) {
                System.out.println("Error al cambiar el estado del proyecto: " + e.getMessage());
                e.printStackTrace(); // Opcional: para ver la traza completa del error
            }
        } else {
            System.out.println("El estado del proyecto no se puede actualizar en este momento.");
        }
    }

    private boolean validarProyectoSolicitadoParaDeterminarAprobacion(String idRecibida, String titulo, String nuevoEstado) {
        boolean existeElProyecto = false;
        int id = Integer.parseInt(idRecibida);

        for (Proyecto proyecto : proyectos) {
            if (proyecto.compararId(id) && proyecto.compararTitulos(titulo) && proyecto.comprobarSiEstaEsperandoAprobacion()) {
                if (nuevoEstado.equals("RECHAZAR")) {
                    rechazarProyecto(proyecto);
                } else if (nuevoEstado.equals("APROBAR")) {
                    aprobarProyecto(proyecto);
                }

                existeElProyecto = true;
            }
        }
        return existeElProyecto;
    }

    private boolean validarProyectoSolicitadoParaFinalizar(String idRecibida, String titulo) {
        boolean existeElProyecto = false;
        int id = Integer.parseInt(idRecibida);

        for (Proyecto proyecto : proyectos) {
            if (proyecto.compararId(id) && proyecto.compararTitulos(titulo) && proyecto.comprobarSiEstaDisponibleParaFinalizar()) {
                finalizarProyecto(proyecto);
                existeElProyecto = true;
            }
        }
        return existeElProyecto;
    }

    private void finalizarProyecto(Proyecto proyecto) {
        proyecto.setProgreso(FINALIZADO);
        LocalDate fechaFin = ((Gerente) usuarioActual).ingresarFechaFin();
        proyecto.setFechaDeFinalizacion(fechaFin);
        proyecto.setProyectoFinalizado(true);
    }

    private boolean validarProyectoSolicitadoParaAdministrarDesarrolladores(String idRecibida, String titulo) {
        boolean existeElProyecto = false;
        int id = Integer.parseInt(idRecibida);

        for (Proyecto proyecto : proyectos) {
            if (proyecto.compararId(id) && proyecto.compararTitulos(titulo) && !proyecto.comprobarSiEstaEsperandoAprobacion() && !proyecto.comprobarSiFueRechazado()) {
                existeElProyecto = true;
            }
        }
        return existeElProyecto;
    }

    private boolean validarAdministradorSolicitado(ArrayList<Desarrollador> desarrolladores, String idRecibida, String nombre) {
        boolean existeDesarrollador = false;
        int id = Integer.parseInt(idRecibida);

        for (Desarrollador desarrollador : desarrolladores) {
            if (desarrollador.compararIdYNombre(id, nombre)) {
                existeDesarrollador = true;
            }
        }

        return existeDesarrollador;
    }

    private void asignarDesarrollador(String idYTituloProyectoEIdYNombreDesarrollador[]) {

        Desarrollador desarrollador = obtenerDesarrollador(idYTituloProyectoEIdYNombreDesarrollador[2], idYTituloProyectoEIdYNombreDesarrollador[3]);
        Proyecto proyecto = obtenerProyecto(idYTituloProyectoEIdYNombreDesarrollador[0], idYTituloProyectoEIdYNombreDesarrollador[1]);

        //Solo se cambiara el progreso a "en desarrollo" la primera vez que se le asigne un desarrollador, para no sobreescribir el estado cada vez que se añada un desarrollador
        if (!proyecto.hayDesarrolladores()) {

            //Y solo se guardara la fecha de inicio la primera vez que se guarde un desarrollador
            if (proyecto.getFechaDeInicio() == null) {
                LocalDate fechaInicio = ((Administrador) usuarioActual).solicitarFechaInicioProyecto();
                proyecto.setFechaDeInicio(fechaInicio);
            }

            proyecto.setProgreso(EN_DESARROLLO);
        }

        proyecto.asignarDesarrollador(desarrollador);
        desarrollador.setDisponible(false);

        System.out.println("Desarrollador asignado");
    }

    private void desasignarDesarrollador(String idYTituloProyectoEIdYNombreDesarrollador[]) {
        // Obtener el desarrollador y el proyecto
        Desarrollador desarrollador = obtenerDesarrollador(idYTituloProyectoEIdYNombreDesarrollador[2], idYTituloProyectoEIdYNombreDesarrollador[3]);
        Proyecto proyecto = obtenerProyecto(idYTituloProyectoEIdYNombreDesarrollador[0], idYTituloProyectoEIdYNombreDesarrollador[1]);

        // Desasignar desarrollador solo si existen y están asignados correctamente
        proyecto.desasignarDesarrollador(desarrollador);
        desarrollador.setDisponible(true);

        // Verificar si quedan desarrolladores asignados, y actualizar el estado del proyecto si es necesario
        if (!proyecto.hayDesarrolladores()) {
            proyecto.setProgreso(ESPERANDO_DESARROLLADOR);
            System.out.println("Todos los desarrolladores han sido desasignados. Proyecto ahora en espera de desarrolladores.");
        }
    }

    private void aprobarProyecto(Proyecto proyecto) {
        proyecto.setProgreso(ESPERANDO_DESARROLLADOR);
    }

    private void rechazarProyecto(Proyecto proyecto) {
        proyecto.setProgreso(RECHAZADO);
    }

    private Desarrollador obtenerDesarrollador(String idRecibida, String nombre) {
        Desarrollador desarrolladorEncontrado = null;
        int id = Integer.parseInt(idRecibida);

        for (Desarrollador desarrollador : desarrolladores) {
            if (desarrollador.compararIdYNombre(id, nombre)) {
                desarrolladorEncontrado = desarrollador;
            }
        }
        return desarrolladorEncontrado;
    }

    private Proyecto obtenerProyecto(String idRecibida, String titulo) {
        Proyecto proyectoEncontrado = null;
        int id = Integer.parseInt(idRecibida);

        for (Proyecto proyecto : proyectos) {
            if (proyecto.compararId(id) && proyecto.compararTitulos(titulo)) {
                proyectoEncontrado = proyecto;
            }
        }

        return proyectoEncontrado;
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

    private int obtenerUltimoIdProyecto() {
        int maxId = 0;
        for (Proyecto proyecto : proyectos) {
            int id = proyecto.getId();
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

    private void guardarProyecto(Proyecto proyecto) {
        proyectos.add(proyecto);
    }

    private void borrarUsuario(String tipo, String idRecibida, String nombre) {
        int id = Integer.parseInt(idRecibida);
        boolean usuarioEncontrado = false;

        Iterator<Usuario> iter = usuarios.iterator();
        while (iter.hasNext()) {
            Usuario usuario = iter.next();
            if (usuario.compararIdYNombre(id, nombre) && usuario.getClass().getSimpleName().toUpperCase().equals(tipo)) {
                iter.remove(); // Uso seguro del método remove del iterador
                System.out.println("El usuario " + usuario.getClass().getSimpleName() + " " + nombre + " fue borrado exitosamente.");
                usuarioEncontrado = true;
            }
        }

        if (!usuarioEncontrado) {
            System.out.println("No se encontro ningun usuario con los datos especificados.");
        }
    }

    private void borrarDesarrollador(String idRecibida, String nombre) {
        int id = Integer.parseInt(idRecibida);
        boolean desarrolladorEncontrado = false;

        Iterator<Desarrollador> iter = desarrolladores.iterator();
        while (iter.hasNext()) {
            Desarrollador desarrollador = iter.next();
            if (desarrollador.compararIdYNombre(id, nombre)) {
                iter.remove(); // Uso seguro del método remove del iterador
                System.out.println("El desarrollador " + nombre + " fue borrado exitosamente.");
                desarrolladorEncontrado = true;
            }
        }

        if (!desarrolladorEncontrado) {
            System.out.println("No se encontro ningun desarrollador con los datos especificados.");
        }
    }

    private ArrayList<Proyecto> obtenerProyectosDelUsuario(Cliente cliente) {
        ArrayList<Proyecto> proyectosDelUsuario = new ArrayList<>();
        for (Proyecto proyecto : proyectos) {
            if (proyecto.compararClientes(cliente)) {
                proyectosDelUsuario.add(proyecto);
            }
        }

        return proyectosDelUsuario;
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
