package softdev;

import java.util.Arrays;
import java.util.List;

public class Constantes {

    //TIPOS DE USUARIOS
    public static final List<String> TIPOS_USUARIO_VALIDOS = Arrays.asList("CLIENTE", "GERENTE", "ADMINISTRADOR");

    //ESTADOS DEL PROYECTO
    static String ESPERANDO_APROVACION = "Esperando aprobacion del proyecto";
    static String ESPERANDO_DESARROLLADOR = "Aguardando que se asigne un desarrollador";
    static String EN_DESARROLLO = "Trabajando en su desarrollo";
    static String FINALIZADO = "Finalizado";

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

}
