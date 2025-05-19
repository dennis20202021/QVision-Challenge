package sura.util;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/*
 * @(#) UtilidadesDeTiempo.java 1.0 30/12/2024
 *
 * Copyright 2024 Sura Afore, Inc. Todos los derechos reservados.
 */

/**
 * Provee utilidades relacionadas con el tiempo en la zona horaria de México.
 * Esta clase es inmutable y segura para hilos.
 *
 * @author Equipo de Automatizaciones SQA S.A
 * @version 1.0
 * @since 30/12/2024
 */
public class UtilidadesDeTiempo {

    /*
     * @UtilidadesDeTiempo
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Constructor privado para prevenir la creación de instancias de esta clase.
     */
    private UtilidadesDeTiempo() {
        throw new IllegalStateException("Utility class");
    }

    /*
     * @adicionarMinutosALaHoraObtenida
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Recupera la hora militar actual en la zona horaria de México, ajustada por los minutos especificados.
     * <p>
     * Este método toma una hora en formato de cadena y un número de minutos para agregar a esa hora.
     * La hora se analiza utilizando el formato de 24 horas ("H:mm"), se ajusta sumando los minutos
     * especificados, y luego retorna como una cadena en el mismo formato.
     *
     * @param hora             La hora inicial en formato de cadena ("H:mm").
     * @param adicionarMinutos El número de minutos a agregar al tiempo actual.
     * @return La hora militar ajustada, formateada como una cadena ("H:mm").
     */
    public static String adicionarMinutosALaHoraObtenida(String hora, int adicionarMinutos) {
        LocalTime tiempo = LocalTime.parse(hora, DateTimeFormatter.ofPattern("H:mm"));
        LocalTime tiempoActualizado = tiempo.plusMinutes(adicionarMinutos);
        return tiempoActualizado.format(DateTimeFormatter.ofPattern("H:mm"));
    }

    /*
     * @obtenerHoraMilitarDeMexico
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Recupera la hora militar actual en la zona horaria de México.
     *
     * @return La hora militar actual formateada como una cadena.
     */
    public static String obtenerHoraMilitarDeMexico() {
        return ZonedDateTime.now(ZoneId.of("America/Mexico_City")).format(DateTimeFormatter.ofPattern("H:mm"));
    }

}
