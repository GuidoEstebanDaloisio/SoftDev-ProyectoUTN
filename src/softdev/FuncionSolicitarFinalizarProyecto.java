package softdev;

public class FuncionSolicitarFinalizarProyecto implements AccionUsuarioStrategy, MenuGerente {

    @Override
    public String[] ejecutarAccion(Object... params) {
        String idYTitulo[] = ingresarDatosDeProyectoAFinalizar();

        return idYTitulo;
    }
}
