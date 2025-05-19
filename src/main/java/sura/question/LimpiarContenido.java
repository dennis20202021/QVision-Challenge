package sura.question;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;
import org.jsoup.Jsoup;
import sura.util.RegistrarInformacion;

/*
 * @(#) LimpiarContenido.java 1.0 15/04/2025
 *
 * Copyright 2024 Sura Afore, Inc. Todos los derechos reservados.
 */

/**
 * La clase {@code LimpiarContenido} implementa la interfaz {@code Question} de Serenity BDD.
 * Se utiliza para extraer y limpiar el contenido HTML de un elemento web, dejando solo el texto visible.
 *
 * <p>
 * Esta clase permite:
 * <ul>
 *   <li>Obtener el texto limpio de un elemento web, eliminando etiquetas HTML.</li>
 *   <li>Registrar el texto limpio en la consola para facilitar el diagnóstico.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Ejemplo de uso:
 * <pre>{@code
 * Target elemento = Target.the("campo de texto").located(By.id("campoTexto"));
 * Question<String> textoLimpio = LimpiarContenido.htmlSinProcesar(elemento);
 * }</pre>
 * </p>
 *
 * <p>
 * Esta clase es útil para realizar validaciones en pruebas automatizadas con Serenity BDD,
 * donde se requiere trabajar con texto limpio extraído de elementos web.
 * </p>
 *
 * @author Equipo de Automatizaciones SQA S.A
 * @version 1.0
 * @since 15/04/2025
 */
public class LimpiarContenido implements Question<String> {

    private final Target elementoWeb;

    /*
     * @LimpiarContenido
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Constructor para la clase {@code LimpiarContenido}.
     *
     * @param elementoWeb El elemento web del cual se extraerá y limpiará el contenido HTML.
     */
    public LimpiarContenido(Target elementoWeb) {
        this.elementoWeb = elementoWeb;
    }

    /*
     * @answeredBy
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Extrae y limpia el contenido HTML del elemento web, dejando solo el texto visible.
     *
     * @param actor El actor que interactúa con el elemento web.
     * @return El texto limpio del elemento web.
     */
    @Override
    public String answeredBy(Actor actor) {
        String textoLimpio = Jsoup.parse(actor.asksFor(ExtraerTexto.de(this.elementoWeb))).text();
        RegistrarInformacion.deConsola("Texto limpio: {}", textoLimpio);
        return textoLimpio;
    }

    /*
     * @htmlSinProcesar
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Método estático para crear una instancia de {@code LimpiarContenido}.
     *
     * @param elementoWeb El elemento web del cual se extraerá y limpiará el contenido HTML.
     * @return Una nueva instancia de {@code LimpiarContenido}.
     */
    public static Question<String> htmlSinProcesar(Target elementoWeb) {
        return new LimpiarContenido(elementoWeb);
    }
}
