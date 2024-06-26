package softdev;

import java.util.Arrays;
import java.util.List;

public class Constantes {

    //TIPOS DE USUARIOS
    public static final List<String> TIPOS_USUARIO_VALIDOS = Arrays.asList("CLIENTE", "GERENTE", "ADMINISTRADOR");

    //MEDIOS PARA LA SOLICITUD DE PROYECTOS
    public static final List<String> MEDIOS_DE_SOLICITUD = Arrays.asList("PRESENCIAL", "TELEFONO", "CORREO");

    //ESTADOS DEL PROYECTO
    static String ESPERANDO_APROBACION = "Esperando aprobacion del proyecto";
    static String ESPERANDO_DESARROLLADOR = "Aguardando que se asigne un desarrollador";
    static String EN_DESARROLLO = "Trabajando en su desarrollo";
    static String PRUEBAS = "En fase de pruebas";
    static String IMPLEMENTACION = "En implementacion o despliegue";
    static String MANTENIMIENTO = "En mantenimiento";
    static String CIERRE = "En fase de cierre del proyecto";
    static String FINALIZADO = "Finalizado";
    static String RECHAZADO = "El proyecto fue rechazado";
    public static final List<String> ESTADOS_PROYECTO = Arrays.asList(EN_DESARROLLO, PRUEBAS, IMPLEMENTACION, MANTENIMIENTO, CIERRE);

    //ERRORES
    private static String CONTORNO_USUARIO_INVALIDO = "          -------------------------------\n";
    private static String TEXTO_USUARIO_INVALIDO = "          ERROR: TIPO DE USUARIO INVALIDO\n";
    static String ERROR_USUARIO_INVALIDO = CONTORNO_USUARIO_INVALIDO + TEXTO_USUARIO_INVALIDO + CONTORNO_USUARIO_INVALIDO;

    private static String CONTORNO_TELEFONO_INVALIDO = "--------------------------------------------------\n";
    private static String TEXTO_TELEFONO_INVALIDO = "ERROR: DEBE INGRESAR UN NUMERO DE TELEFONO VALIDO\n";
    static String ERROR_TELEFONO_INVALIDO = CONTORNO_TELEFONO_INVALIDO + TEXTO_TELEFONO_INVALIDO + CONTORNO_TELEFONO_INVALIDO;

    private static String CONTORNO_OPCION_INVALIDA = "     -------------------------------------\n";
    private static String TEXTO_OPCION_INVALIDA = "     Opcion no valida, ingrese otro numero\n";
    static String ERROR_OPCION_INVALIDA = CONTORNO_OPCION_INVALIDA + TEXTO_OPCION_INVALIDA + CONTORNO_OPCION_INVALIDA;

    private static String CONTORNO_OPCION_INVALIDA_CARACTER = "     -------------------------------------------\n";
    private static String TEXTO_OPCION_INVALIDA_CARACTER = "     Entrada no valida, debe ingresar un numero\n";
    static String ERROR_OPCION_INVALIDA_CARACTER = CONTORNO_OPCION_INVALIDA_CARACTER + TEXTO_OPCION_INVALIDA_CARACTER + CONTORNO_OPCION_INVALIDA_CARACTER;

    private static String CONTORNO_PRESUPUESTO_INVALIDO = "------------------------------------------\n";
    private static String TEXTO_PRESUPUESTO_INVALIDO = "ERROR: DEBE INGRESAR UN PRESUPUESTO VALIDO\n";
    static String ERROR_PRESUPUESTO_INVALIDO = CONTORNO_PRESUPUESTO_INVALIDO + TEXTO_PRESUPUESTO_INVALIDO + CONTORNO_PRESUPUESTO_INVALIDO;

    private static String CONTORNO_ID_INVALIDO = "---------------------------------------------------\n";
    private static String TEXTO_ID_INVALIDO = "ERROR: DEBE INGRESAR UN ID QUE SEA UN NUMERO ENTERO\n";
    static String ERROR_ID_INVALIDO = CONTORNO_ID_INVALIDO + TEXTO_ID_INVALIDO + CONTORNO_ID_INVALIDO;

    private static String CONTORNO_ESTADO_PROYECTO_INVALIDO = "------------------------------------------------------------------\n";
    private static String TEXTO_ESTADO_PROYECTO_INVALIDO = "ERROR: DEBE INGRESAR UNA OPCION QUE CORRESPONDA A UN ESTADO VALIDO\n";
    static String ERROR_ESTADO_PROYECTO_INVALIDO = CONTORNO_ESTADO_PROYECTO_INVALIDO + TEXTO_ESTADO_PROYECTO_INVALIDO + CONTORNO_ESTADO_PROYECTO_INVALIDO;

}
