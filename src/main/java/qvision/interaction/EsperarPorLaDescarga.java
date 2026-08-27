package qvision.interaction;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.*;
import org.awaitility.Awaitility;
import qvision.util.RegistrarInformacion;

import java.io.File;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static qvision.util.Constantes.EXTENSION_TEMP_CRDOWNLOAD;
import static qvision.util.Constantes.EXTENSION_TEMP_PART;

public class EsperarPorLaDescarga implements Interaction {

    private final String directorioDeDescarga;
    private final long tiempoDeEsperaEnSegundos;

    public EsperarPorLaDescarga(String directorioDeDescarga, long tiempoDeEsperaEnSegundos) {
        this.directorioDeDescarga = directorioDeDescarga;
        this.tiempoDeEsperaEnSegundos = tiempoDeEsperaEnSegundos;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        Path rutaDeDescarga = Paths.get(this.directorioDeDescarga);

        // Obtener lista inicial de archivos
        Set<String> archivosIniciales = listaDeArchivosEnDirectorio(rutaDeDescarga);

        // Esperar por la aparición del nuevo archivo
        AtomicReference<String> nombreDelArchivoDescargado = new AtomicReference<>();

        Awaitility.await().atMost(this.tiempoDeEsperaEnSegundos, TimeUnit.SECONDS).pollInterval(500, TimeUnit.MILLISECONDS).until(() -> {
            Set<String> archivosActuales = listaDeArchivosEnDirectorio(rutaDeDescarga);
            archivosActuales.removeAll(archivosIniciales);

            // Filtrar archivos de descarga temporales
            Optional<String> nuevoArchivo = archivosActuales.stream().filter(file -> !file.endsWith(EXTENSION_TEMP_CRDOWNLOAD) && !file.endsWith(EXTENSION_TEMP_PART)).findFirst();

            if (nuevoArchivo.isPresent()) {
                nombreDelArchivoDescargado.set(nuevoArchivo.get());
                return true;
            }

            // También verificar si tenemos archivos temporales que podrían indicar descarga en progreso
            Optional<String> archivoTemp = archivosActuales.stream().filter(file -> file.endsWith(EXTENSION_TEMP_CRDOWNLOAD) || file.endsWith(EXTENSION_TEMP_PART)).findFirst();

            return archivoTemp.isPresent();
        });

        // Si encontramos un archivo temporal, espere a que desaparezca y sea reemplazado
        String nombreArchivoFinal = nombreDelArchivoDescargado.get();
        if (nombreArchivoFinal == null) {
            // Habremos detectado un archivo temporal, aguarda el archivo original
            Awaitility.await().atMost(this.tiempoDeEsperaEnSegundos, TimeUnit.SECONDS).until(() -> {
                Set<String> archivosActuales = listaDeArchivosEnDirectorio(rutaDeDescarga);
                archivosActuales.removeAll(archivosIniciales);

                Optional<String> newFile = archivosActuales.stream().filter(file -> !file.endsWith(EXTENSION_TEMP_CRDOWNLOAD) && !file.endsWith(EXTENSION_TEMP_PART)).findFirst();

                if (newFile.isPresent()) {
                    nombreDelArchivoDescargado.set(newFile.get());
                    return true;
                }
                return false;
            });
            nombreArchivoFinal = nombreDelArchivoDescargado.get();
        }

        // Ahora esperar por el tamaño del archivo estabilizado
        Path rutaArchivo = rutaDeDescarga.resolve(nombreArchivoFinal);
        AtomicReference<Long> tamanioPrevio = new AtomicReference<>(-1L);

        Awaitility.await().atMost(this.tiempoDeEsperaEnSegundos, TimeUnit.SECONDS).pollInterval(500, TimeUnit.MILLISECONDS).until(() -> {
            if (!Files.exists(rutaArchivo)) return false;

            long tamanioActual = Files.size(rutaArchivo);
            boolean esEstable = tamanioActual > 0 && tamanioActual == tamanioPrevio.get();
            tamanioPrevio.set(tamanioActual);
            return esEstable;
        });

        RegistrarInformacion.deConsola("✅ El archivo se ha descargado correctamente. Por favor, confirme su disponibilidad en la siguiente ubicación: {}", this.directorioDeDescarga);
    }

    private Set<String> listaDeArchivosEnDirectorio(Path directorio) {
        File[] archivos = directorio.toFile().listFiles();
        if (archivos == null) return new HashSet<>();

        Set<String> nombreDeArchivos = new HashSet<>();
        for (File archivo : archivos) {
            if (archivo.isFile()) {
                nombreDeArchivos.add(archivo.getName());
            }
        }
        return nombreDeArchivos;
    }

    public static Interaction completaDelArchivoEnDirectorio(String directorioDeDescarga, long tiempoDeEsperaEnSegundos) {
        return Instrumented.instanceOf(EsperarPorLaDescarga.class).withProperties(directorioDeDescarga, tiempoDeEsperaEnSegundos);
    }
}
