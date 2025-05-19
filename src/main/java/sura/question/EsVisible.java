package sura.question;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.FluentWait;
import sura.util.RegistrarInformacion;

import java.time.Duration;

/*
 * @(#) EsVisible.java 1.0 05/01/2025
 *
 * Copyright 2024 Sura Afore, Inc. Todos los derechos reservados.
 */

/**
 * La clase EsVisible implementa la interfaz Question de Serenity BDD.
 * Es usado para determinar si un elemento web (target) especificado es visible para un actor dado.
 *
 * @author Equipo de Automatizaciones SQA S.A
 * @version 1.0
 * @since 05/01/2025
 */
public class EsVisible implements Question<Boolean> {

    private final Target target;
    private final Long timeout;

    /*
     * @EsVisible
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Constructor para la clase EsVisible.
     *
     * @param target  El elemento web a ser verificado para visibilidad.
     * @param timeout El tiempo de espera en segundos.
     */
    public EsVisible(Target target, Long timeout) {
        this.target = target;
        this.timeout = timeout != null ? timeout : 15L;
    }

    /*
     * @answeredBy
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Determina si el elemento web (target) especificado es visible al actor proporcionado.
     *
     * @param actor el actor que realizará la verificación de visibilidad.
     * @return verdadero si el elemento web (target) está presente y se muestra para el actor, de lo contrario falso.
     */
    @Override
    public Boolean answeredBy(Actor actor) {

        try {

            FluentWait<Actor> wait = new FluentWait<>(actor)
                    .withTimeout(Duration.ofSeconds(this.timeout))
                    .pollingEvery(Duration.ofSeconds(5))
                    .ignoring(NoSuchElementException.class);

            wait.until(a -> this.target.resolveFor(a).isCurrentlyVisible());

            boolean esVisible = this.target.resolveFor(actor).isPresent() && this.target.resolveFor(actor).isDisplayed();
            RegistrarInformacion.deConsola("Estado de visibilidad del elemento: {}", esVisible);
            return esVisible;

        } catch (Exception e) {
            RegistrarInformacion.deConsola(
                    "Error mientras esperaba a que el elemento \"{}\" fuera visible: {}",
                    this.target.getName(),
                    e.getMessage()
            );
            return false;
        }
    }

    /*
     * @de
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Método estático para crear una instancia de la clase EsVisible con un tiempo de espera predeterminado.
     *
     * @param target El elemento web a ser verificado para visibilidad.
     * @return Una nueva instancia de la clase EsVisible con un tiempo de espera predeterminado.
     */
    public static EsVisible de(Target target) {
        return new EsVisible(target, null);
    }

    /*
     * @de
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Método estático para crear una instancia de la clase EsVisible con un tiempo de espera especificado.
     *
     * @param target  El elemento web a ser verificado para visibilidad.
     * @param timeout El tiempo de espera en segundos.
     * @return Una nueva instancia de la clase EsVisible con el tiempo de espera especificado.
     */
    public static EsVisible de(Target target, Long timeout) {
        return new EsVisible(target, timeout);
    }
}
