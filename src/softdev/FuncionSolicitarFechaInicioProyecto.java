package softdev;

import java.time.LocalDate;

public class FuncionSolicitarFechaInicioProyecto implements AccionUsuarioStrategy, MenuAdministrador {
    @Override
    public LocalDate ejecutarAccion(Object... params) {
        LocalDate fechaDeInicio = ingresarFechaDeInicioDeProyecto();
        return fechaDeInicio;
    }
}
