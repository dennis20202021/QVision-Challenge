package qvision.util.config.wait;

import qvision.exception.ExcepcionDeLectura;
import qvision.exception.ExcepcionInterrumpida;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class EsperarArchivo {

    private final String rutaDeArchivo;
    private final Duration timeout;
    private long tamanioPrevioDelArchivo = -1;

    public EsperarArchivo(String rutaDeArchivo, Duration timeout) {
        this.rutaDeArchivo = rutaDeArchivo;
        this.timeout = timeout;
    }

    private WatchService configurarServicioDeVigilancia(Path path) {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY);
            return watchService;
        } catch (IOException error) {
            throw new ExcepcionDeLectura(
                    String.format("Archivo no encontrado en la ruta especificada: %s", path),
                    EsperarArchivo.class.getName(),
                    error
            );
        }
    }

    private WatchKey obtenerClaveDeVigilancia(WatchService watchService) {
        try {
            return watchService.poll(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException error) {
            Thread.currentThread().interrupt();
            throw new ExcepcionInterrumpida(
                    "Error al intentar sondear 100ms de espera por el archivo",
                    EsperarArchivo.class.getName(),
                    error
            );
        }
    }

    private void procesarEventos(WatchKey key, Path path) {
        for (WatchEvent<?> event : key.pollEvents()) {
            if ((event.kind() == StandardWatchEventKinds.ENTRY_CREATE || event.kind() == StandardWatchEventKinds.ENTRY_MODIFY)
                    && event.context().toString().equals(path.getFileName().toString())) {
                verificarArchivoDescargado(path);
            }
        }
        key.reset();
    }

    private void verificarArchivoDescargado(Path path) {
        File file = new File(path.toString());
        if (file.exists() && file.length() > 0) {
            long currentFileSize = file.length();
            if (this.tamanioPrevioDelArchivo != -1 && currentFileSize == this.tamanioPrevioDelArchivo) {
                // El archivo no ha cambiado de tamaño, por lo que probablemente esté descargado completamente
                return;
            }
            this.tamanioPrevioDelArchivo = currentFileSize;
        }
    }

    public void deDescarga() {
        Path path = Paths.get(this.rutaDeArchivo);
        WatchService watchService = configurarServicioDeVigilancia(path);

        Instant tiempoDeInicio = Instant.now();

        while (Instant.now().isBefore(tiempoDeInicio.plus(this.timeout))) {
            WatchKey key = obtenerClaveDeVigilancia(watchService);
            if (key != null) {
                procesarEventos(key, path);
            }
        }
    }
}
