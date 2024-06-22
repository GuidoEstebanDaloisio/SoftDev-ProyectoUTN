package softdev;

public class FuncionSolicitarAsignarDesarrollador implements AccionUsuarioStrategy, MenuAdministrador{

    @Override
    public String[] ejecutarAccion(Object... params) {
        String idYTituloProyecto[] = ingresarDatosDeProyecto();
       String idYNombreDesarrollador[] = ingresarDatosDeDesarrolladorParaAsignar();
       
       String idYTituloProyectoEIdYNombreDesarrollador [] ={idYTituloProyecto[0], idYTituloProyecto[1], idYNombreDesarrollador[0], idYNombreDesarrollador[1]};
       
       return idYTituloProyectoEIdYNombreDesarrollador;
    }


    
}
