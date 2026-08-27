package qvision.interaction;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.ElementClickInterceptedException;
import qvision.question.smart.ElElementoWeb;
import qvision.util.RegistrarInformacion;

import java.util.Optional;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isCurrentlyVisible;

public class ClicSi implements Interaction {

    private final Target buttonElementTarget;
    private final String errorMessage;
    private final Object[] errorMessageParams;

    public ClicSi(Target buttonElementTarget, String errorMessage, Object... params) {
        this.buttonElementTarget = buttonElementTarget;
        this.errorMessage = errorMessage;
        this.errorMessageParams = params;
    }

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

    public static ClicSiBuilder elElementoEsVisible(Target buttonElementTarget) {
        return Instrumented.instanceOf(ClicSiBuilder.class).withProperties(buttonElementTarget);
    }

    public static class ClicSiBuilder {

        private final Target buttonElementTarget;
        private String errorMessage;
        private Object[] errorMessageParams;

        public ClicSiBuilder(Target buttonElementTarget) {
            this.buttonElementTarget = buttonElementTarget;
        }

        public ClicSiBuilder oMostrarMensajeDeError(String errorMessage, Object... params) {
            this.errorMessage = errorMessage;
            this.errorMessageParams = params;
            return this; // Retorna la instancia actual para encadenar llamadas
        }

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


