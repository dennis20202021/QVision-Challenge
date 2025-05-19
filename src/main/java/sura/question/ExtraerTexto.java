package sura.question;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

/*
 * @(#) ExtraerTexto.java 1.0 15/04/2025
 *
 * Copyright 2024 Sura Afore, Inc. Todos los derechos reservados.
 */

/**
 * La clase {@code ExtraerTexto} implementa la interfaz {@code Question} de Serenity BDD.
 * Se utiliza para extraer el texto de un elemento web especificado.
 *
 * <p>
 * Esta clase permite:
 * <ul>
 *   <li>Obtener el texto visible de un elemento web.</li>
 *   <li>Utilizar este texto en validaciones o acciones posteriores dentro de pruebas automatizadas.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Ejemplo de uso:
 * <pre>{@code
 * Target elemento = Target.the("campo de texto").located(By.id("campoTexto"));
 * Question<String> textoExtraido = ExtraerTexto.de(elemento);
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
public class ExtraerTexto implements Question<String> {

    private final Target elementoWeb;

    /*
     * @ExtraerTexto
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Constructor para la clase {@code ExtraerTexto}.
     *
     * @param elementoWeb El elemento web del cual se extraerá el texto.
     */
    public ExtraerTexto(Target elementoWeb) {
        this.elementoWeb = elementoWeb;
    }

    /*
     * @answeredBy
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Extrae el texto visible del elemento web especificado.
     *
     * @param actor El actor que interactúa con el elemento web.
     * @return El texto visible del elemento web.
     */
    @Override
    public String answeredBy(Actor actor) {
        return elementoWeb.resolveFor(actor).getText();
    }

    /*
     * @de
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Método estático para crear una instancia de {@code ExtraerTexto}.
     *
     * @param elementoWeb El elemento web del cual se extraerá el texto.
     * @return Una nueva instancia de {@code ExtraerTexto}.
     */
    public static Question<String> de(Target elementoWeb) {
        return new ExtraerTexto(elementoWeb);
    }
}
