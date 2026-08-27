package qvision.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UtilidadesFecha {

    private UtilidadesFecha() {
        throw new IllegalStateException("Utility class");
    }

    public static String obtenerFechaActualFormateada() {
        // Definir el patrón para la fecha en el nombre del archivo (ddMMyy)
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("ddMMyy");
        return LocalDate.now().format(formato);
    }

}
