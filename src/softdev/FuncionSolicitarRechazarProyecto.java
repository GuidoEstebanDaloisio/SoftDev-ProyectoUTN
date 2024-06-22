package softdev;

public class FuncionSolicitarRechazarProyecto implements AccionUsuarioStrategy, MenuGerente {

    @Override
    public String[] ejecutarAccion(Object... params) {
        String IdYTitulo[] = ingresarDatosDeProyectoARechazar();
        
        return IdYTitulo;
    }
    
}
