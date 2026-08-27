package qvision.service;

import org.apache.poi.ss.usermodel.*;

import java.util.*;

public class TrazaDeDatos {

    private TrazaDeDatos() {
        throw new IllegalStateException("Utility class");
    }

    public static Map<String, String> filaTrazaAMapa(Row fila) {

        Map<String, String> mapa = new LinkedHashMap<>();
        DataFormatter dataFormatter = new DataFormatter();

        for (Cell cell : fila) {
            String valor = dataFormatter.formatCellValue(cell);
            mapa.put(String.format("Columna %d", (cell.getColumnIndex() + 1)), valor);
        }
        return mapa;
    }
}
