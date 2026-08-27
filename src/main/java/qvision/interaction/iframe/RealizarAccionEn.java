package qvision.interaction.iframe;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Switch;
import net.serenitybdd.screenplay.targets.Target;

public class RealizarAccionEn implements Interaction {

    private final Performable[] accionesDentroDelIframe;
    private final Target localizadorIframe;

    public RealizarAccionEn(Target localizadorIframe, Performable... accionesDentroDelIframe) {
        this.localizadorIframe = localizadorIframe;
        this.accionesDentroDelIframe = accionesDentroDelIframe;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                CambiarAIframe.con(this.localizadorIframe)
        );
        actor.attemptsTo(this.accionesDentroDelIframe);
        actor.attemptsTo(
                Switch.toParentFrame()
        );
    }

    public static Interaction iframeEspecificado(Target localizadorIframe, Performable... accionesDentroDelIframe) {
        return Instrumented.instanceOf(RealizarAccionEn.class)
                .withProperties(localizadorIframe, accionesDentroDelIframe);
    }
}


