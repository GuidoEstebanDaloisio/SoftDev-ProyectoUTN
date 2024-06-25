package softdev;

import java.io.Serializable;

public interface AccionUsuarioStrategy <T> extends Serializable {
    public T ejecutarAccion(Object... params);
}