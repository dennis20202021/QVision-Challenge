package qvision.util;

import qvision.exception.ExcepcionDeLectura;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class Cargar {

    private Cargar() {
        throw new IllegalStateException("Utility class");
    }

    private static InputStream flujoDelRecurso;

    private static InputStream obtenerFlujoDelRecurso() {
        return Cargar.flujoDelRecurso;
    }

    private static void establecerFlujoDelRecurso(InputStream nuevoFlujoDelRecurso) {
        Cargar.flujoDelRecurso = nuevoFlujoDelRecurso;
    }

    public static InputStream recurso(String ruta) {

        try {

            RegistrarInformacion.deConsola("Leyendo recurso desde la ubicación: {}...", ruta);
            establecerFlujoDelRecurso(Cargar.class.getClassLoader().getResourceAsStream(ruta));

            if (Objects.nonNull(Cargar.flujoDelRecurso)) {
                return obtenerFlujoDelRecurso();
            } else {
                establecerFlujoDelRecurso(Files.newInputStream(Path.of(ruta)));
            }

        } catch (IOException error) {
            throw new ExcepcionDeLectura("Archivo no encontrado, por favor verifique su ruta", Cargar.class.getName(), error);
        }

        return obtenerFlujoDelRecurso();

    }

    public static String recursoComoCadena(String ruta) {

        try {
            RegistrarInformacion.deConsola("Leyendo recurso desde la ubicación: {}...", ruta);
            InputStream flujo = Cargar.class.getClassLoader().getResourceAsStream(ruta);

            if (Objects.nonNull(flujo)) {
                return Path.of(
                        Objects.requireNonNull(
                                Cargar.class.getClassLoader().getResource(ruta)
                        ).toURI()
                ).toString();
            } else {
                return Path.of(ruta).toString();
            }

        } catch (Exception error) {
            throw new ExcepcionDeLectura("Archivo no encontrado, por favor verifique su ruta", Cargar.class.getName(), error);
        }
    }
}


