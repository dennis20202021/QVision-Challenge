package sura.util.report;

import net.serenitybdd.core.Serenity;
import sura.exception.ExcepcionDeLectura;
import sura.util.RegistrarInformacion;

import java.io.File;
import java.io.IOException;

public class UtilidadesReporte {

    private UtilidadesReporte() {
        throw new IllegalStateException("Utility class");
    }

    public static void adjuntarArchivoAlReporte(String rutaDeArchivo, String titulo) {
        File file = new File(rutaDeArchivo);
        if (file.exists()) {
            try {
                Serenity.recordReportData().withTitle(titulo).downloadable().fromFile(file.toPath());
            } catch (IOException error) {
                throw new ExcepcionDeLectura(
                        String.format("¡No se encontró el archivo en la ruta especificada: %s!", rutaDeArchivo),
                        UtilidadesReporte.class.getName(),
                        error
                );
            }
        } else {
            RegistrarInformacion.deConsola("¡No se encontró el archivo en la ruta especificada: {}!", rutaDeArchivo);
        }
    }
}
