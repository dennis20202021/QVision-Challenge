package qvision.interaction.iframe;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CambiarAIframe implements Interaction {

    private final Target localizadorIframe;

    public CambiarAIframe(Target localizadorIframe) {
        this.localizadorIframe = localizadorIframe;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        WebDriver driver = BrowseTheWeb.as(actor).getDriver(); //Obtener instancia Driver

        WebElement elementoIframe = this.localizadorIframe.resolveFor(actor); // Convertir el Target a WebElement
        driver.switchTo().frame(elementoIframe); // Cambiar de iframe

    }

    public static Interaction con(Target localizadorIframe) {
        return Instrumented.instanceOf(CambiarAIframe.class).withProperties(localizadorIframe);
    }
}


