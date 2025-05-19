package sura.util;

import sura.exception.ExcepcionDeEscritura;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Function;

public class EscrituraFeature {

    private EscrituraFeature() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> String escribirDatosEnArchivoDeCaracteristicas(String contenido, List<T> datos, Function<T, String> procesarDato, String rutaDeArchivoEsperada, String nombreHojaEsperada) {

        String[] lineas = contenido.split("\n");
        StringBuilder nuevoContenido = new StringBuilder();

        boolean escribirDatos = false;
        boolean eliminarLineas = false;


        for (String linea : lineas) {
            if (linea.trim().contains(String.format("#@data:%s", rutaDeArchivoEsperada)) && linea.contains(String.format("#@sheetName:%s", nombreHojaEsperada))) {
                nuevoContenido.append(linea).append("\n");
                escribirDatos = true;
                eliminarLineas = true;
            } else if (eliminarLineas) {
                if (!linea.startsWith("\t\t|")) {
                    eliminarLineas = false;
                    escribirDatosEnContenido(nuevoContenido, datos, procesarDato);
                    escribirDatos = false;
                    nuevoContenido.append(linea).append("\n");
                }
            } else {
                nuevoContenido.append(linea).append("\n");
            }
        }

        if (escribirDatos) {
            escribirDatosAlFinal(nuevoContenido, datos, procesarDato);
        }
        return nuevoContenido.toString();
    }

    private static <T> void escribirDatosEnContenido(StringBuilder contenido, List<T> datos, Function<T, String> procesarDato) {
        for (T dato : datos) {
            contenido.append("\t\t| ").append(procesarDato.apply(dato)).append(" |\n");
        }
    }

    private static <T> void escribirDatosAlFinal(StringBuilder contenido, List<T> datos, Function<T, String> procesarDato) {
        for (T dato : datos) {
            contenido.append("\t\t| ").append(procesarDato.apply(dato)).append(" |\n");
        }
    }

    public static void escribirContenidoEnArchivo(String contenido, String rutaArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(rutaArchivo), StandardCharsets.UTF_8))) {
            writer.write(contenido);
        } catch (IOException error) {
            throw new ExcepcionDeEscritura("Error al intentar escribir en el archivo .feature", EscrituraFeature.class.getName(), error);
        }
    }
}
