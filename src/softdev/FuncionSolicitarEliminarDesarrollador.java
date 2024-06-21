package softdev;

public class FuncionSolicitarEliminarDesarrollador implements AccionUsuarioStrategy, MenuAdministrador{

    @Override
    public String[] ejecutarAccion(Object... params) {
        return ingresarDatosParaBorrarDesarrollador();
    }

}
