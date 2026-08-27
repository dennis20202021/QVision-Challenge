package qvision.util.interfaces.propiedades;

import java.util.Properties;

public interface GestionArchivoPropiedades {
    void cargarPropiedades(String rutaDeArchivo, Properties propiedades);
    void guardarPropiedades();
}
