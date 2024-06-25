package softdev;

public class FuncionSolicitarEliminarUsuario implements AccionUsuarioStrategy, MenuAdministrador {

    @Override
    public String[] ejecutarAccion(Object... params) {
        String tipoUsuarioIdYNombre[] = ingresarDatosParaBorrarUsuario();
        return tipoUsuarioIdYNombre;
    }

}
