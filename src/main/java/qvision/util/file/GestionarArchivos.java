package qvision.util.file;

import qvision.util.RegistrarInformacion;
import java.io.File;
import static qvision.util.Constantes.RUTA_ABSOLUTA_DESCARGAS;

public class GestionarArchivos {

    private GestionarArchivos() {
        throw new IllegalStateException("Utility class");
    }

    public static String obtenerRutaDirectorioDeDescargas() {
        File directorio = new File(RUTA_ABSOLUTA_DESCARGAS);

        // Verificar si el directorio existe
        if (!directorio.exists() && directorio.mkdirs()) {
            RegistrarInformacion.deConsola("Directorio creado exitosamente.");
        }
        return directorio.getAbsolutePath();
    }

    public static String obtenerRutaSubDirectorio(String nombreSubDirectorio) {
        File directorio = new File(obtenerRutaDirectorioDeDescargas(), nombreSubDirectorio);

        // Verificar si el directorio existe
        if (!directorio.exists() && directorio.mkdirs()) {
            RegistrarInformacion.deConsola("Directorio creado exitosamente.");
        }
        return directorio.getAbsolutePath();
    }
}
