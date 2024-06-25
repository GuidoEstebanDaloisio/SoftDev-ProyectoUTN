package softdev;

public class FuncionSolicitarDesasignarDesarrollador implements AccionUsuarioStrategy, MenuAdministrador {

    @Override
    public String[] ejecutarAccion(Object... params) {
        String idYTituloProyecto[] = ingresarDatosDeProyecto();
        String idYNombreDesarrollador[] = ingresarDatosDeDesarrolladorParaDesasignar();

        String idYTituloProyectoEIdYNombreDesarrollador[] = {idYTituloProyecto[0], idYTituloProyecto[1], idYNombreDesarrollador[0], idYNombreDesarrollador[1]};

        return idYTituloProyectoEIdYNombreDesarrollador;
    }

}
