package qvision.util;

import java.util.Objects;

import static qvision.util.Constantes.CANTIDAD_DE_CONCURRENCIAS;

public class DefinirPropiedad {

    private DefinirPropiedad() {
        throw new IllegalStateException("Utility class");
    }

    public static String obtenerConcurrencia() {
        String envConcurrencia = System.getProperty("cucumber.execution.parallel.config.fixed.parallelism");
        if (Objects.isNull(envConcurrencia)) {
            return CANTIDAD_DE_CONCURRENCIAS;
        }
        return envConcurrencia;
    }
}
