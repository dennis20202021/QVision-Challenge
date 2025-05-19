package sura.interaction;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.ElementClickInterceptedException;
import sura.question.smart.ElElementoWeb;
import sura.util.RegistrarInformacion;

import java.util.Optional;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isCurrentlyVisible;

/*
 * @(#) ClicSi.java 1.0 05/01/2025
 *
 * Copyright 2024 Sura Afore, Inc. Todos los derechos reservados.
 */

/**
 * La clase ClicSi implementa la interfaz Interaction de Serenity BDD.
 * Es usado para realizar una acción de clic en un elemento web (target) especificado si es visible y se puede hacer clic.
 * Si el elemento web (target) no está presente, registra un mensaje de error.
 *
 * @author Equipo de Automatizaciones SQA S.A
 * @version 1.0
 * @since 05/01/2025
 */
public class ClicSi implements Interaction {

    private final Target buttonElementTarget;
    private final String errorMessage;
    private final Object[] errorMessageParams;

    /*
     * @ClicSi
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Constructor para la clase ClicSi.
     *
     * @param buttonElementTarget El elemento web (target) para hacer clic.
     * @param errorMessage        El mensaje de error a ser registrado si el elemento web (target) no está presente.
     * @param params              Parámetros adicionales para el mensaje de error.
     */
    public ClicSi(Target buttonElementTarget, String errorMessage, Object... params) {
        this.buttonElementTarget = buttonElementTarget;
        this.errorMessage = errorMessage;
        this.errorMessageParams = params;
    }

    /*
     * @performAs
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Realiza la acción de clic en el elemento web (target) especificado si es visible y se puede hacer clic.
     * Si el elemento web (target) no está presente, registra el mensaje de error proporcionado.
     * Si no se puede hacer clic en el elemento debido a un {@code ElementClickInterceptedException}, realiza un clic
     * en JavaScript como alternativa.
     *
     * @param actor El actor realizando la interacción.
     * @param <T>   El tipo de actor.
     */
    @Override
    public <T extends Actor> void performAs(T actor) {

        Optional<Target> optionalButton = Optional.of(this.buttonElementTarget);

        optionalButton.ifPresent(
                button -> {
                    if (button.resolveFor(actor).isPresent()) {
                        try {
                            actor.attemptsTo(
                                    WaitUntil.the(button, isCurrentlyVisible()).forNoMoreThan(45).seconds(),
                                    Boolean.FALSE.equals(actor.asksFor(ElElementoWeb.estaSuperpuesto(button)))
                                            ? Click.on(button) : JavaScriptClick.on(button)
                            );
                        } catch (ElementClickInterceptedException error) {
                            actor.attemptsTo(
                                    Scroll.to(button),
                                    JavaScriptClick.on(button)
                            );
                        }
                    } else {
                        RegistrarInformacion.deConsola(this.errorMessage, this.errorMessageParams);
                    }
                }
        );
    }

    /*
     * @elElementoEsVisible
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Método estático para crear un constructor para la clase ClicSi.
     *
     * @param buttonElementTarget El elemento web (target) para hacer clic.
     * @return Una nueva instancia de la clase ClicSiBuilder.
     */
    public static ClicSiBuilder elElementoEsVisible(Target buttonElementTarget) {
        return Instrumented.instanceOf(ClicSiBuilder.class).withProperties(buttonElementTarget);
    }

    /*
     * @(#) ClicSiBuilder.java 1.0 05/01/2025
     *
     * Copyright 2024 Sura Afore, Inc. Todos los derechos reservados.
     */

    /**
     * La clase ClicSiBuilder es usado para construir una nueva instancia de la clase ClicSi.
     *
     * @author Equipo de Automatizaciones SQA S.A
     * @version 1.0
     * @since 05/01/2025
     */
    public static class ClicSiBuilder {

        private final Target buttonElementTarget;
        private String errorMessage;
        private Object[] errorMessageParams;

        /*
         * @ClicSiBuilder
         *
         * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
         */

        /**
         * Constructor para la clase ClicSiBuilder.
         *
         * @param buttonElementTarget El elemento web (target) para hacer clic.
         */
        public ClicSiBuilder(Target buttonElementTarget) {
            this.buttonElementTarget = buttonElementTarget;
        }

        /*
         * @oMostrarMensajeDeError
         *
         * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
         */

        /**
         * Establece el mensaje de error a ser registrado si el elemento web (target) no está presente.
         *
         * @param errorMessage El mensaje de error a ser registrado.
         * @param params       Parámetros adicionales para el mensaje de error.
         * @return La instancia actual de ClicSiBuilder para encadenar llamadas.
         */
        public ClicSiBuilder oMostrarMensajeDeError(String errorMessage, Object... params) {
            this.errorMessage = errorMessage;
            this.errorMessageParams = params;
            return this; // Retorna la instancia actual para encadenar llamadas
        }

        /*
         * @build
         *
         * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
         */

        /**
         * Construye una nueva instancia de la clase ClicSi con las propiedades especificadas.
         *
         * @return Una nueva instancia de la clase ClicSi.
         */
        public Interaction build() {
            return Instrumented.instanceOf(ClicSi.class)
                    .withProperties(
                            this.buttonElementTarget,
                            this.errorMessage,
                            this.errorMessageParams
                    );
        }
    }
}
