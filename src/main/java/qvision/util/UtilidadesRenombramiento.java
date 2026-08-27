package qvision.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static qvision.util.Constantes.*;

public class UtilidadesRenombramiento {

    // Método para comprobar si el nombre del archivo coincide con el patrón esperado
    private boolean esValidoElNombreDeArchivo(String nombreDeArchivo) {
        return nombreDeArchivo.contains("_") && nombreDeArchivo.endsWith(EXTENSION_EXCEL_MODERNO);
    }

    // Método para extraer el nuevo nombre de archivo basado en la fecha actual
    private String obtenerNuevoNombreDeArchivo(String nombreDeArchivo, String fechaFormato) {
        String[] partes = nombreDeArchivo.split("_");
        return String.join("_", Arrays.copyOfRange(partes, 0, partes.length - 1)) + "_" + fechaFormato + EXTENSION_EXCEL_MODERNO;
    }

    // Método para renombrar un archivo
    private void renombrarArchivo(Path archivo, Path nuevaRutaArchivo) {
        try {
            Files.move(archivo, nuevaRutaArchivo, StandardCopyOption.REPLACE_EXISTING);
            RegistrarInformacion.deConsola("Archivo renombrado: {}", nuevaRutaArchivo.getFileName());
        } catch (IOException e) {
            RegistrarInformacion.deConsola("Error al renombrar archivo: {}", e.getMessage());
        }
    }

    public List<Path> deArchivosEn(String rutaDirectorio) {

        List<Path> resultadoDeArchivosRenombrados = new ArrayList<>();
        String recurso = Cargar.recursoComoCadena(rutaDirectorio);

        // Definir la ruta de archivos
        Path rutaArchivos = Path.of(recurso);

        // Encontrar archivos en el directorio y renombrarlos
        try (Stream<Path> archivos = Files.walk(rutaArchivos)) {

            archivos.forEach(archivo -> {

                // Procesar cada archivo Excel
                String nombreArchivo = archivo.getFileName().toString();

                if (esValidoElNombreDeArchivo(nombreArchivo)) {
                    String[] partes = nombreArchivo.split("_");
                    if (partes.length > 1 && partes[partes.length - 1].endsWith(EXTENSION_EXCEL_MODERNO)) {
                        String fragmentoFecha = partes[partes.length - 1].replace(EXTENSION_EXCEL_MODERNO, "");
                        if (fragmentoFecha.length() == 6) { // ddMMyy
                            String nuevoNombreDeArchivo = obtenerNuevoNombreDeArchivo(nombreArchivo, UtilidadesFecha.obtenerFechaActualFormateada());
                            Path nuevaRutaDeArchivo = rutaArchivos.resolve(nuevoNombreDeArchivo);
                            renombrarArchivo(archivo, nuevaRutaDeArchivo);
                            resultadoDeArchivosRenombrados.add(nuevaRutaDeArchivo);
                        }
                    }
                }

            });
        } catch (IOException e) {
            RegistrarInformacion.deConsola("Error enlistando los archivos: {}", e.getMessage());
        }

        return resultadoDeArchivosRenombrados;

    }

}
