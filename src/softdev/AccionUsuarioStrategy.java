package softdev;

import java.io.Serializable;

public interface AccionUsuarioStrategy <T> extends Serializable {
    T ejecutarAccion(Object... params);
}