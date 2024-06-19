package softdev;

import java.util.Arrays;
import java.util.List;

public class Constantes {

    //TIPOS DE USUARIOS
    public static final List<String> TIPOS_USUARIO_VALIDOS = Arrays.asList("CLIENTE", "GERENTE", "Administrador");

    //ESTADOS DEL PROYECTO
    static String ESPERANDO_APROVACION = "Esperando aprobacion del proyecto";
    static String ESPERANDO_DESARROLLADOR = "Aguardando que se asigne un desarrollador";
    static String EN_DESARROLLO = "Trabajando en su desarrollo";
    static String FINALIZADO = "Finalizado";

}
