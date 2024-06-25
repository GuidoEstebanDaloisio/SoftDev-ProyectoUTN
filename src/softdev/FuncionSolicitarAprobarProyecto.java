package softdev;

public class FuncionSolicitarAprobarProyecto implements AccionUsuarioStrategy, MenuGerente {

    @Override
    public String[] ejecutarAccion(Object... params) {
        String IdYTitulo[] = ingresarDatosDeProyectoAAprobar();
        
        return IdYTitulo;
    }

}
