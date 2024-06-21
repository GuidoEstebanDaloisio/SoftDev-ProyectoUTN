package softdev;

public class FuncionCrearUsuario implements AccionUsuarioStrategy, MenuAdministrador{
    @Override
    public Usuario ejecutarAccion(Object... params) {
        Usuario nuevoUsuario = null;

        String tipoUsuarioNombreYContraseña[] = ingresarDatosParaNuevoUsuario();

        switch (tipoUsuarioNombreYContraseña[0]) {
            case "CLIENTE" -> {
                String direccionMailTelefono[] = ingresarDatosDelCliente();
                nuevoUsuario = new Cliente(direccionMailTelefono[0], direccionMailTelefono[1], Integer.parseInt(direccionMailTelefono[2]), tipoUsuarioNombreYContraseña[1], tipoUsuarioNombreYContraseña[2]);
            }
            case "GERENTE" ->
                nuevoUsuario = new Gerente(tipoUsuarioNombreYContraseña[1], tipoUsuarioNombreYContraseña[2]);
            case "ADMINISTRADOR" ->
                nuevoUsuario = new Administrador(tipoUsuarioNombreYContraseña[1], tipoUsuarioNombreYContraseña[2]);
        }
        return nuevoUsuario;
    }
}
