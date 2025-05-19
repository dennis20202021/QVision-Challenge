package sura.question;

import net.serenitybdd.screenplay.*;

/*
 * @(#) EvaluarCoincidencia.java 1.0 15/04/2025
 *
 * Copyright 2024 Sura Afore, Inc. Todos los derechos reservados.
 */

/**
 * La clase {@code EvaluarCoincidencia} implementa la interfaz {@code Question} de Serenity BDD.
 * Se utiliza para evaluar si un valor actual coincide con un valor esperado, ignorando mayúsculas y minúsculas.
 *
 * <p>
 * Esta clase permite:
 * <ul>
 *   <li>Comparar un valor actual proporcionado por una {@code Question} con un valor esperado.</li>
 *   <li>Devolver un resultado booleano indicando si los valores coinciden.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Ejemplo de uso:
 * <pre>{@code
 * Question<String> valorActual = Text.of(someTarget);
 * String valorEsperado = "Texto esperado";
 * Question<Boolean> coincidencia = EvaluarCoincidencia.deDosObjetos(valorActual, valorEsperado);
 * }</pre>
 * </p>
 *
 * <p>
 * Esta clase es útil para realizar validaciones en pruebas automatizadas con Serenity BDD.
 * </p>
 *
 * @author Equipo de Automatizaciones SQA S.A
 * @version 1.0
 * @since 15/04/2025
 */
public class EvaluarCoincidencia implements Question<Boolean> {

    private final Question<String> valorActual;
    private final String valorEsperado;

    /*
     * @EvaluarCoincidencia
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Constructor para la clase {@code EvaluarCoincidencia}.
     *
     * @param valorActual   La {@code Question} que proporciona el valor actual.
     * @param valorEsperado El valor esperado con el que se comparará.
     */
    public EvaluarCoincidencia(Question<String> valorActual, String valorEsperado) {
        this.valorActual = valorActual;
        this.valorEsperado = valorEsperado;
    }

    /*
     * @answeredBy
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Evalúa si el valor actual coincide con el valor esperado, ignorando mayúsculas y minúsculas.
     *
     * @param actor El actor que realiza la evaluación.
     * @return {@code true} si los valores coinciden, de lo contrario {@code false}.
     */
    @Override
    public Boolean answeredBy(Actor actor) {
        String actualString = this.valorActual.answeredBy(actor);
        return actualString != null && actualString.equalsIgnoreCase(this.valorEsperado);
    }

    /*
     * @deDosObjetos
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Método estático para crear una instancia de {@code EvaluarCoincidencia}.
     *
     * @param valorActual   La {@code Question} que proporciona el valor actual.
     * @param valorEsperado El valor esperado con el que se comparará.
     * @return Una nueva instancia de {@code EvaluarCoincidencia}.
     */
    public static Question<Boolean> deDosObjetos(Question<String> valorActual, String valorEsperado) {
        return new EvaluarCoincidencia(valorActual, valorEsperado);
    }
}
