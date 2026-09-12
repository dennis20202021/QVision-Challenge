package qvision.question;

import net.serenitybdd.screenplay.*;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.FluentWait;
import qvision.util.RegistrarInformacion;

import java.time.Duration;

public class EsVisible implements Question<Boolean> {

    private final Target target;
    private final Long timeout;

    public EsVisible(Target target, Long timeout) {
        this.target = target;
        this.timeout = timeout != null ? timeout : 15L;
    }

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

    public static EsVisible de(Target target) {
        return new EsVisible(target, null);
    }

    public static EsVisible de(Target target, Long timeout) {
        return new EsVisible(target, timeout);
    }
}


