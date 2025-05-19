package sura.interaction.wait;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import sura.util.RegistrarInformacion;
import sura.util.config.wait.EsperarArchivo;

import java.time.Duration;

/*
 * @(#) EsperaDescarga.java 1.0 14/04/2025
 *
 * Copyright 2024 Sura Afore, Inc. Todos los derechos reservados.
 */

/**
 * La clase {@code EsperaDescarga} implementa la interfaz {@code Interaction} de Serenity BDD.
 * Se utiliza para esperar la descarga de un archivo en una ubicación específica dentro de un tiempo límite definido.
 *
 * <p>
 * Esta clase permite a los actores (actors) realizar las siguientes acciones:
 * <ul>
 *   <li>Esperar hasta que un archivo esté disponible en la ruta especificada.</li>
 *   <li>Registrar un mensaje en la consola una vez que el archivo se haya descargado correctamente.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Para crear una instancia de esta interacción, utilice el método estático
 * {@link #deArchivo(String)}.
 * </p>
 *
 * @author Equipo de Automatizaciones SQA S.A
 * @version 1.0
 * @since 14/04/2025
 */
public class EsperaDescarga implements Interaction {

    private final String rutaDeArchivo;
    private final Duration timeout;

    /*
     * @EsperaDescarga
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Constructor para la clase {@code EsperaDescarga}.
     *
     * @param rutaDeArchivo La ruta del archivo cuya descarga se espera.
     */
    public EsperaDescarga(String rutaDeArchivo) {
        this.rutaDeArchivo = rutaDeArchivo;
        this.timeout = Duration.ofSeconds(30);
    }

    /*
     * @performAs
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Realiza la acción de esperar la descarga del archivo en la ruta especificada.
     *
     * @param actor El actor que realiza la interacción.
     * @param <T>   El tipo de actor.
     */
    @Override
    public <T extends Actor> void performAs(T actor) {
        new EsperarArchivo(this.rutaDeArchivo, this.timeout).deDescarga();
        RegistrarInformacion.deConsola("¡Archivo descargado correctamente!");
    }

    /*
     * @deArchivo
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Método estático para crear una nueva instancia de la interacción {@code EsperaDescarga}.
     *
     * @param rutaDeArchivo La ruta del archivo cuya descarga se espera.
     * @return Una nueva instancia de la interacción {@code EsperaDescarga}.
     */
    public static Interaction deArchivo(String rutaDeArchivo) {
        return Instrumented.instanceOf(EsperaDescarga.class).withProperties(rutaDeArchivo);
    }
}
