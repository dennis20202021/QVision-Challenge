package sura.interaction.iframe;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Switch;
import net.serenitybdd.screenplay.targets.Target;

/*
 * @(#) RealizarAccionEn.java 1.0 14/04/2025
 *
 * Copyright 2024 Sura Afore, Inc. Todos los derechos reservados.
 */

/**
 * La clase {@code RealizarAccionEn} implementa la interfaz {@code Interaction} de Serenity BDD.
 * Se utiliza para realizar una o más acciones dentro de un iframe específico en la interfaz de usuario,
 * cambiando el contexto al iframe, ejecutando las acciones y luego regresando al marco principal.
 *
 * <p>
 * Esta clase permite a los actores (actors) realizar las siguientes acciones:
 * <ul>
 *   <li>Cambiar el contexto del navegador al iframe especificado.</li>
 *   <li>Ejecutar una o más acciones dentro del iframe.</li>
 *   <li>Regresar al marco principal después de ejecutar las acciones.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Para crear una instancia de esta interacción, utilice el método estático
 * {@link #iframeEspecificado(Target, Performable...)}.
 * </p>
 *
 * @author Equipo de Automatizaciones SQA S.A
 * @version 1.0
 * @since 14/04/2025
 */
public class RealizarAccionEn implements Interaction {

    private final Performable[] accionesDentroDelIframe;
    private final Target localizadorIframe;

    /*
     * @RealizarAccionEn
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Constructor para la clase {@code RealizarAccionEn}.
     *
     * @param localizadorIframe       El {@code Target} que identifica el iframe al que se cambiará el contexto.
     * @param accionesDentroDelIframe Las acciones que se ejecutarán dentro del iframe.
     */
    public RealizarAccionEn(Target localizadorIframe, Performable... accionesDentroDelIframe) {
        this.localizadorIframe = localizadorIframe;
        this.accionesDentroDelIframe = accionesDentroDelIframe;
    }

    /*
     * @performAs
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Realiza la acción de cambiar al iframe, ejecutar las acciones y regresar al marco principal.
     *
     * @param actor El actor que realiza la interacción.
     * @param <T>   El tipo de actor.
     */
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                CambiarAIframe.con(this.localizadorIframe)
        );
        actor.attemptsTo(this.accionesDentroDelIframe);
        actor.attemptsTo(
                Switch.toParentFrame()
        );
    }

    /*
     * @iframeEspecificado
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Método estático para crear una nueva instancia de la interacción {@code RealizarAccionEn}.
     *
     * @param localizadorIframe       El {@code Target} que identifica el iframe al que se cambiará el contexto.
     * @param accionesDentroDelIframe Las acciones que se ejecutarán dentro del iframe.
     * @return Una nueva instancia de la interacción {@code RealizarAccionEn}.
     */
    public static Interaction iframeEspecificado(Target localizadorIframe, Performable... accionesDentroDelIframe) {
        return Instrumented.instanceOf(RealizarAccionEn.class)
                .withProperties(localizadorIframe, accionesDentroDelIframe);
    }
}
