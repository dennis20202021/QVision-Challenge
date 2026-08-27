package qvision.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrarInformacion {

    // Instancia de SLF4J Logger para el registro de mensajes
    private static final Logger logger = LoggerFactory.getLogger(RegistrarInformacion.class);

    private RegistrarInformacion() {
        throw new IllegalStateException("Utility class");
    }

    public static void deConsola(String mensaje) {
        logger.info(mensaje);
    }

    public static void deConsola(String mensaje, Object... params) {
        logger.info(mensaje, params);
    }

}
