package sura.util;

import sura.exception.ExcepcionDeLectura;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

public class LectorFeature<T> {

    private final Function<Row, T> trazaDeFila;
    private final Function<T, String> procesarDato;

    public LectorFeature(Function<Row, T> trazaDeFila, Function<T, String> procesarDato) {
        this.trazaDeFila = trazaDeFila;
        this.procesarDato = procesarDato;
    }

    private Function<Row, T> getTrazaDeFila() {
        return this.trazaDeFila;
    }

    private Function<T, String> getProcesarDato() {
        return this.procesarDato;
    }

    public void leerArchivosDeCaracteristicas(String rutaDirectorio) {
        try (Stream<Path> rutas = Files.walk(Paths.get(rutaDirectorio))) {
            rutas.filter(ruta -> ruta.getFileName().toString().endsWith(".feature"))
                    .forEach(this::procesarArchivoFeature);
        } catch (IOException error) {
            throw new ExcepcionDeLectura(
                    "Error al intentar leer los archivos .feature",
                    LectorFeature.class.getName(),
                    error
            );
        }
    }

    // Método para procesar cada archivo .feature
    private void procesarArchivoFeature(Path feature) {

        String contenido = this.leerArchivoCaracteristicas(feature.toString());
        List<String> referencias = this.encontrarReferenciasExcel(contenido);

        for (String referencia : referencias) {

            Map<String, String> datos = this.extraerDatosCaracteristicas(referencia);
            String rutaDeArchivo = datos.get("rutaArchivo");
            String nombreHoja = datos.get("nombreHoja");

            contenido = EscrituraFeature.escribirDatosEnArchivoDeCaracteristicas(
                    contenido,
                    LectorExcel.leerDatosDesdeExcel(rutaDeArchivo, nombreHoja, getTrazaDeFila()),
                    getProcesarDato(),
                    rutaDeArchivo,
                    nombreHoja
            );

        }

        EscrituraFeature.escribirContenidoEnArchivo(contenido, feature.toString());

    }

    private String leerArchivoCaracteristicas(String rutaArchivo) {
        StringBuilder contenido = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(rutaArchivo))) {
            while (scanner.hasNextLine()) {
                contenido.append(scanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException error) {
            throw new ExcepcionDeLectura(
                    "Archivo .feature no encontrado en la ruta especificada",
                    LectorFeature.class.getName(),
                    error
            );
        }
        return contenido.toString();
    }

    private List<String> encontrarReferenciasExcel(String contenido) {
        String[] lineas = contenido.split("\n");
        List<String> referencias = new ArrayList<>();

        for (String linea : lineas) {
            if (linea.contains("#@data:")) {
                referencias.add(linea.trim());
            }
        }

        return referencias;
    }

    private Map<String, String> extraerDatosCaracteristicas(String referencia) {

        String[] partes = referencia.split("#");
        String rutaArchivo = partes[1].split(":")[1];
        String nombreHoja = partes[2].split(":")[1];

        Map<String, String> datos = new HashMap<>();
        datos.put("rutaArchivo", rutaArchivo);
        datos.put("nombreHoja", nombreHoja);

        RegistrarInformacion.deConsola("Ruta del archivo Excel: {}", rutaArchivo);
        RegistrarInformacion.deConsola("Nombre de la hoja de cálculo: {}", nombreHoja);

        return datos;

    }

}
