package softdev;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import static softdev.Constantes.*;

public class Sistema implements Serializable {

    private final MenuInicio menu;

    private ArrayList<Desarrollador> desarrolladores;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Proyecto> proyectos;

    private Usuario usuarioActual;

    private static final String USUARIOS_FILE = "usuarios.bin";
    private static final String DESARROLLADORES_FILE = "desarrolladores.bin";
    private static final String PROYECTOS_FILE = "proyectos.bin";

    public Sistema() {
        usuarioActual = null;

        menu = new MenuInicio();

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

        if (!contieneAdministrador()) {
            //Primero creamos el primer usuario que va a ser un Administrador
            String primerUsuarioYContraseña[];
            primerUsuarioYContraseña = menu.primerInicioDeSesion();

            crearPrimerUsuario(primerUsuarioYContraseña[0], primerUsuarioYContraseña[1]);

            Administrador primerAdmin = (Administrador) usuarios.get(0);

            usuarioActual = primerAdmin;
            menu.saludarPrimerUsuario(primerUsuarioYContraseña[0]);
            while (!salir) {
                String entrada = primerAdmin.elegirAccion();
                salir = ejecutarAccion(entrada);
            }

            guardarDatos();

        } else {

            String usuarioYContraseña[];
            Usuario usuarioLogueado;

            do {
                usuarioYContraseña = menu.inicioDeSesion();
                usuarioLogueado = loguearUsuario(usuarioYContraseña[0], usuarioYContraseña[1]);
            } while (usuarioLogueado == null);

            usuarioActual = usuarioLogueado;
            menu.bienvenida(usuarioYContraseña[0]);
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
                Usuario nuevoUsuario = null;
                do {
                    nuevoUsuario = usuarioActual.ejecutarAccion(opcion);
                } while (!validarUsuario(nuevoUsuario));

                int id = obtenerUltimoIdUsuario(nuevoUsuario.getClass()) + 1;
                nuevoUsuario.setId(id);
                guardarUsuario(nuevoUsuario);
                break;
            }
            case "BORRAR_USUARIO": {
                String tipoUsuarioEId[] = usuarioActual.ejecutarAccion(opcion);

                borrarUsuario(tipoUsuarioEId[0], tipoUsuarioEId[1]);
                break;
            }
            case "NUEVO_DESARROLLADOR": {
                Desarrollador nuevoDesarrollador = usuarioActual.ejecutarAccion(opcion, obtenerUltimoIdDesarrollador());

                guardarDesarrollador(nuevoDesarrollador);
                break;
            }
            case "BORRAR_DESARROLLADOR": {
                String idDesarrollador = usuarioActual.ejecutarAccion(opcion);

                borrarDesarrollador(idDesarrollador);
                break;
            }
            case "ASIGNAR_DESARROLLADOR": {
                String idProyectoEIdDesarrollador[] = usuarioActual.ejecutarAccion(opcion);

                boolean administradorValido = validarAdministradorSolicitado(obtenerDesarrolladoresDisponibles(), idProyectoEIdDesarrollador[1]);
                boolean proyectoValido = validarProyectoSolicitadoParaAdministrarDesarrolladores(idProyectoEIdDesarrollador[0]);

                if (administradorValido && proyectoValido) {
                    asignarDesarrollador(idProyectoEIdDesarrollador);
                } else if (!administradorValido && !proyectoValido) {
                    System.out.println("Los datos proporcionados no corresponden a un proyecto ni desarrollador disponible");
                } else if (!administradorValido) {
                    System.out.println("El desarrollador seleccionado no se encuentra disponible");
                } else if (!proyectoValido) {
                    System.out.println("El proyecto seleccionado no se encuentra disponible");
                }
                break;
            }
            case "DESASIGNAR_DESARROLLADOR": {
                String idProyectoEIdDesarrollador[] = usuarioActual.ejecutarAccion(opcion);

                boolean administradorValido = validarAdministradorSolicitado(obtenerDesarrolladoresAsignados(), idProyectoEIdDesarrollador[1]);
                boolean proyectoValido = validarProyectoSolicitadoParaAdministrarDesarrolladores(idProyectoEIdDesarrollador[0]);

                if (administradorValido && proyectoValido) {
                    desasignarDesarrollador(idProyectoEIdDesarrollador);
                } else if (!administradorValido && !proyectoValido) {
                    System.out.println("Los datos proporcionados no corresponden a un proyecto ni desarrollador disponible");
                } else if (!administradorValido) {
                    System.out.println("El desarrollador seleccionado no se encuentra disponible");
                } else if (!proyectoValido) {
                    System.out.println("El proyecto seleccionado no se encuentra disponible");
                }

                break;
            }
            case "VER_CLIENTES": {
                usuarioActual.ejecutarAccion(opcion, obtenerUsuariosPorTipo(Cliente.class));

                break;
            }
            case "VER_GERENTES": {
                usuarioActual.ejecutarAccion(opcion, obtenerUsuariosPorTipo(Gerente.class));

                break;
            }
            case "VER_ADMINISTRADORES": {
                usuarioActual.ejecutarAccion(opcion, obtenerUsuariosPorTipo(Administrador.class));

                break;
            }
            case "VER_DESARROLLADORES_DISPONIBLES": {
                usuarioActual.ejecutarAccion(opcion, obtenerDesarrolladoresDisponibles());

                break;
            }
            case "VER_DESARROLLADORES_ASIGNADOS": {
                usuarioActual.ejecutarAccion(opcion, obtenerDesarrolladoresAsignados());
                break;
            }
            case "NUEVO_PROYECTO": {
                Proyecto nuevoProyecto = usuarioActual.ejecutarAccion(opcion);

                nuevoProyecto.setClienteSolicitante(usuarioActual);
                nuevoProyecto.setId(obtenerUltimoIdProyecto() + 1);
                guardarProyecto(nuevoProyecto);
                break;
            }
            case "CONSULTAR_PROYECTO": {
                usuarioActual.ejecutarAccion(opcion, obtenerProyectosDelUsuario((Cliente) usuarioActual));

                break;
            }
            case "VER_PROYECTOS": {
                usuarioActual.ejecutarAccion(opcion, proyectos);
                break;
            }
            case "APROBAR_PROYECTO": {
                String id = usuarioActual.ejecutarAccion(opcion);

                if (validarProyectoSolicitadoParaDeterminarAprobacion(id, "APROBAR")) {
                    System.out.println("Proyecto aprobado");
                } else {
                    System.out.println("Los datos proporcionados no corresponden a un proyecto disponible");
                }
                break;
            }
            case "RECHAZAR_PROYECTO": {
                String id = usuarioActual.ejecutarAccion(opcion);

                if (validarProyectoSolicitadoParaDeterminarAprobacion(id, "RECHAZAR")) {
                    System.out.println("Proyecto rechazado");
                } else {
                    System.out.println("Los datos proporcionados no corresponden a un proyecto disponible");
                }
                break;
            }
            case "FINALIZAR_PROYECTO": {
                String id = usuarioActual.ejecutarAccion(opcion);

                if (validarProyectoSolicitadoParaFinalizar(id)) {

                    System.out.println("Proyecto Finalizado");
                } else {
                    System.out.println("Los datos proporcionados no corresponden a un proyecto disponible");
                }
                break;

            }
            case "ACTUALIZAR_PROGRESO_PROYECTO": {
                String nuevoEstadoYId[] = usuarioActual.ejecutarAccion(opcion);

                cambiarEstadoDeProyecto(obtenerProyecto(nuevoEstadoYId[1]), nuevoEstadoYId[0]);
                break;
            }

            case "SALIR": {
                menu.saludoDespedida();
                salir = true;
                break;
            }
        }
        return salir;
    }

    public boolean esUltimoAdministrador(Usuario usuario) {
        int contadorAdmin = 0;

        for (Usuario u : usuarios) {
            if (u instanceof Administrador) {
                contadorAdmin++;
            }
        }

        return contadorAdmin == 1 && usuario instanceof Administrador;
    }

    private Usuario loguearUsuario(String nombre, String contraseña) {

        for (Usuario usuario : usuarios) {
            if (usuario.compararNombreYContraseña(nombre, contraseña)) {
                return usuario;
            }
        }
        System.out.println("No se encontro ningun usuario con ese nombre y contrasenia");
        System.out.println("        Por favor ingrese un usuario valido");

        return null;
    }

    private void crearPrimerUsuario(String primerNombreUsuario, String primeraContraseña) {
        Administrador primerUsuario = new Administrador(primerNombreUsuario, primeraContraseña);

        usuarios.add(primerUsuario);
    }

    private boolean validarUsuario(Usuario nuevoUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.compararNombreYContraseña(nuevoUsuario.getNombre(), nuevoUsuario.getContraseña())) {

                System.out.println("Ese nombre y contrasenia ya pertenecen a otro usuario");
                System.out.println("Por favor ingrese otro nombre y contrasenia");
                return false;
            }
        }
        return true;
    }

    private void cambiarEstadoDeProyecto(Proyecto proyecto, String nuevoEstado) {
        if (proyecto == null) {
            System.out.println("El proyecto no existe. No se puede actualizar el estado.");
            return; // Salimos del método si el proyecto no existe
        }

        if (proyecto.comprobarSiEstaDisponibleParaActualizarProgreso()) {
            try {
                proyecto.setProgreso(nuevoEstado);
                System.out.println("El estado del proyecto se ha cambiado a: " + nuevoEstado);
            } catch (Exception e) {
                System.out.println("Error al cambiar el estado del proyecto: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("El estado del proyecto no se puede actualizar en este momento.");
        }
    }

    private boolean validarProyectoSolicitadoParaDeterminarAprobacion(String idRecibida, String nuevoEstado) {
        boolean existeElProyecto = false;
        int id = Integer.parseInt(idRecibida);

        for (Proyecto proyecto : proyectos) {
            if (proyecto.compararId(id) && proyecto.comprobarSiEstaEsperandoAprobacion()) {
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

    private boolean validarProyectoSolicitadoParaFinalizar(String idRecibida) {
        boolean existeElProyecto = false;
        int id = Integer.parseInt(idRecibida);

        for (Proyecto proyecto : proyectos) {
            if (proyecto.compararId(id) && proyecto.comprobarSiEstaDisponibleParaFinalizar()) {
                finalizarProyecto(proyecto);
                existeElProyecto = true;
            }
        }
        return existeElProyecto;
    }

    private void finalizarProyecto(Proyecto proyecto) {
        proyecto.setProgreso(FINALIZADO);
        LocalDate fechaFin = usuarioActual.ejecutarAccion("INGRESAR_FECHA_FIN");
        proyecto.setFechaDeFinalizacion(fechaFin);
        proyecto.setProyectoFinalizado(true);
    }

    private boolean validarProyectoSolicitadoParaAdministrarDesarrolladores(String idRecibida) {
        boolean existeElProyecto = false;
        int id = Integer.parseInt(idRecibida);

        for (Proyecto proyecto : proyectos) {
            if (proyecto.compararId(id) && !proyecto.comprobarSiEstaEsperandoAprobacion() && !proyecto.comprobarSiFueRechazado()) {
                existeElProyecto = true;
            }
        }
        return existeElProyecto;
    }

    private boolean validarAdministradorSolicitado(ArrayList<Desarrollador> desarrolladores, String idRecibida) {
        boolean existeDesarrollador = false;
        int id = Integer.parseInt(idRecibida);

        for (Desarrollador desarrollador : desarrolladores) {
            if (desarrollador.compararId(id)) {
                existeDesarrollador = true;
            }
        }
        return existeDesarrollador;
    }

    private void asignarDesarrollador(String idProyectoEIdDesarrollador[]) {

        Desarrollador desarrollador = obtenerDesarrollador(idProyectoEIdDesarrollador[1]);
        Proyecto proyecto = obtenerProyecto(idProyectoEIdDesarrollador[0]);

        //Solo se cambiara el progreso a "en desarrollo" la primera vez que se le asigne un desarrollador, para no sobreescribir el estado cada vez que se añada un desarrollador
        if (!proyecto.hayDesarrolladores()) {

            //Y solo se guardara la fecha de inicio la primera vez que se guarde un desarrollador
            if (proyecto.getFechaDeInicio() == null) {
                LocalDate fechaInicio = usuarioActual.ejecutarAccion("PEDIR_FECHA");

                proyecto.setFechaDeInicio(fechaInicio);
            }

            proyecto.setProgreso(EN_DESARROLLO);
        }

        proyecto.asignarDesarrollador(desarrollador);
        desarrollador.setDisponible(false);

        System.out.println("Desarrollador asignado");
    }

    private void desasignarDesarrollador(String idProyectoEIdDesarrollador[]) {
        // Obtener el desarrollador y el proyecto
        Desarrollador desarrollador = obtenerDesarrollador(idProyectoEIdDesarrollador[1]);
        Proyecto proyecto = obtenerProyecto(idProyectoEIdDesarrollador[0]);

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

    private Desarrollador obtenerDesarrollador(String idRecibida) {
        Desarrollador desarrolladorEncontrado = null;
        int id = Integer.parseInt(idRecibida);

        for (Desarrollador desarrollador : desarrolladores) {
            if (desarrollador.compararId(id)) {
                desarrolladorEncontrado = desarrollador;
            }
        }
        return desarrolladorEncontrado;
    }

    private Proyecto obtenerProyecto(String idRecibida) {
        int id = Integer.parseInt(idRecibida);

        for (Proyecto proyecto : proyectos) {
            if (proyecto.compararId(id)) {
                return proyecto;
            }
        }

        //Cuando el proyecto no existe
        return null;
    }

    private <T extends Usuario> int obtenerUltimoIdUsuario(Class<T> tipoUsuario) {
        int maxId = 0;
        for (Usuario usuario : usuarios) {
            if (tipoUsuario.isInstance(usuario)) {
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

    private void borrarUsuario(String tipo, String idRecibida) {
        int id = Integer.parseInt(idRecibida);
        boolean usuarioEncontrado = false;

        Iterator<Usuario> iter = usuarios.iterator();
        while (iter.hasNext()) {
            Usuario usuario = iter.next();
            if (usuario.compararId(id) && usuario.getClass().getSimpleName().toUpperCase().equals(tipo)) {

                // Verificar si el usuario es el último administrador
                if (esUltimoAdministrador(usuario)) {
                    System.out.println("No se puede eliminar al ultimo administrador.");
                } else {
                    iter.remove(); // Uso seguro del método remove del iterador
                    System.out.println("El usuario " + usuario.getClass().getSimpleName() + " " + usuario.getNombre() + " fue borrado exitosamente.");
                }
                usuarioEncontrado = true;
            }
        }

        if (!usuarioEncontrado) {
            System.out.println("No se encontro ningun usuario con los datos especificados.");
        }
    }

    private void borrarDesarrollador(String idRecibida) {
        int id = Integer.parseInt(idRecibida);
        boolean desarrolladorEncontrado = false;

        Iterator<Desarrollador> iter = desarrolladores.iterator();
        while (iter.hasNext()) {
            Desarrollador desarrollador = iter.next();
            if (desarrollador.compararId(id)) {
                iter.remove(); // Uso seguro del método remove del iterador
                System.out.println("El desarrollador " + desarrollador.getNombre() + " fue borrado exitosamente.");
                desarrolladorEncontrado = true;
            }
        }

        if (!desarrolladorEncontrado) {
            System.out.println("No se encontro ningun desarrollador con los datos especificados.");
        }
    }

    private ArrayList<Proyecto> obtenerProyectosDelUsuario(Usuario cliente) {
        ArrayList<Proyecto> proyectosDelUsuario = new ArrayList<>();
        for (Proyecto proyecto : proyectos) {
            if (proyecto.compararClientes(cliente)) {
                proyectosDelUsuario.add(proyecto);
            }
        }

        return proyectosDelUsuario;
    }

    private <T extends Usuario> ArrayList<T> obtenerUsuariosPorTipo(Class<T> tipoUsuario) {
        ArrayList<T> usuariosFiltrados = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (tipoUsuario.isInstance(usuario)) {
                usuariosFiltrados.add(tipoUsuario.cast(usuario));
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

    private boolean contieneAdministrador() {
        for (Usuario usuario : usuarios) {
            if (usuario.getClass().equals(Administrador.class)) {
                return true;
            }
        }
        return false;
    }

}
