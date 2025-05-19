package sura.interaction;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.webdriver.WebDriverFacade;
import net.thucydides.core.webdriver.WebDriverFactory;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;
import org.openqa.selenium.WebDriver;
import sura.util.config.SustituirOpcionesChrome;
import sura.util.config.SustituirOpcionesEdge;
import sura.util.config.SustituirOpcionesFirefox;

import java.io.File;
import java.util.UUID;

import static sura.util.HiloConcurrente.establecerIdUnico;
import static sura.util.HiloConcurrente.obtenerIdUnico;
import static sura.util.file.GestionarArchivos.obtenerRutaSubDirectorio;

/*
 * @(#) SobreescribirOpciones.java 1.0 14/04/2025
 *
 * Copyright 2024 Sura Afore, Inc. Todos los derechos reservados.
 */

/**
 * La clase {@code SobreescribirOpciones} implementa la interfaz {@code Interaction} de Serenity BDD.
 * Se utiliza para configurar opciones personalizadas del navegador durante la ejecución de pruebas automatizadas,
 * incluyendo la configuración de rutas de descarga específicas y la inicialización del navegador con dichas opciones.
 *
 * <p>
 * Esta clase permite a los actores (actors) realizar las siguientes acciones:
 * <ul>
 *   <li>Generar un identificador único para configurar rutas personalizadas de descarga.</li>
 *   <li>Configurar opciones específicas para navegadores como Chrome, Firefox y Edge.</li>
 *   <li>Inicializar el navegador con las opciones configuradas.</li>
 *   <li>Almacenar la ruta personalizada de descarga en la memoria del actor.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Para crear una instancia de esta interacción, utilice el método estático
 * {@link #delNavegador()}.
 * </p>
 *
 * <p>
 * Esta clase es compatible con los navegadores Chrome, Firefox y Edge. Si se intenta utilizar un navegador no soportado,
 * se lanzará una excepción {@code UnsupportedOperationException}.
 * </p>
 *
 * @author Equipo de Automatizaciones SQA S.A
 * @version 1.0
 * @since 14/04/2025
 */
public class SobreescribirOpciones implements Interaction {

    @Managed
    WebDriver driver;

    private final String nombreNavegador;

    /*
     * @SobreescribirOpciones
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Constructor para la clase {@code SobreescribirOpciones}.
     * Obtiene el nombre del navegador desde las variables de entorno.
     */
    public SobreescribirOpciones() {
        EnvironmentVariables variablesDeAmbiente = SystemEnvironmentVariables.createEnvironmentVariables();
        this.nombreNavegador = variablesDeAmbiente.getProperty("webdriver.driver");
    }


    /*
     * @performAs
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Realiza la acción de configurar las opciones del navegador y establecer una ruta personalizada de descarga.
     *
     * @param actor El actor que realiza la interacción.
     * @param <T>   El tipo de actor.
     */
    @Override
    public <T extends Actor> void performAs(T actor) {

        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString().replace("-", "");
        String numeroSeisDigitosUnico = uuidStr.substring(uuidStr.length() - 6);

        String rutaDeArchivo = String.format("%s%s%s", obtenerNombreNavegador(), File.separator, numeroSeisDigitosUnico);

        establecerIdUnico(rutaDeArchivo);

        String rutaPersonalizadaDeDescarga = obtenerRutaSubDirectorio(obtenerIdUnico());
        actor.remember("rutaArchivo", rutaPersonalizadaDeDescarga);

        switch (obtenerNombreNavegador()) {
            case "chrome" ->
                    this.driver = new SustituirOpcionesChrome().implementarOpciones(rutaPersonalizadaDeDescarga);
            case "firefox" ->
                    this.driver = new SustituirOpcionesFirefox().implementarOpciones(rutaPersonalizadaDeDescarga);
            case "edge" -> this.driver = new SustituirOpcionesEdge().implementarOpciones(rutaPersonalizadaDeDescarga);
            default ->
                    throw new UnsupportedOperationException(String.format("Navegador incompatible: %s", this.nombreNavegador));
        }

        this.driver.manage().deleteAllCookies();
        BrowseTheWeb.as(actor).setDriver(new WebDriverFacade(this.driver, new WebDriverFactory()));
    }

    /*
     * @obtenerNombreNavegador
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Obtiene el nombre del navegador desde las variables de entorno o las propiedades del sistema.
     *
     * @return El nombre del navegador en minúsculas.
     */
    private String obtenerNombreNavegador() {
        if (!this.nombreNavegador.isEmpty()) {
            return this.nombreNavegador.toLowerCase();
        } else {
            return System.getProperty("webdriver.driver").toLowerCase();
        }
    }

    /*
     * @delNavegador
     *
     * Copyright 2024 Sura Afore Inc. Todos los derechos reservados.
     */

    /**
     * Método estático para crear una nueva instancia de la interacción {@code SobreescribirOpciones}.
     *
     * @return Una nueva instancia de la interacción {@code SobreescribirOpciones}.
     */
    public static Interaction delNavegador() {
        return Instrumented.instanceOf(SobreescribirOpciones.class).newInstance();
    }
}
