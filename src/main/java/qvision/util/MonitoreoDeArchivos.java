package qvision.util;

import java.io.File;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class MonitoreoDeArchivos {

    private MonitoreoDeArchivos() {
        throw new IllegalStateException("Utility class");
    }

    public static void esperarPorLasActualizaciones(String rutaDelDirectorio) {

        File[] archivos = obtenerArchivosAspectos(rutaDelDirectorio);
        if (archivos == null) return;

        Set<File> conjuntoDeArchivos = new HashSet<>(Arrays.asList(archivos));
        long[] tamanioDeArchivos = iniciarTamanioDeArchivos(conjuntoDeArchivos);

        AtomicBoolean todosLosArchivosEscritos = new AtomicBoolean(false);
        AtomicBoolean actualizado = new AtomicBoolean(false);

        // Sale del cíclo si todos los archivos están escritos y no actualizados
        do {
            verificarArchivos(conjuntoDeArchivos, tamanioDeArchivos, todosLosArchivosEscritos, actualizado);
        } while (!todosLosArchivosEscritos.get() || actualizado.get());

    }

    private static File[] obtenerArchivosAspectos(String rutaDelDirectorio) {
        File directorio = new File(rutaDelDirectorio);
        return directorio.listFiles((dir, name) -> name.endsWith(".feature"));
    }

    private static long[] iniciarTamanioDeArchivos(Set<File> conjuntoDeArchivos) {
        long[] tamanioDeArchivos = new long[conjuntoDeArchivos.size()];
        int i = 0;
        for (File archivo : conjuntoDeArchivos) {
            tamanioDeArchivos[i++] = archivo.length();
        }
        return tamanioDeArchivos;
    }

    private static void verificarArchivos(Set<File> conjuntoDeArchivos, long[] tamanioDeArchivos, AtomicBoolean todosLosArchivosEscritos, AtomicBoolean actualizado) {
        if (todosLosArchivosExistentes(conjuntoDeArchivos)) {
            comprobarActualizaciones(conjuntoDeArchivos, tamanioDeArchivos, actualizado);
            if (!actualizado.get()) {
                todosLosArchivosEscritos.set(true);
            }
        }
    }

    private static boolean todosLosArchivosExistentes(Set<File> conjuntoDeArchivos) {
        for (File archivo : conjuntoDeArchivos) {
            if (!archivo.exists() || archivo.length() == 0) {
                return false;
            }
        }
        return true;
    }

    private static void comprobarActualizaciones(Set<File> conjuntoDeArchivos, long[] tamanioDeArchivos, AtomicBoolean actualizado) {
        int i = 0;
        for (File archivo : conjuntoDeArchivos) {
            if (archivo.length() != tamanioDeArchivos[i++]) {
                actualizado.set(true);
                break;
            }
        }
    }
}
