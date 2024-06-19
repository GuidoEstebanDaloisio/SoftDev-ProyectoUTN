package softdev;

public class Administrador extends Usuario implements MenuAdministrador {
    
    
    public Administrador(String nombre, String contrasenia) {
        super(nombre, contrasenia, 12);
        setAccionDeUsuario(new AdministradorStrategy());
    }

    
    
    @Override
    public String elegirAccion() {
        System.out.println("==================================================================================");
        System.out.println("(1)Crear usuario                        | (7)Ver clientes");
        System.out.println("(2)Eliminar usuario                     | (8)Ver gerentes");
        System.out.println("(3)Registrar desarrollador              | (9)Ver administradores");
        System.out.println("(4)Eliminar desarrolador                | (10)Ver desarrolladores diponibles");
        System.out.println("(5)Asignar desarrolador a un proyecto   | (11)Ver desarrolladores asignados");
        System.out.println("(6)Quitar desarrolador de un proyecto   | (12)Salir");

        return ejecutarAccion(leerOpcionMenu(cantidadDeOpciones));
    }

    @Override
    public String ejecutarAccion(int accionNum) {
        String opcion = null;
        switch (accionNum) {
            case 1 -> {
                opcion = "NUEVO_USUARIO";
            }
            case 2 -> {
                opcion = "BORRAR_USUARIO";
            }
            case 3 -> {
                opcion = "NUEVO_DESARROLLADOR";
            }
            case 4 -> {
                opcion = "BORRAR_DESARROLLADOR";
            }
            case 5 -> {
                opcion = "ASIGNAR_DESARROLLADOR";
            }
            case 6 -> {
                opcion = "DESASIGNAR_DESARROLLADOR";
            }
            case 7 -> {
                opcion = "VER_CLIENTES";
            }
            case 8 -> {
                opcion = "VER_GERENTES";
            }
            case 9 -> {
                opcion = "VER_ADMINISTRADORES";
            }
            case 10 -> {
                opcion = "VER_DESARROLLADORES_DISPONIBLES";
            }
            case 11 -> {
                opcion = "VER_DESARROLLADORES_ASIGNADOS";
            }
            case 12 -> {
                opcion = "SALIR";
            }
        }
        return opcion;
    }
}
