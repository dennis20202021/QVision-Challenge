package sura.task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;
import sura.interaction.ClicSi;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;
import static sura.ui.AforeDashboardUI.CERRAR_SESION_BTN;
import static sura.ui.AforePortalAccesoLoginUI.FORMULARIO_LOGIN;

public class CerrarSesionTask implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (CERRAR_SESION_BTN.isVisibleFor(actor)) {
            actor.attemptsTo(
                    WaitUntil.the(CERRAR_SESION_BTN, isCurrentlyVisible()).forNoMoreThan(60).seconds(),
                    ClicSi.elElementoEsVisible(CERRAR_SESION_BTN).build(),
                    WaitUntil.the(FORMULARIO_LOGIN, isCurrentlyVisible()).forNoMoreThan(60).seconds(),
                    Ensure.that(FORMULARIO_LOGIN).isDisplayed()
            );
        }
    }

    public static CerrarSesionTask actual() {
        return Tasks.instrumented(CerrarSesionTask.class);
    }
}
