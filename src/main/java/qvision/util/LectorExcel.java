package qvision.util;

import qvision.exception.ExcepcionDeLectura;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;
import java.util.function.Function;

import static qvision.util.Constantes.EXTENSION_EXCEL_MODERNO;

public class LectorExcel {

    private LectorExcel() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> List<T> leerDatosDesdeExcel(String nombreArchivo, String nombreHoja, Function<Row, T> trazaDeFila) {

        List<T> listaDeResultados = new ArrayList<>();

        // Crear un libro de trabajo basado en el tipo de archivo
        try (InputStream fis = new BufferedInputStream(Cargar.recurso(nombreArchivo)); Workbook libroDeTrabajo = nombreArchivo.toLowerCase().endsWith(EXTENSION_EXCEL_MODERNO) ? new XSSFWorkbook(fis) : new HSSFWorkbook(fis)) {

            // Iterar sobre las filas en la hoja
            for (Row fila : libroDeTrabajo.getSheet(nombreHoja)) {
                // Omitir encabezado si es necesario
                if (fila.getRowNum() == 0) continue;

                // Mapear la fila al POJO deseado usando la función de traza proporcionada
                T pojo = trazaDeFila.apply(fila);
                if (pojo != null) {
                    listaDeResultados.add(pojo);
                }
            }

        } catch (IOException error) {
            throw new ExcepcionDeLectura("Error al intentar leer el archivo Excel", LectorExcel.class.getName(), error);
        }
        return listaDeResultados;
    }
}
