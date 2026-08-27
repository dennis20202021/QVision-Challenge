package qvision.util;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class UtilidadesDeTiempo {

    private UtilidadesDeTiempo() {
        throw new IllegalStateException("Utility class");
    }

    public static String adicionarMinutosALaHoraObtenida(String hora, int adicionarMinutos) {
        LocalTime tiempo = LocalTime.parse(hora, DateTimeFormatter.ofPattern("H:mm"));
        LocalTime tiempoActualizado = tiempo.plusMinutes(adicionarMinutos);
        return tiempoActualizado.format(DateTimeFormatter.ofPattern("H:mm"));
    }

    public static String obtenerHoraMilitarDeMexico() {
        return ZonedDateTime.now(ZoneId.of("America/Mexico_City")).format(DateTimeFormatter.ofPattern("H:mm"));
    }

}


