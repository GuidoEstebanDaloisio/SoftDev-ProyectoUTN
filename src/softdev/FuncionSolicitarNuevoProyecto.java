package softdev;

public class FuncionSolicitarNuevoProyecto implements AccionUsuarioStrategy, MenuCliente {

    @Override
    public Proyecto ejecutarAccion(Object... params) {
        String tituloDescripcionMedioYPresupuesto[] = ingresarDatosParaNuevoProyecto();

        double presupuesto = Double.parseDouble(tituloDescripcionMedioYPresupuesto[3]);

        Proyecto nuevoProyecto = new Proyecto(tituloDescripcionMedioYPresupuesto[0], tituloDescripcionMedioYPresupuesto[1], tituloDescripcionMedioYPresupuesto[2], presupuesto);
        return nuevoProyecto;
    }    
}
