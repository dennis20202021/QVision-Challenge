package sura.util;

import sura.exception.ExcepcionDeLectura;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/*
 * @(#) Cargar.java 1.0 03/12/2024
 *
 * Copyright 2024 Sura Afore, Inc. Todos los derechos reservados.
 */

/**
 * Clase Utilidad para cargar recursos como InputStreams.
 * <p>
 * La clase Cargar provee un método estático para obtener un InputStream para un recurso especificado por su ruta.
 * Intenta cargar el recurso utilizando el cargador de clases y si no se encuentra, intenta cargarlo desde el
 * sistema de archivos. Esta clase es una clase utilitaria y no debería ser instanciada.
 * </p>
 * <p>
 * Registra la ruta del recurso que se está accediendo y lanza una ExcepcionDeLectura si el recurso
 * no pudo ser encontrado o accedido.
 * </p>
 *
 * @author Equipo de Automatizaciones SQA S.A
 * @version 1.0
 * @since 03/12/2024
 */
public class Cargar {

    /*
     * @Cargar
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Constructor privado para prevenir instancias de esta clase utilidad.
     * <p>
     * Lanza una IllegalStateException si se intenta instanciar la clase.
     * </p>
     */
    private Cargar() {
        throw new IllegalStateException("Utility class");
    }

    private static InputStream flujoDelRecurso;

    /*
     * @obtenerFlujoDelRecurso
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Recupera el InputStream actual para el recurso.
     *
     * @return el InputStream para el recurso.
     */
    private static InputStream obtenerFlujoDelRecurso() {
        return Cargar.flujoDelRecurso;
    }

    /*
     * @establecerFlujoDelRecurso
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Establece el InputStream para el recurso.
     *
     * @param nuevoFlujoDelRecurso el nuevo InputStream que se establecerá para el recurso.
     */
    private static void establecerFlujoDelRecurso(InputStream nuevoFlujoDelRecurso) {
        Cargar.flujoDelRecurso = nuevoFlujoDelRecurso;
    }

    /*
     * @recurso
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Obtiene un InputStream para un recurso especificado por su ruta.
     * <p>
     * Este método primero intenta cargar el recurso utilizando el cargador de clases. Si el
     * recurso no se encuentra en el classpath, intenta cargarlo desde el sistema de archivos.
     * </p>
     * <p>
     * Registra la ruta del recurso que se está accediendo y lanza una ExcepcionDeLectura
     * si el recurso no se encuentra o no se puede acceder.
     * </p>
     *
     * @param ruta la ruta al recurso, ya sea en el classpath o en el sistema de archivos.
     * @return un InputStream para el recurso especificado.
     * @throws ExcepcionDeLectura si el recurso no pudo ser encontrado o accedido.
     */
    public static InputStream recurso(String ruta) {

        try {

            RegistrarInformacion.deConsola("Leyendo recurso desde la ubicación: {}...", ruta);
            establecerFlujoDelRecurso(Cargar.class.getClassLoader().getResourceAsStream(ruta));

            if (Objects.nonNull(Cargar.flujoDelRecurso)) {
                return obtenerFlujoDelRecurso();
            } else {
                establecerFlujoDelRecurso(Files.newInputStream(Path.of(ruta)));
            }

        } catch (IOException error) {
            throw new ExcepcionDeLectura("Archivo no encontrado, por favor verifique su ruta", Cargar.class.getName(), error);
        }

        return obtenerFlujoDelRecurso();

    }

    /*
     * @recursoComoCadena
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Obtiene la ruta de un recurso como una cadena de texto.
     * <p>
     * Este método intenta cargar el recurso utilizando el cargador de clases. Si el recurso no se encuentra
     * en el classpath, intenta cargarlo desde el sistema de archivos y devuelve la ruta como una cadena de texto.
     * </p>
     * <p>
     * Registra la ruta del recurso que se está accediendo y lanza una ExcepcionDeLectura
     * si el recurso no pudo ser encontrado o accedido.
     * </p>
     *
     * @param ruta la ruta al recurso, ya sea en el classpath o en el sistema de archivos.
     * @return la ruta del recurso como una cadena de texto.
     * @throws ExcepcionDeLectura si el recurso no pudo ser encontrado o accedido.
     */
    public static String recursoComoCadena(String ruta) {

        try {
            RegistrarInformacion.deConsola("Leyendo recurso desde la ubicación: {}...", ruta);
            InputStream flujo = Cargar.class.getClassLoader().getResourceAsStream(ruta);

            if (Objects.nonNull(flujo)) {
                return Path.of(
                        Objects.requireNonNull(
                                Cargar.class.getClassLoader().getResource(ruta)
                        ).toURI()
                ).toString();
            } else {
                return Path.of(ruta).toString();
            }

        } catch (Exception error) {
            throw new ExcepcionDeLectura("Archivo no encontrado, por favor verifique su ruta", Cargar.class.getName(), error);
        }
    }
}
