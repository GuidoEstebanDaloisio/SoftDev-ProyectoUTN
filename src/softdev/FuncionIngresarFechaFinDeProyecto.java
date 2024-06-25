package softdev;

import java.time.LocalDate;

public class FuncionIngresarFechaFinDeProyecto implements AccionUsuarioStrategy, MenuGerente{

    @Override
    public LocalDate ejecutarAccion(Object... params) {
        return ingresarFechaFin();
    }

    
}
