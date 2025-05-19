package sura.interaction.iframe;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*
 * @(#) CambiarAIframe.java 1.0 14/04/2025
 *
 * Copyright 2024 Sura Afore, Inc. Todos los derechos reservados.
 */

/**
 * La clase {@code CambiarAIframe} implementa la interfaz {@code Interaction} de Serenity BDD.
 * Se utiliza para cambiar el contexto del navegador a un iframe específico en la interfaz de usuario.
 *
 * <p>
 * Esta clase permite a los actores (actors) realizar las siguientes acciones:
 * <ul>
 *   <li>Resolver un iframe identificado por un {@code Target}.</li>
 *   <li>Cambiar el contexto del navegador al iframe resuelto.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Para crear una instancia de esta interacción, utilice el método estático
 * {@link #con(Target)}.
 * </p>
 *
 * @author Equipo de Automatizaciones SQA S.A
 * @version 1.0
 * @since 14/04/2025
 */
public class CambiarAIframe implements Interaction {

    private final Target localizadorIframe;

    /*
     * @CambiarAIframe
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Constructor para la clase {@code CambiarAIframe}.
     *
     * @param localizadorIframe El {@code Target} que identifica el iframe al que se cambiará el contexto.
     */
    public CambiarAIframe(Target localizadorIframe) {
        this.localizadorIframe = localizadorIframe;
    }

    /*
     * @performAs
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Realiza la acción de cambiar el contexto del navegador al iframe especificado.
     *
     * @param actor El actor que realiza la interacción.
     * @param <T>   El tipo de actor.
     */
    @Override
    public <T extends Actor> void performAs(T actor) {

        WebDriver driver = BrowseTheWeb.as(actor).getDriver(); //Obtener instancia Driver

        WebElement elementoIframe = this.localizadorIframe.resolveFor(actor); // Convertir el Target a WebElement
        driver.switchTo().frame(elementoIframe); // Cambiar de iframe

    }

    /*
     * @con
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Método estático para crear una nueva instancia de la interacción {@code CambiarAIframe}.
     *
     * @param localizadorIframe El {@code Target} que identifica el iframe al que se cambiará el contexto.
     * @return Una nueva instancia de la interacción {@code CambiarAIframe}.
     */
    public static Interaction con(Target localizadorIframe) {
        return Instrumented.instanceOf(CambiarAIframe.class).withProperties(localizadorIframe);
    }
}
