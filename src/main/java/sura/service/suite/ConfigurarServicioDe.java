package sura.service.suite;

import sura.service.TrazaDeDatos;
import sura.util.Cargar;
import sura.util.LectorFeature;
import sura.util.MonitoreoDeArchivos;

import java.util.Map;

public class ConfigurarServicioDe {

    private ConfigurarServicioDe() {
        throw new IllegalStateException("Utility class");
    }

    public static void lecturaYEscrituraDeArchivos() {
        String recurso = Cargar.recursoComoCadena("features");
        new LectorFeature<>(
                TrazaDeDatos::filaTrazaAMapa,
                data -> {
                    StringBuilder fila = new StringBuilder();
                    for (Map.Entry<String, String> entrada : data.entrySet()) {
                        fila.append(entrada.getValue()).append(" | ");
                    }
                    return fila.toString().replaceAll("\\s\\|\\s$", ""); // Elimina el último "|"
                }
        ).leerArchivosDeCaracteristicas(recurso);
        MonitoreoDeArchivos.esperarPorLasActualizaciones(recurso);
    }
}
