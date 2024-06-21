package softdev;

public class FuncionRegistrarDesarrollador implements AccionUsuarioStrategy<Desarrollador>, MenuAdministrador {

    @Override
    public Desarrollador ejecutarAccion(Object... params) {
        int ultimoIdDesarrollador = (int) params[0]; // Suponiendo que el primer parámetro es el último ID registrado
        
        String nombreYHabilidad[] = ingresarDatosParaNuevoDesarrollador();

        Desarrollador nuevoDesarrollador = new Desarrollador(ultimoIdDesarrollador + 1, nombreYHabilidad[0], nombreYHabilidad[1]);

        return nuevoDesarrollador;
    }
}