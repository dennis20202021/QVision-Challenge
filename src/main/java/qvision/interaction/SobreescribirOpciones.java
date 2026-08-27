package qvision.interaction;

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
import qvision.util.config.SustituirOpcionesChrome;
import qvision.util.config.SustituirOpcionesEdge;
import qvision.util.config.SustituirOpcionesFirefox;

import java.io.File;
import java.util.UUID;

import static qvision.util.HiloConcurrente.establecerIdUnico;
import static qvision.util.HiloConcurrente.obtenerIdUnico;
import static qvision.util.file.GestionarArchivos.obtenerRutaSubDirectorio;

public class SobreescribirOpciones implements Interaction {

    @Managed
    WebDriver driver;

    private final String nombreNavegador;

    public SobreescribirOpciones() {
        EnvironmentVariables variablesDeAmbiente = SystemEnvironmentVariables.createEnvironmentVariables();
        this.nombreNavegador = variablesDeAmbiente.getProperty("webdriver.driver");
    }


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

    private String obtenerNombreNavegador() {
        if (!this.nombreNavegador.isEmpty()) {
            return this.nombreNavegador.toLowerCase();
        } else {
            return System.getProperty("webdriver.driver").toLowerCase();
        }
    }

    public static Interaction delNavegador() {
        return Instrumented.instanceOf(SobreescribirOpciones.class).newInstance();
    }
}


