package softdev;

public class FuncionNuevoEstadoDelProyecto implements AccionUsuarioStrategy, MenuGerente {

    @Override
    public String[] ejecutarAccion(Object... params) {
        String nuevoEstado = ingresarNuevoEstadoDeProyecto();
        String idYTitulo[] = ingresarDatosDeProyecto();

        String nuevoEstadoIdYTitulo[] = {nuevoEstado, idYTitulo[0], idYTitulo[1]};
        return nuevoEstadoIdYTitulo;
    }

}
